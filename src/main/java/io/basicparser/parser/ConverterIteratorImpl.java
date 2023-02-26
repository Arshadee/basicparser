package io.basicparser.parser;

import java.util.Map;
import java.util.Stack;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjects.ObjectTree;
import io.basicparser.dataobjectsInterfaces.INode;
import io.basicparser.dataobjectsInterfaces.IObjectTree;
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
	public void mapTreeToTreeObj(String name, IObjectTree objectTree, Map<String, INode<String>> treeMap) {

		Stack<INode<String>> stack = new Stack<>();
		INode<String> node = treeMap.get(name);
		stack.push(node);
		Stack<IObjectTree> stackObjTree = new Stack<>();
		objectTree.setName(node.getName());
		objectTree.setId(node.getName());
		stackObjTree.push(objectTree);

		while (!stack.isEmpty()) {
			node = stack.pop();
			objectTree = stackObjTree.pop();
			node = treeMap.get(node.getName());

			for (int i = 0; i < node.getChildren().size(); i++) {
				Node<String> child = node.getChildren().get(i);
				stack.push(child);
				ObjectTree n = new ObjectTree();
				n.setName(child.getName());
				n.setId(child.getName());
				n.setParent(objectTree);
				objectTree.getChildren().add(n);
				stackObjTree.push(n);
			}
		}
	}
}