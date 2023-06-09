package com.example.demo1.Utils;

import com.example.demo1.Graph.GraphRepresentation;
import com.example.demo1.Graph.Node;
import javafx.scene.shape.Line;
import org.graph4j.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristescu Cristian
 * This Singleton class is used to generate the graphic representation of the edges of the graph.
 */
public class LineGenerator {
    /**
     * This method generates a list of lines, representing the graphic representation of every edge of the graph
     * @param graphRepresentation this is the graph that needs a representation for it's edges
     * @return return a list of lines, corresponding to every edge of the graph
     */
    public static List<Line> getLines(GraphRepresentation graphRepresentation){
        List<Line> lines = new ArrayList<>();
        Edge<Node>[] edges = graphRepresentation.getGraph().edges();
        for (Edge<Node> edge : edges) {
            Line line = new Line();
            Node source = graphRepresentation.getGraph().getVertexLabel(edge.source());
            Node target = graphRepresentation.getGraph().getVertexLabel(edge.target());
            line.setStartX(source.getPoint2D().getX());
            line.setStartY(source.getPoint2D().getY());
            line.setEndX(target.getPoint2D().getX());
            line.setEndY(target.getPoint2D().getY());
            line.setStroke(source.getColor());
            line.setStrokeWidth(1);
            lines.add(line);
        }
        return lines;

    }
}
