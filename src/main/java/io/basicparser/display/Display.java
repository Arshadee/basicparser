package io.basicparser.display;

import java.util.HashMap;
import java.util.List;

import io.basicparser.dataobjectsInterfaces.INode;

/**
 * View class - this class contains logic to display input and results
 * 
 * @author Arshad
 */
public class Display {
   
	/**
	 * This method displays list of node from expression string
	 * 
	 * @param a List
	 */
	public static void displayNodelist(List<INode<String>> alist) {
		for (int i = 0; i < alist.size(); i++) {
			System.out.println(alist.get(i));
		}
	}

	/**
	 * This method displays the object map
	 * 
	 * @param parsingMap
	 */
	public static void display(HashMap<String, INode<String>> parsingMap) {
		parsingMap.keySet().stream().forEach(k -> System.out.println(k + "|" + parsingMap.get(k).getChildren()));
	}

	/**
	 * This method displays Tree Object created from the expression string using the
	 * Depth First Search algorithm, overloaded method
	 * 
	 * @param objectTree
	 * @param tab
	 */
//	public static void display(IObjectTree objectTree, String tab) {
//		System.out.println(tab + objectTree.getId() + " [" + objectTree.getName() + "]");
//
//		// traverse children
//		int childCount = objectTree.getChildren().size();
//
//		for (int i = 0; i < childCount; i++) {
//			IObjectTree child = objectTree.getChildren().get(i);
//			// display(child,tab.replaceAll(">","|")+"->");
//			display(child, tab + "|--");
//		}
//	}

	/**
	 * This method displays Tree Object created from the expression string using the
	 * Depth First Search algorithm, overloaded method
	 * 
	 * @param objectTree
	 * @param tab
	 * @param result
	 * @return
	 */
	public static StringBuilder display(INode<String> treeNode, String tab, StringBuilder result) {
		result.append(tab + treeNode.getName() + "[" + treeNode.getValue() + "]\n");
		// traverse children
		int childCount = treeNode.getChildren().size();

		for (int i = 0; i < childCount; i++) {
			INode<String> child = treeNode.getChildren().get(i);
			display(child, tab + "|--", result);
		}
		return result;
	}
}