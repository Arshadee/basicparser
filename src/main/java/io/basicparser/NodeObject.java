package io.basicparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class - specifically used to store node relation data parsed from string expression
 * @author Arshad
 */
public class NodeObject {
    private Node<String> objectElement = new Node<>();
    private List<Node<String>> objectElements = new ArrayList<>();
    
    public NodeObject() {
    }

    public Node<String> getObjectElement() {
        return objectElement;
    }

    public void setObjectElement(Node<String> objectElement) {
        this.objectElement = objectElement;
    }

    public List<Node<String>> getObjectElements() {
        return objectElements;
    }

    public void setObjectElements(List<Node<String>> objectElements) {
        this.objectElements = objectElements;
    }
   
   
   
}