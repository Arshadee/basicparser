package io.basicparser.parser;

import java.util.Map;
import java.util.Optional;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjectsInterfaces.INode;
import io.basicparser.parserinterfaces.IConverter;

/**
 * Utility class - houses method(s) used to convert parsed string expression
 * into a tree object
 * 
 * @author Arshad
 */
public class ConverterRecursiveImpl implements IConverter{

	private final static String ALGORITH_NAME="RECURSIVE DFS";
	
	/**
	 * Overrided from IConverter Interface
	 * 
	 * This is a simple getter method that return the this implementation
	 * name
	 * 
	 * return algorithm name from static constant
	 */
	@Override
	public String getAlgorthmName() {
		return ALGORITH_NAME;
	}
	
	/**
	 * Overrided from IConverter Interface
	 * 
	 * This method creates an object tree from the an object map (key node , value
	 * children) it uses the Depth First Search algorithm - Recursion Implementation
	 * 
	 * @param name
	 * @param objectTree
	 * @param treeMap
	 */
	@Override
	public void mapTreeToTreeObj(String name, INode<String> treeNode, 
			Map<String, INode<String>> treeMap) {

		
	   /* Populate object tree with node Id and node data fields
		* from object map
		*/
		INode<String> node = treeMap.get(name);
		treeNode.setName(node.getName());
		treeNode.setValue(node.getValue());

		node.getChildren().parallelStream().forEachOrdered(nodeChild -> {
			INode<String> n = new Node<>();
			n.setParent(treeNode);
			treeNode.getChildren().add(n);
			Node<String> nodeElement = (Node<String>) (nodeChild);
			Optional<INode<String>> tree = treeNode.getChildren()
					.parallelStream()
					.filter(d -> d.getName() == null)
					.findFirst();
			mapTreeToTreeObj(nodeElement.getName(), tree.get(), treeMap);
		});
	}
	
}
