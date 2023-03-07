package io.basicparser.dataobjects;

import java.util.ArrayList;
import java.util.List;

import io.basicparser.dataobjectsInterfaces.INode;

/**
 * Model class - specifically used to store node data parsed from string expression
 * @author Arshad
 */
public class Node<T> implements INode<T>{
    private INode<T> parent;
    private String name;
    private T value;
    private List<INode<T>> children = new ArrayList<>();

    public Node(){}
   
    public Node(String name){
      this.name = name;

    }

    public Node(String name,T value){
      this.name = name;
      this.value = value;
    }

    public INode<T> getParent() {
        return parent;
    }

    public void setParent(INode<T> parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
    	return value;
    }
    
    public void setValue(T value){
        this.value = value;
    }

    @Override
    public String toString(){
        //return "name "+name+ " parent "+parent;
        String nodeString = name+"(";
        for(INode<T> child : children){
            nodeString=nodeString+child;
        }
        nodeString=nodeString+")";
        return nodeString;
    }

    public List<INode<T>> getChildren(){
        return children;
    }

    public List<INode<T>> getSiblings(){
        List<INode<T>> siblings = parent.getChildren();
        siblings.remove(this);
        return siblings;
    }

    public void addNode(INode<T> node){
        children.add(node);
        node.setParent(this);
    }

    public void removeNode(INode<T> node){
        children.remove(node);
        node.setParent(null);
    }

}