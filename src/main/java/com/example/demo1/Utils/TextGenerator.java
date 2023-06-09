package com.example.demo1.Utils;

import com.example.demo1.Graph.GraphRepresentation;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristescu Cristian
 * This class is a Singleton and is used to generate the textual representation of every node in the graph.
 */
public class TextGenerator {
    /**
     * This method is used to generate textual representation for graphs.
     * @param graphRepresentation this is the graph that needs a textual representation
     * @return returns a list of Text objects, that contains the textual representation of every node in the graph
     */
    public static List<Text> getTexts(GraphRepresentation graphRepresentation){
        List<Text> textList = new ArrayList<>();
        int[] vertices = graphRepresentation.getGraph().vertices();
        for (int vertex : vertices) {
            Text text = new Text();
            text.setX(graphRepresentation.getGraph().getVertexLabel(vertex).getPoint2D().getX() - 5);
            text.setY(graphRepresentation.getGraph().getVertexLabel(vertex).getPoint2D().getY() + 5);
            text.setText(graphRepresentation.getGraph().getVertexLabel(vertex).getNode());
            text.setStroke(Color.INDIGO);
            text.setTextAlignment(TextAlignment.CENTER);
            textList.add(text);
        }
        return textList;
    }
}
