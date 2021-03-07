package io.basicparser;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Arshad
 */
public class Display {
   
    /**
     * This method displays list of node from expression string
     * @param alist
     */
    public static void displayNodelist(List<Node<String>> alist){
        for(int i=0;i<alist.size();i++){
            System.out.println(alist.get(i));
        }
    }
   
    /**
     * This method displays the object map
     * @param parsingMap
     */
    public static void display(HashMap<String,Node<String>> parsingMap){
        parsingMap.keySet().stream().forEach(k ->System.out.println(k+"|"+parsingMap.get(k).getChildren()));
    }
   
    /**
     * This method displays Tree Object created from the expression string
     * using the Depth First Search algorithm
     * @param objectTree
     * @param tab
     */
    public static void display(ObjectTree objectTree,String tab){    
          System.out.println(tab+objectTree.getId()+" ["+objectTree.getName()+"]");
          // traverse children
           int childCount = objectTree.getChildren().size();      

            for (int i = 0; i < childCount; i++) {
            	ObjectTree child = objectTree.getChildren().get(i);
                //display(child,tab.replaceAll(">","|")+"->");
                display(child,tab+"|--");
               
            }
 
    }
    
    /**
     * This method displays Tree Object created from the expression string
     * using the Depth First Search algorithm
     * @param objectTree
     * @param tab
     * @param result
     * @return
     */
    public static StringBuilder display(ObjectTree objectTree,String tab, StringBuilder result){    
    	result.append(tab+objectTree.getId()+"["+objectTree.getName()+"]\n");
        // traverse children
         int childCount = objectTree.getChildren().size();      

          for (int i = 0; i < childCount; i++) {
          	ObjectTree child = objectTree.getChildren().get(i);
              display(child,tab+"|--",result);
             
          }
       return result;
  }
}