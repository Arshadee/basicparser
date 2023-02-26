package io.basicparser.dataobjectsInterfaces;

import java.io.Serializable;
import java.util.List;

import io.basicparser.dataobjects.ObjectTree;

public interface IObjectTree extends Serializable{
	
	public String getId();

	public void setId(String id);

	public IObjectTree getParent();

	public void setParent(IObjectTree parent);

	public List<IObjectTree> getChildren();

	public void setChildren(List<IObjectTree> children);

	public String getName();

	public void setName(String name);

}
