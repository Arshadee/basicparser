package io.basicparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjects.ObjectTree;
import io.basicparser.dataobjectsInterfaces.INode;
import io.basicparser.dataobjectsInterfaces.IObjectTree;
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
	public void mapTreeToTreeObj(String name, IObjectTree objectTree,
			Map<String, INode<String>> treeMap) {
		
	   /* Populate object tree with node Id and node data fields
		* from object map
		*/
		INode<String> node = treeMap.get(name);
		objectTree.setId(node.getName());
		objectTree.setName(node.getValue());

		node.getChildren().parallelStream().forEachOrdered(nodeChild -> {
			ObjectTree n = new ObjectTree();
			n.setParent(objectTree);
			objectTree.getChildren().add(n);
			Node<String> nodeElement = (Node<String>) (nodeChild);
			Optional<IObjectTree> tree = objectTree.getChildren()
					.parallelStream()
					.filter(d -> d.getName() == null)
					.findFirst();
			mapTreeToTreeObj(nodeElement.getName(), tree.get(), treeMap);
		});
	}

	


	
}
