package uk.ac.ncl.echo_wave_algorithms.module;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int nodeId;
	
	private List<Node> neighbor = new ArrayList<Node>();;
	
	private List<Node> children = new ArrayList<Node>();
	
	private int fatherId = -1;
	
	private boolean decision = false;
	
	/***
	 * state 0: no wake up
	 * state 1: have received a message from its neighbor (wake up)
	 * state 2: have sent message to neighbor excepted its father
	 * state 3: have received all message from its neighbor excepted its father and send message to its father
	 */
	private int state = 0;

	public Node(int nodeId) {	
		this.nodeId = nodeId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public List<Node> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(List<Node> neighbor) {
		this.neighbor = neighbor;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isDecision() {
		return decision;
	}

	public void setDecision(boolean decision) {
		this.decision = decision;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	
}
