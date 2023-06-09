package com.example.demo1.Utils;

import com.example.demo1.Graph.GraphRepresentation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristescu Cristian
 * This Singleton class is used to generate the graphic representation for every node in a graph.
 */
public class CircleGenerator {
    /**
     * This method is used to generate the representation for every node in a graph.
     * @param graphRepresentation this is the graph that needs a representation for it's nodes.
     * @return returns a list of circles representing the graphic representation for every node.
     */
    public static List<Circle> getCircles(GraphRepresentation graphRepresentation){
        List<Circle> circles = new ArrayList<>();
        int[] vertices = graphRepresentation.getGraph().vertices();
        for (int i = 0; i < vertices.length; i++) {
            Circle circle = new Circle();
            circle.setCenterX(graphRepresentation.getGraph().getVertexLabel(vertices[i]).getPoint2D().getX());
            circle.setCenterY(graphRepresentation.getGraph().getVertexLabel(vertices[i]).getPoint2D().getY());
            circle.setRadius(10);
            circle.setAccessibleText(graphRepresentation.getGraph().getVertexLabel(vertices[i]).getNode());
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            graphRepresentation.getGraph().getVertexLabel(vertices[i]).setCircle(circle);
            circles.add(circle);
        }
        return circles;

    }
}
