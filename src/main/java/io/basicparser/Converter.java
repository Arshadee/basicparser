package io.basicparser;

import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Arshad
 */
public class Converter {
    

  /* 
    static Map<String,NObj> convertListToMap(List<Node> oes){
        Map<String,NObj> treeMap = oes.stream().parallel().collect(
            Collectors.toMap(Node::getName, p ->{
                NObj n = new NObj();
                n.setOe(p);
                List<Node> tmpOes = (List<Node>) oes.stream().
                    parallel().filter(x -> x.getName().equals(p.getName())).
                    collect(Collectors.toList());
                n.setOes(tmpOes);
                return n;
                }           
            )
        );
        return treeMap;
    }
   */
    
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
        objectTree.setName(node.getName()); //eg Node Data field
       
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
            
       /** old DFS
        for(int i =0; i<node.children.size();i++){
            //Populate (TreeObj)  node Id and node parent and node Children field
            ObjTree n = new ObjTree();
            n.setParent(objTree);
            objTree.getChildren().add(n);
            Node<String> nodeElement = (Node<String>)(node.children.get(i));
            mapTreeToTreeObj(nnodeElement.getName(),name,objTree.getChildren().get(i),treeMap);
        }
        **/
    }

}
