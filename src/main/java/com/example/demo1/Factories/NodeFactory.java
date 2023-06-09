package com.example.demo1.Factories;

import com.example.demo1.Graph.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristescu Cristian
 * This class is used to generate the nodes of the graph, processing the edges written in the text area
 */
public class NodeFactory {
    /**
     * This is the string containing the edges
     */
    private String s;
    /**
     * This is the color of the nodes.
     */
    private Color color = Color.BLACK;
    public String getS() {
        return s;
    }
    public void setNodes(String nodes) {
        this.s = nodes;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method processes the String s and generates the list of nodes of the graph.
     * @return returns a list of Nodes, representing the nodes of the graph
     */
    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        if (color == null) {
            color = Color.BLACK;
        }
        String[] lines = s.split("\n");
        for (String line : lines) {
            String[] columns = line.split("-");
            if (columns.length != 2) {
                throw new IllegalStateException("Invalid edge label!");
            }
            for (String column : columns) {
                if (!containsNode(column, nodes)){
                    nodes.add(new Node(column, color));
                }
            }
        }
        return nodes;
    }
    private boolean containsNode(String node, List<Node> nodes){
        for(Node n : nodes){
            if(n.getNode().equals(node)){
                return  true;
            }
        }
        return false;
    }
}
