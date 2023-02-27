package io.basicparser.dataobjectsInterfaces;

import java.util.List;

import io.basicparser.dataobjects.Node;

public interface INode<T> {
	
	 public INode<T> getParent();

	    public void setParent(INode<T> parent);

	    public String getName();

	    public void setName(String name);

	    public T getValue();
	    
	    public void setValue(T value);

	    public List<INode<T>> getChildren();

	    public List<INode<T>> getSiblings();

	    public void addNode(INode<T> node);

	    public void removeNode(INode<T> node);
}
