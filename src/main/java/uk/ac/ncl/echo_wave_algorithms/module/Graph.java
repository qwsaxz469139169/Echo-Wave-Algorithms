package uk.ac.ncl.echo_wave_algorithms.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import uk.ac.ncl.echo_wave_algorithms.tool.FileUtil;


public class Graph {
	
	private String name;
	
	private List<Node> nodes;

	public String getName() {
		return name;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	/***
	 * create structure by json file
	 * json file is in the resource, it have been created
	 * there are three graph: normalGraph, doughnutGraph, treeGraph (this is the param)
	 * @param String Object: the name of json file , just name without .json
	 * @return List<Node> Object: a graph structure contain N node
	 */
	public static Graph createStructureByJson(String graphName){
		Graph result = new Graph();
		
		//read json file to jsonObj
//		String path = Graph.class.getClassLoader().getResource(graphName+".json").getPath();
		String path = "src/main/resource/"+graphName+".json";
		String jsonString = FileUtil.ReadFile(path);
		JSONObject jsonObj = JSON.parseObject(jsonString);
		
		String name = (String) jsonObj.get("name");
		JSONArray nodesJson = jsonObj.getJSONArray("nodes");
		
		List<Node> nodes = new ArrayList<>();	
		Node node = null;

		//create node
		for (int i = 0; i < nodesJson.size(); i++) {
			JSONObject nodeJson = (JSONObject) nodesJson.get(i);
			int nodeId = Integer.valueOf((String) nodeJson.get("nodeId"));
			node = new Node(nodeId);
			nodes.add(node);
		}
		
		//set neighbor of each node
		for (int i = 0; i < nodesJson.size(); i++) {
			JSONObject nodeJson = (JSONObject) nodesJson.get(i);	
			int nodeId = Integer.valueOf((String) nodeJson.get("nodeId"));;
			node = nodes.get(nodeId);
			
			JSONArray neighborsJson = nodeJson.getJSONArray("neighbor");
			
			List<Node> neighbors = node.getNeighbor();
			
			for (int j = 0; j < neighborsJson.size(); j++) {
				int neighborJson = Integer.valueOf((String) neighborsJson.get(j));
				Node neighbor = nodes.get(neighborJson);

				if (!checkNodeInList(neighbors, neighbor)) {
					neighbors.add(neighbor);
				}
			}	
			
			node.setNeighbor(neighbors);
		}	
		
		//create graph
		result.setName(name);
		result.setNodes(nodes);
		
		return result;
	}
	
	/***
	 * create structure by input
	 * @return List<Node> Object: a graph structure contain N node
	 */
	public static Graph createStructureByInput() {
		// TODO Auto-generated method stub
		Graph result = new Graph();
		List<Node> nodes = result.getNodes();

		Node node = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("Please input the number of nodes:");
		int NodeNum = sc.nextInt();

		for (int i = 0; i < NodeNum; i++) {
			node = new Node(i);
			nodes.add(node);
		}

		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			List<Node> neighbor = node.getNeighbor();

			System.out.print("Node " + i + ", ");
			System.out.println("please input the neighbor of this node and end with -1:");
			while (true) {
				int neigh = sc.nextInt();

				if (neigh == -1) {
					break;
				}
				
				Node neighborNode = nodes.get(neigh);
				
				if (!checkNodeInList(neighbor, neighborNode)) {
					neighbor.add(neighborNode);
				}
				
				List<Node> neighborNodeList = neighborNode.getNeighbor();
				
				if(!checkNodeInList(neighborNodeList, node)){
					neighborNodeList.add(node);
				}
				
				neighborNode.setNeighbor(neighborNodeList);
			}

			node.setNeighbor(neighbor);
		}
		
		result.setName("defaultGraph");
		result.setNodes(nodes);

		System.out.println("Create successful!");
		return result;
	}
	
	/***
	 * check whether the node is in the list
	 * @param List<Node> Object
	 * @param Node Object
	 * @return boolean Object
	 */
	public static boolean checkNodeInList(List<Node> nodeList, Node node) {
		boolean result = false;

		for (int i = 0; i < nodeList.size(); i++) {
			if (node.getNodeId() == nodeList.get(i).getNodeId()) {
				result = true;
				break;
			}
		}

		return result;
	}
	
	/***
	 * print tree in console
	 * @param List<Node> Object: a graph structure contain N node
	 */
	public static void printTree(List<Node> Structure) {
		// TODO Auto-generated method stub

		System.out.println("Tree--------------------------------------------------");
		Node node = null;

		for (int i = 0; i < Structure.size(); i++) {
			node = Structure.get(i);

			if (node.getFatherId() == -1) {
				System.out.println("    Node " + node.getNodeId() + ", the node is farther node.");

			} else {
				System.out.println("    Node " + node.getNodeId() + ", the farther of this node is " + node.getFatherId() + ".");
			}
		}

	}
}
