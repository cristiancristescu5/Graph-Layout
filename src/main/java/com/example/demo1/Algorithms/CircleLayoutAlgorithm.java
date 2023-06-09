package com.example.demo1.Algorithms;

import com.example.demo1.Graph.GraphRepresentation;
import javafx.geometry.Point2D;

/**
 * @author Cristescu Cristian
 * This class implements a circle layout algorithm for graph visualisation.
 */
public class CircleLayoutAlgorithm {
    /**
     * This method applies the circle layout algorithm to the give graph.
     * @param graphRepresentation the graph to be represented.
     * @return the graph with the modified position of nodes.
     */
    public GraphRepresentation circleLayoutAlgorithm(GraphRepresentation graphRepresentation){
        double radius;
        if(graphRepresentation.getGraph().vertices().length<=50) {
            radius = 200;
        }else{
            radius = 300;
        }
        int numberOfNodes = graphRepresentation.getNodes().size();
        double angle = 2 * Math.PI/numberOfNodes;
        int i = 0;
        int[] vertices = graphRepresentation.getGraph().vertices();
        for(int j = 0 ; j < vertices.length ; j++){
            double x = Math.cos(i*angle)*radius+500;
            double y = Math.sin(i*angle)*radius+300;
            graphRepresentation.getGraph().getVertexLabel(vertices[j]).setPoint2D(new Point2D(x,y));
            i++;
        }
        return graphRepresentation;
    }
}
