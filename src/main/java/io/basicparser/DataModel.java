package io.basicparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Arshad
 */
public class DataModel {
    private Stack<String> parsingStack = new Stack<>();
    private List<Node<String>> nodeList = new ArrayList<>();
    private HashMap<String,Node<String>> parsingMap = new HashMap<>();
    private Node<String> root = new Node<>();
    private ObjectTree objectTree = new ObjectTree();
    
	public Stack<String> getParsingStack() {
		return parsingStack;
	}
	public void setParsingStack(Stack<String> parsingStack) {
		this.parsingStack = parsingStack;
	}

	public List<Node<String>> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<Node<String>> nodeList) {
		this.nodeList = nodeList;
	}
	public HashMap<String, Node<String>> getParsingMap() {
		return parsingMap;
	}
	public void setParsingMap(HashMap<String, Node<String>> parsingMap) {
		this.parsingMap = parsingMap;
	}
	public Node<String> getRoot() {
		return root;
	}
	public void setRoot(Node<String> root) {
		this.root = root;
	}
	public ObjectTree getObjectTree() {
		return objectTree;
	}
	public void setObjectTree(ObjectTree objectTree) {
		this.objectTree = objectTree;
	}

   
}