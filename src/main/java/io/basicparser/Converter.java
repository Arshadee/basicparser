package io.basicparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Utility class - houses method(s) used to convert parsed string expression
 * into a tree object
 * @author Arshad
 */
public class Converter {
    
    
    /**
     * This method creates an object tree from the an object map (key node , value children)
     * it uses the Depth First Search algorithm
     * @param name
     * @param pid
     * @param objectTree
     * @param treeMap
     */
    public static void mapTreeToTreeObj(String name,String pid,
    		ObjectTree objectTree, Map<String,Node<String>> treeMap){
        //Populate object tree with node Id and node data fields
        //from object map
        Node<String> node = treeMap.get(name);
        objectTree.setId(node.getName());
        //objectTree.setName(node.getName()); //eg Node Data field
        objectTree.setName(node.getValue());
       
        node.getChildren().parallelStream().forEachOrdered( c -> {
        	ObjectTree n = new ObjectTree();
                n.setParent(objectTree);
                objectTree.getChildren().add(n);
				Node<String> nodeElement =(Node<String>)(c);
                Optional<ObjectTree> cc = objectTree.getChildren().parallelStream()
                                    .filter(d -> d.getName() == null)
                                    .findFirst();
                mapTreeToTreeObj(nodeElement.getName(),name,cc.get(),treeMap);
            });
            
    }
    
    public static List<String> mapToStringTokenList(String expression){
    	expression = expression.replace("(", " ( ").replace(")", " ) ");
	      List<String> tokens = new ArrayList<>();
	      StringTokenizer tokenizer = new StringTokenizer(expression, " ");
	      while (tokenizer.hasMoreElements()) {
	     	 String tok = tokenizer.nextToken();
	         tokens.add(tok);
	      }
	      return tokens;
    }

}
