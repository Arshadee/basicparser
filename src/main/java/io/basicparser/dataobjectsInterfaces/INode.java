package io.basicparser.dataobjectsInterfaces;

import java.util.List;

import io.basicparser.dataobjects.Node;

public interface INode<T> {
	
	 public Node<T> getParent();

	    public void setParent(Node<T> parent);

	    public String getName();

	    public void setName(String name);

	    public T getValue();
	    
	    public void setValue(T value);

	    public List<Node<T>> getChildren();

	    public List<Node<T>> getSiblings();

	    public void addNode(Node<T> node);

	    public void removeNode(Node<T> node);
}
