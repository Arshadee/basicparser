package io.basicparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjects.ObjectTree;
import io.basicparser.parser.interfaces.IConverter;

public class ConverterIteratorImpl implements IConverter{
	


	/**
	 * Overrided from IConverter Interface
	 * 
	 * This method creates an object tree from the an object map (key node , value
	 * children) it uses the Depth First Search algorithm - Iterative implmentation
	 * 
	 * @param name
	 * @param objectTree
	 * @param treeMap
	 */
	@Override
	public void mapTreeToTreeObj(String name, ObjectTree objTree,
			Map<String, Node<String>> treeMap) {

		Stack<Node<String>> stack = new Stack<>();
		Node<String> node = treeMap.get(name);
		stack.push(node);
		Stack<ObjectTree> stackObjTree = new Stack<>();
		objTree.setName(node.getName());
		objTree.setId(node.getName());
		stackObjTree.push(objTree);

		while (!stack.isEmpty()) {
			node = stack.pop();
			objTree = stackObjTree.pop();
			node = treeMap.get(node.getName());

			for (int i = 0; i < node.getChildren().size(); i++) {
				Node<String> child = node.getChildren().get(i);
				stack.push(child);
				ObjectTree n = new ObjectTree();
				n.setName(child.getName());
				n.setId(child.getName());
				n.setParent(objTree);
				objTree.getChildren().add(n);
				stackObjTree.push(n);
			}
		}
	}
	
}
