package com.example.demo1.Factories;

import com.example.demo1.Graph.GraphRepresentation;
import com.example.demo1.Graph.Node;

import java.util.List;
import java.util.Map;

/**
 * @author Cristescu Cristian
 * This class is used to generate the graph that needs a graphic representation.
 */
public class GraphFactory {
    /**
     * This flag indicates the nature of the graph.
     */
    private boolean isDirected = false;

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    /**
     * This method generates the graph.
     * @param edges this is the adjacency matrix of the graph.
     * @return returns a graph representation, created using the adjacency matrix of the graph.
     */
    public GraphRepresentation getGraph(Map<Node, List<Node>> edges){
        GraphRepresentation graphRepresentation = new GraphRepresentation(edges.keySet().stream().toList(), isDirected);
        for(Node n : edges.keySet()){
            System.out.println(n.toString() + "-------" + edges.get(n).toString());
        }
        for(Node node : edges.keySet()){
           if(edges.get(node).size()>0){
               for(Node n : edges.get(node)){
                   graphRepresentation.addEdge(node,n);
               }
           }
        }
        return graphRepresentation;
    }
}