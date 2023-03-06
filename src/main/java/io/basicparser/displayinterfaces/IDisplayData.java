package io.basicparser.displayinterfaces;

import java.util.HashMap;
import java.util.List;

import io.basicparser.dataobjectsInterfaces.INode;

public interface IDisplayData {
	
	public void displayNodelist(List<INode<String>> alist);

	public void display(HashMap<String, INode<String>> parsingMap);

	public StringBuilder display(INode<String> treeNode, String tab, StringBuilder result);
	
}
