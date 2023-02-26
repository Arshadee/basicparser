package io.basicparser.dataobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.basicparser.dataobjectsInterfaces.IObjectTree;

/**
 * Model Object - Specifically used to store tree object from parsed string expression
 * @author Arshad
 */

public class ObjectTree implements IObjectTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private IObjectTree parent;
	private List<IObjectTree> children;
	private String name;

	public ObjectTree() {
		id = "";
		children = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IObjectTree getParent() {
		return parent;
	}

	public void setParent(IObjectTree parent) {
		this.parent = parent;
	}

	public List<IObjectTree> getChildren() {
		return children;
	}

	public void setChildren(List<IObjectTree> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}