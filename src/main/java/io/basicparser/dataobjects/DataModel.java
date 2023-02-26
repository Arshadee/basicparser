package io.basicparser.dataobjects;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import io.basicparser.dataobjectsInterfaces.*;

/**
 * Model class used to store parsed data
 * @author Arshad
 */
public class DataModel implements IDataModel{
	
    private Stack<String> parsingStack = new Stack<>();
    private List<INode<String>> nodeList = new ArrayList<>();
    private HashMap<String,INode<String>> parsingMap = new HashMap<>();
    private INode<String> root = new Node<>();
    private IObjectTree objectTree = new ObjectTree();
    
	public Stack<String> getParsingStack() {
		return parsingStack;
	}
	public void setParsingStack(Stack<String> parsingStack) {
		this.parsingStack = parsingStack;
	}

	public List<INode<String>> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<INode<String>> nodeList) {
		this.nodeList = nodeList;
	}
	public HashMap<String, INode<String>> getParsingMap() {
		return parsingMap;
	}
	public void setParsingMap(HashMap<String, INode<String>> parsingMap) {
		this.parsingMap = parsingMap;
	}
	public INode<String> getRoot() {
		return root;
	}
	public void setRoot(INode<String> root) {
		this.root = root;
	}
	public IObjectTree getObjectTree() {
		return objectTree;
	}
	public void setObjectTree(IObjectTree objectTree) {
		this.objectTree = objectTree;
	}

   
}