package com.example.demo1.Algorithms;


import com.example.demo1.Graph.GraphRepresentation;
import com.example.demo1.Graph.Node;
import javafx.geometry.Point2D;

import org.graph4j.Edge;

import java.util.Random;

/**
 * @author Cristescu Cristian
 * This class represents the implementation of a force directed algorithm for drawing graphs.
 * This algorithm is based on physics: there is an attractive force based on Hooke's law that attract pairs of
 * endpoint of the graph's edges toward eachother, while simultanoeously repulsive forces are used to separate all pairs of nodes.
 */
public class ForceDirected {

    private GraphRepresentation graphRepresentation;

    public ForceDirected(GraphRepresentation graphRepresentation) {
        this.graphRepresentation = graphRepresentation;
    }

    /**
     * This method implements the force directed algorithm.
     * @param iterations the number of iterations that the algorithm will perform.
     * @param attraction the attraction force of the nodes
     */
    public void forceDirected(int iterations, double attraction) {
        double scale = 10000;
        Random random = new Random();
        //Assigns a random position for every node
        for (int i = 0; i < graphRepresentation.getGraph().vertices().length; i++) {
            double x = random.nextDouble() * 990;
            double y = random.nextDouble() * 550;
            graphRepresentation.getNodeByIndex(i).setPoint2D(new Point2D(x, y));
        }
        //The iterations of the algorithm
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < graphRepresentation.getGraph().vertices().length; j++) {
                Point2D coord = graphRepresentation.getNodeByIndex(j).getPoint2D();
                Point2D force = new Point2D(0, 0);
                for (int k = 0; k < graphRepresentation.getGraph().vertices().length; k++) {
                    if (j != k) {
                        Point2D coord1 = graphRepresentation.getNodeByIndex(k).getPoint2D();
                        double x = coord.getX() - coord1.getX();
                        double y = coord.getY() - coord1.getY();
                        double distance = Math.max(0.001, Math.sqrt(x * x + y * y));
                        double repulsion = attraction * attraction / distance;
                        force = new Point2D(force.getX() + (x / distance) * repulsion, force.getY() + (y / distance) * repulsion);

                    }
                }
            }
        }
            //Calculating the attractive forces
            for (Edge<Node> edge : graphRepresentation.getGraph().edges()) {
                Node source = graphRepresentation.getGraph().getVertexLabel(edge.source());
                Node target = graphRepresentation.getGraph().getVertexLabel(edge.target());

                Point2D sourceCoord = source.getPoint2D();
                Point2D targetCoord = target.getPoint2D();

                double x = sourceCoord.getX() - targetCoord.getX();
                double y = sourceCoord.getY() - targetCoord.getY();
                double distance = Math.max(0.001, Math.sqrt(x * x + y * y));
                double attractionForce = distance * distance / attraction;

                double x1 = (x / distance) * attractionForce;
                double y1 = (y / distance) * attractionForce;
                graphRepresentation.getNodeByIndex(edge.source()).setPoint2D(new Point2D(sourceCoord.getX() - x1, sourceCoord.getY() - y1));
                graphRepresentation.getNodeByIndex(edge.target()).setPoint2D(new Point2D(targetCoord.getX() + x1, targetCoord.getY() + y1));
            }
            //Updating the vertex coordinates
            for (int k = 0; k < graphRepresentation.getGraph().vertices().length; k++) {
                Point2D coord = graphRepresentation.getNodeByIndex(k).getPoint2D();
                double distance = Math.max(0.001, Math.sqrt(coord.getX() * coord.getX() + coord.getY() * coord.getY()));
                double scaleFactor = Math.min(scale / distance, 1.0);
                graphRepresentation.getNodeByIndex(k).setPoint2D(new Point2D(coord.getX() * scaleFactor, coord.getY() * scaleFactor));
            }
        }
}
