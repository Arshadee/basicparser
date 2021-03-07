package io.basicparser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arshad
 */
//To be the entity class persisted to the database
//@Entity
public class ObjectTree implements Serializable{
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
    //@Column(name="id", unique=true)
    private String id;
   
    //@ManyToOne
    //@JoinColumn(name="parent")
    private ObjectTree parent;
   
    //@OneToMany(mappedBy="parent")
    private List<ObjectTree> children;
   
    private String name;

    public ObjectTree(){
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