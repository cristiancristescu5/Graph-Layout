package com.example.demo1.Factories;

import com.example.demo1.Graph.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cristescu Cristian
 * This class is used to generate the edges of the graph.
 */
public class EdgeFactory {
    /**
     * The String s represents the edges written in the text area.
     */
    private String s;
    /**
     * This is the color of every edge.
     */
    private Color color = Color.BLACK;
    public void setS(String s) {
        this.s = s;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method takes the nodes of the graph, processes the String s and generates the edges of the graph.
     * @param nodes the list of nodes of the graph.
     * @return returns a Map representing the matrix of adjacency of the graph.
     */
    public Map<Node, List<Node>> getEdges(List<Node> nodes){
        Map<Node, List<Node>> edges = new HashMap<>();
        for(Node node : nodes){
            edges.put(node, new ArrayList<>());
        }
        String[]lines = s.split("\n");
        for(String line : lines){
            String[] row = line.split("-");
            if(row.length!=2){
                throw new IllegalStateException("Invalid edge format!");
            }
            Node from = getNodeByInfo(row[0], nodes);
            Node to = getNodeByInfo(row[1], nodes);
            edges.get(from).add(to);
            edges.get(to).add(from);
        }
        return edges;
    }
    private Node getNodeByInfo(String node, List<Node>nodes){
        for(Node n : nodes){
            if(n.getNode().equals(node)){
                return n;
            }
        }
        return null;
    }

}
