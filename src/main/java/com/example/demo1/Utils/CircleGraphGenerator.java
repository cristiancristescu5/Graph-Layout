package com.example.demo1.Utils;

import com.example.demo1.Graph.GraphRepresentation;
import com.example.demo1.Graph.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Cristescu Cristian
 * This class is used to generate an instance of a graph that will be represented using a circle layout algorithm.
 */
public class CircleGraphGenerator {
    /**
     * This method is used to generate the graph.
     * @return returns the random generated graph.
     */
    public static GraphRepresentation generateGraph(){
        List<Node> nodes = new ArrayList<>();
        for(int i = 0; i < 50; i ++){
            nodes.add(new Node(Integer.toString(i), Color.RED));
        }
        GraphRepresentation graphRepresentation = new GraphRepresentation(nodes,false);

        Random random = new Random();
        for(int i = 0 ; i < 400; i++){
            int y = random.nextInt(0, 49);
            int x = random.nextInt(0, 49);
            Node from = findNodeByInfo(Integer.toString(x), nodes);
            Node to = findNodeByInfo(Integer.toString(y), nodes);
            if(!to.getNode().equals(from.getNode())){
                graphRepresentation.addEdge(from ,to);
            }
        }
        return graphRepresentation;
    }
    private static Node findNodeByInfo(String number, List<Node> nodes){
        for(Node node : nodes){
            if(node.getNode().equals(number)){
                return node;
            }
        }
        return null;
    }
}
