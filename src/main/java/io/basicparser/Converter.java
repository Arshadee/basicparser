package io.basicparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Utility class - houses method(s) used to convert parsed string expression
 * into a tree object
 * 
 * @author Arshad
 */
public class Converter {

	/**
	 * This method creates an object tree from the an object map (key node , value
	 * children) it uses the Depth First Search algorithm
	 * 
	 * @param name
	 * @param pid
	 * @param objectTree
	 * @param treeMap
	 */
	public static void mapTreeToTreeObj(String name, String pid, ObjectTree objectTree,
			Map<String, Node<String>> treeMap) {
		// Populate object tree with node Id and node data fields
		// from object map
		Node<String> node = treeMap.get(name);
		objectTree.setId(node.getName());
		// objectTree.setName(node.getName()); //eg Node Data field
		objectTree.setName(node.getValue());

		node.getChildren().parallelStream().forEachOrdered(c -> {
			ObjectTree n = new ObjectTree();
			n.setParent(objectTree);
			objectTree.getChildren().add(n);
			Node<String> nodeElement = (Node<String>) (c);
			Optional<ObjectTree> cc = objectTree.getChildren().parallelStream().filter(d -> d.getName() == null)
					.findFirst();
			mapTreeToTreeObj(nodeElement.getName(), name, cc.get(), treeMap);
		});

	}

	public static List<String> mapToStringTokenList(String expression) {
		expression = expression.replace("(", " ( ").replace(")", " ) ");
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(expression, " ");
		while (tokenizer.hasMoreElements()) {
			String tok = tokenizer.nextToken();
			tokens.add(tok);
		}
		return tokens;
	}

	public static void mapTreeToTreeObjItr(String name, String pid, ObjectTree objTree, Map<String, Node<String>> treeMap) {

		Stack<Node<String>> stack = new Stack();
		Node<String> node = treeMap.get(name);
		stack.push(node);
		Stack<ObjectTree> stackObjTree = new Stack();
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
