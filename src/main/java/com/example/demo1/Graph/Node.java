package com.example.demo1.Graph;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Cristescu Cristian
 * This class is used to model the Nodes of the graph.
 */

public class Node{
    /**
     * The information stored in a node.
     **/
    private String node;
    /**
     * The color of the node.
     */
    private Color color;
    /**
     * The position of the node in the drawing. Initial position is (0.0, 0.0).
     */
    private Point2D point2D = new Point2D(0,0);
    /**
     * The graphic representation of every node.
     */
    private Circle circle;

    public Node(String node, Point2D point2D, Color color) {
        this.node = node;
        this.point2D=point2D;
        this.color = color;
    }
    public Node(String node, Point2D point2D){
        this.node = node;
        this.point2D = point2D;
    }
    public Node(String node, Color color){
        this.node = node;
        this.color = color;
    }
    public Node(String node){
        this.node = node;
    }
    public Node(){}

    public String getNode() {
        return node;
    }
    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.toString() + " " + point2D.getX() + " " + point2D.getY();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setCircle(Circle circle){
        this.circle = circle;
    }
    public Circle getCircle() {
        return circle;
    }
}
