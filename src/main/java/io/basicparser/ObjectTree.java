package io.basicparser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model Object - Specifically used to store tree object from parsed string expression
 * @author Arshad
 */

public class ObjectTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private ObjectTree parent;
	private List<ObjectTree> children;
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

	public ObjectTree getParent() {
		return parent;
	}

	public void setParent(ObjectTree parent) {
		this.parent = parent;
	}

	public List<ObjectTree> getChildren() {
		return children;
	}

	public void setChildren(List<ObjectTree> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}