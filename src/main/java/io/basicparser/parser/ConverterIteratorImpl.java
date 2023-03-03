package io.basicparser.parser;

import java.util.Map;
import java.util.Stack;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjectsInterfaces.INode;
import io.basicparser.parserinterfaces.IConverter;

public class ConverterIteratorImpl implements IConverter{
	

	
	
	private final static String ALGORITH_NAME="ITERATIVE DFS";
	
	/**
	 * Overridden from IConverter Interface
	 * 
	 * This is a simple getter method that return the thiIs implementation
	 * name
	 * 
	 * return algorithm name from static constant
	 */
	@Override
	public String getAlgorthmName() {
		return ALGORITH_NAME;
	}
	

	/**
	 * Overridden from IConverter Interface
	 * 
	 * This method creates an object tree from the an object map (key node , value
	 * children) it uses the Depth First Search algorithm - Iterative implmentation
	 * 
	 * @param name
	 * @param objectTree
	 * @param treeMap
	 */
	@Override
	public void mapTreeToTreeObj(String name, INode<String> treeNode, Map<String, INode<String>> treeMap) {

		Stack<INode<String>> stack = new Stack<>();
		INode<String> node = treeMap.get(name);
		stack.push(node);
		Stack<INode<String>> stackTreeNode = new Stack<>();
		treeNode.setName(node.getName());
		treeNode.setValue(node.getValue());
		stackTreeNode.push(treeNode);
		

		while (!stack.isEmpty()) {
			node = stack.pop();
			treeNode = stackTreeNode.pop();
			node = treeMap.get(node.getName());

			for (int i = 0; i < node.getChildren().size(); i++) {
				INode<String> child = node.getChildren().get(i);
				stack.push(child);
				INode<String> n = new Node<>();
				n.setName(child.getName());
				n.setValue(child.getValue());
				n.setParent(treeNode);
				treeNode.getChildren().add(n);
				stackTreeNode.push(n);
			}
		}
	}
}
