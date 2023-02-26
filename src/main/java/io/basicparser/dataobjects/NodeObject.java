package io.basicparser.dataobjects;

import java.util.ArrayList;
import java.util.List;

import io.basicparser.dataobjectsInterfaces.INode;
import io.basicparser.dataobjectsInterfaces.INodeObject;

/**
 * Model class - specifically used to store node relation data parsed from string expression
 * @author Arshad
 */
public class NodeObject implements INodeObject{
    private INode<String> objectElement = new Node<>();
    private List<INode<String>> objectElements = new ArrayList<>();
    
    public NodeObject() {
    }

    @Override
    public INode<String> getObjectElement() {
        return objectElement;
    }

    @Override
    public void setObjectElement(INode<String> objectElement) {
        this.objectElement = objectElement;
    }

    @Override
    public List<INode<String>> getObjectElements() {
        return objectElements;
    }

    @Override
    public void setObjectElements(List<INode<String>> objectElements) {
        this.objectElements = objectElements;
    }
   
}