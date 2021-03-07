package io.basicparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arshad
 */
public class Node<T> {
    private Node<T> parent;
    private String name;
    private T value;
    private List<Node<T>> children = new ArrayList<>();

    public Node(){}
   
    public Node(String name){
      this.name = name;

    }

    public Node(String name,T value){
      this.name = name;
      this.value = value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
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
        return "name "+name+ " parent "+parent;
    }

    public List<Node<T>> getChildren(){
        return children;
    }

    public List<Node<T>> getSiblings(){
        List<Node<T>> siblings = parent.children;
        siblings.remove(this);
        return siblings;
    }

    public void addNode(Node<T> node){
        children.add(node);
        node.parent = this;
    }

    public void removeNode(Node<T> node){
        children.remove(node);
        node.parent = null;
    }

}