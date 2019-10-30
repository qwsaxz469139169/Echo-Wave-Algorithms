package uk.ac.ncl.echo_wave_algorithms.main;

import java.util.List;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.doublematrix.DenseDoubleMatrix2D;

import java.util.ArrayList;


import uk.ac.ncl.echo_wave_algorithms.module.Node;
import uk.ac.ncl.echo_wave_algorithms.module.Graph;
import uk.ac.ncl.echo_wave_algorithms.tool.RandomTool;

public class Algorithms{
	
	//matrix
	private static DenseDoubleMatrix2D matrix = null;	
	
	/***
	 * The main method and entrance of echo wave algorithms. 
	 * Recording message by matrix to change state of all of the node,
	 * @param List<Node> Object: a graph structure contain N node
	 */
	public static void algorithms(Graph structure) {
		//Get node number
		List<Node> nodes = structure.getNodes();
		int nodeNum = nodes.size();
		//Create n*n Matrix
		matrix = DenseMatrix.Factory.zeros(nodeNum,nodeNum);
		
		//loop count
		int computingCount = 1;
		int loopCount = 1;
		//node and neighbor nodes
		Node node = null;
		
		boolean algorithmsRun = true;
		//Begin Algorithms
		while(algorithmsRun){
			
//			try {
//				Thread.currentThread().sleep(100);
//			}
//			catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			System.out.println(loopCount+"-----------------------------------------------------");
			
			int[] randomN = RandomTool.randomNRed(nodeNum);
			
			for (int i = 0; i < randomN.length; i++) {
				int nodeId = randomN[i];
				
				node = nodes.get(nodeId);
				
				System.out.print("    "+computingCount+", selected node is "+nodeId);
				
				if(node.isDecision()){
					if(checkReceiveAll(node)){
						System.out.println(", decision node receive all message from its neighbor.");
						System.out.println("");
						System.out.print("Algorithms End! ");
						System.out.println("Iteration number is "+loopCount+", selected number is " + computingCount+".");
						System.out.println("");
						Graph.printTree(nodes);
						algorithmsRun = false;
						break;
					}
				}
				
				//First loop: set the random node to decided node
				if (computingCount == 1){
					System.out.print(", decisionNode is "+node.getNodeId());
					node.setDecision(true);
					
					sendMessage(node);
					
					System.out.println(" ");
					computingCount++;
					continue;
				}
				
				//get state
				int nodeState = node.getState();
				
				if(nodeState==0){
					//wait
					System.out.println(", wait");
					computingCount++;
					continue;	
					
				}else if(nodeState == 1){
					//set father
					setFather(node);
					//send message to neighbor (awaken)
					sendMessage(node);
					
					//check all receive message
					if(checkReceiveAll(node)){
						responseMessage(node);
					}
					
					System.out.println(" ");	
					computingCount++;
					continue;
					
				}else if(nodeState == 2){	
					//check all receive message
					if(checkReceiveAll(node)){
						responseMessage(node);
					}else{
						System.out.print(", wait");
					}
					
					System.out.println(" ");
					computingCount++;
					continue;
					
				}else if(nodeState == 3){
					//wait
					System.out.println(", wait");
					computingCount++;
					continue;
				}
			}
			
			loopCount++;		
		}
	}
	
	/***
	 * the selected node first receive some message from its neighbor node ,
	 * send message to add of its neighbor excepted father node and change state to 2
	 * if its neighbor's state is state 0 (no wake), waken up it change state to 1
	 * @param Node Object: a selected node in this process
	 */
	public static void sendMessage(Node node){
		int sendId = node.getNodeId();
		List<Node> neighborList = node.getNeighbor();

		Node neighborNode = null;
		System.out.print(", send message to");
		for (int i = 0; i < neighborList.size(); i++) {
			neighborNode = neighborList.get(i);
			
			//If the neighbor is not father, send message
			if(node.getFatherId() != neighborNode.getNodeId() ){
				matrix.setAsInt(1,sendId,neighborNode.getNodeId());
				System.out.print(" node "+ neighborNode.getNodeId());
				
				if(neighborNode.getState()==0){
					neighborNode.setState(1);
					System.out.print("(wake up)");
				}	
			}
		}
		
		node.setState(2);
	}
	
	/***
	 * the selected node first receive some message from its neighbor node 
	 * random selected a node which send message to it as its father node 
	 * @param Node Object: a selected node in this process
	 */
	public static void setFather(Node node){
		int nodeId = node.getNodeId();	
		List<Node> neighborList = node.getNeighbor();
		
		List<Node> messageNode = new ArrayList<>();

		Node neighborNode = null;
		int messageNum = 0;
		for (int i = 0; i < neighborList.size(); i++) {
			neighborNode = neighborList.get(i);
			
			if(matrix.getAsInt(neighborNode.getNodeId(),nodeId)==1){
				messageNode.add(neighborNode);
				messageNum++;
			}
		}
		
		Node fatherNode = null;
		if (messageNum == 1) {
			fatherNode = messageNode.get(0);
			
			List<Node> children = fatherNode.getChildren();
			children.add(node);
			fatherNode.setChildren(children);
			
			node.setFatherId(fatherNode.getNodeId());
			System.out.print(", set father node "+fatherNode.getNodeId());
		} else {
			int randomNum = RandomTool.randomInt(messageNum);

			fatherNode = messageNode.get(randomNum);

			List<Node> children = fatherNode.getChildren();
			children.add(node);
			fatherNode.setChildren(children);
			
			node.setFatherId(fatherNode.getNodeId());
			System.out.print(", set father node "+fatherNode.getNodeId());
		}
	}
	
	/***
	 * check the selected node whether received all message from its neighbor excepted its father node
	 * @param boolean Object
	 */
	public static boolean checkReceiveAll(Node node){
		boolean result = false;
		
		int receiveId = node.getNodeId();
		List<Node> neighborList = node.getNeighbor();

		Node neighborNode = null;
		for (int i = 0; i < neighborList.size(); i++) {
			neighborNode = neighborList.get(i);
			
			int sendId = neighborNode.getNodeId();
			
			if(node.getFatherId() == sendId && i != neighborList.size() - 1){
				continue;
			}
			
			if(matrix.getAsInt(sendId,receiveId) != 1){
				break;	
			}
			
			if(i == neighborList.size() - 1) {
				result = true;
			}
		}	
		return result;
	}
	
	/***
	 * the selected node have received all message from its neighbor excepted father node
	 * send message to its father and change state to 3
	 * @param Node Object: a selected node in this process
	 */
	private static void responseMessage(Node node) {
		int fatherId = node.getFatherId();
		matrix.setAsInt(1,node.getNodeId(),fatherId);
		System.out.println(", send message to father node "+fatherId);
		node.setState(3);
	}

}
