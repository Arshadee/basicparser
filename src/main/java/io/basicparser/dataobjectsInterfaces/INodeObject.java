package io.basicparser.dataobjectsInterfaces;

import java.util.List;

import io.basicparser.dataobjects.Node;

public interface INodeObject {

	public INode<String> getObjectElement();

	public void setObjectElement(INode<String> objectElement);

	public List<INode<String>> getObjectElements();

	public void setObjectElements(List<INode<String>> objectElements);
}
