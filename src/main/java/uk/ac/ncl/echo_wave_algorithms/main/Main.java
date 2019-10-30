package uk.ac.ncl.echo_wave_algorithms.main;


import uk.ac.ncl.echo_wave_algorithms.module.Graph;

public class Main {

	public static void main(String[] args) {
		
		/***
		 * use Graph.createStructureByInput() to create any graph by you input
		 */
//		Graph graph = Graph.createStructureByInput();
		
		/***
		 * there are three graph tha have been created by json
		 * useGraph.createStructureByJson("name");
		 * doughnutGraph
		 * normalGraph
		 * treeGraph
		 * 
		 * by pass the name to create graph
		 */
		Graph normalGraph = Graph.createStructureByJson("normalGraph");
		Graph doughnutGraph = Graph.createStructureByJson("doughnutGraph");
		Graph treeGraph = Graph.createStructureByJson("treeGraph");
			
		//Execute Algorithms
		Algorithms.algorithms(normalGraph);
	}
}
