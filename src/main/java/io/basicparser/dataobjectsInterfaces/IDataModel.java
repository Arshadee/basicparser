package io.basicparser.dataobjectsInterfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import io.basicparser.dataobjects.Node;
import io.basicparser.dataobjects.ObjectTree;

public interface IDataModel{

	public Stack<String> getParsingStack();

	public void setParsingStack(Stack<String> parsingStack);

	public List<INode<String>> getNodeList();

	public void setNodeList(List<INode<String>> nodeList);

	public HashMap<String, INode<String>> getParsingMap();

	public void setParsingMap(HashMap<String, INode<String>> parsingMap);

	public INode<String> getRoot();

	public void setRoot(INode<String> root);

//	public IObjectTree getObjectTree();
//
//	public void setObjectTree(IObjectTree objectTree);
}
