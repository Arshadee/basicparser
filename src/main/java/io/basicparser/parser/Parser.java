package io.basicparser.parser;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.dataobjects.Node;
import io.basicparser.parser.interfaces.IParser;

/**
 * Utility Class that houses methods used to Parse the string expression
 * 
 * @author Arshad
 * 
 */
public class Parser implements IParser{
 
    public Parser() {}
       
    /**
     * This method builds a object map from the stack used per element
     * off node String elements in the dataModel parsed from the expressions
     * @param dataModel
     */
    private DataModel buildNodeStr(DataModel dataModel) {
        String tmp;
        String back = dataModel.getParsingStack().pop();
        String front;
        try {
            front = dataModel.getParsingStack().peek();
        } catch (EmptyStackException ep) {
            front = "null";
        }

        if (!front.equals("null")) {
            tmp = front + "->" + back;
        } else {
            tmp = back;
            dataModel.setRoot(new Node<String>(back));
        }
        buildMap(tmp,dataModel.getParsingMap());
        return dataModel;
    }

    /**
     * This method adds node to the object map
     * @param element
     * @param map
     */
    private static void addMapElement(String element,
            Map<String, Node<String>> map) {
        if (map.get(element) == null) {
           // map.put(element, new Node<String>(element));
        	map.put(element, new Node<String>(element,element));
        }
    }

    /**
     * This method splits string input into key and value pair
     * and adds it to the object map of(key) parent and (value) children
     * @param expression
     * @param parsingMap
     */
    private static void buildMap(String expression,
        HashMap<String, Node<String>> parsingMap) {
        StringTokenizer st = new StringTokenizer(expression, "->");
        String tmp1 = st.nextElement().toString();
        String tmp2 = "";
        addMapElement(tmp1, parsingMap);
        if (expression.contains("->")) {
            tmp2 = st.nextElement().toString();
            parsingMap.get(tmp1).getChildren().add(new Node<>(tmp2,tmp1));
            addMapElement(tmp2, parsingMap);
        }
    }
   

    /**
     * Overridden from IParser Interface
     *  
     * This method builds the stack in the dataModel from the expression string
     * and uses the stack to build a object map of (key) parent and (value) children 
     * @param String expression
     * @return DataModel instance
     */
    @Override
    public DataModel parse(String expression) {
    	DataModel dataModel = new DataModel();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ')') {
            	dataModel = buildNodeStr(dataModel);
            }
            if ((expression.charAt(i) != '(') && (expression.charAt(i) != ')')) {
            	dataModel.getParsingStack().push(expression.charAt(i) + "");
            }
        }
        return dataModel;
    }
    
    /**
     * Overridden and overloaded from IParser Interface
     * 
     * This method builds the stack in the dataModel from the expression string
     * and uses the stack to build a object map of (key) parent and (value) children 
     * @param String List expression
     * @return DataModel instance
     */
    @Override
    public DataModel parse(List<String> expression) {
    	DataModel dataModel = new DataModel();
        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals(")")){
            	dataModel = buildNodeStr(dataModel);
            }
            if ((!expression.get(i).equals("(")) && (!expression.get(i).equals(")"))) {
            	dataModel.getParsingStack().push(expression.get(i));
            }
        }
        return dataModel;
    }


}
