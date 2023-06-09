package com.example.demo1.Controllers;

import com.example.demo1.Algorithms.CircleLayoutAlgorithm;
import com.example.demo1.Algorithms.ForceDirected;
import com.example.demo1.Factories.EdgeFactory;
import com.example.demo1.Factories.GraphFactory;
import com.example.demo1.Graph.GraphRepresentation;
import com.example.demo1.Factories.NodeFactory;
import com.example.demo1.Utils.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Cristescu Cristian
 * This controller class is used to handle the menu interface of the application.
 */
public class MenuController implements Initializable {
    /**
     * This RadioButton represents a way of setting the nature of a graph.
     */
    @FXML
    private RadioButton radioButton = new RadioButton();
    /**
     * This ComboBox is used to set the color of the graph.
     */
    @FXML
    private ComboBox<String> colorComboBox = new ComboBox<>();
    @FXML
    private Button generate = new Button();
    /**
     * In this TextArea, the user of the application inserts the edges of the graph.
     */
    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private Text text = new Text();
    @FXML
    private Button graphGenerator = new Button();

    private final NodeFactory nodeFactory = new NodeFactory();
    private final GraphFactory graphFactory = new GraphFactory();
    private final EdgeFactory edgeFactory = new EdgeFactory();
    private final VerticesEdgesValidator verticesEdgesValidator = new VerticesEdgesValidator();
    private Stage stage = new Stage();
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorComboBox.setItems(FXCollections.observableArrayList("Red", "Aqua", "Green", "Orange"));
    }

    /**
     * This method handles the events of the RadioButton.
     * @param e the ActionEvent.
     */
    public void radioButtonAction(ActionEvent e) {
        graphFactory.setDirected(radioButton.isSelected());
    }

    /**
     * This method handles the events of the ComboBox and generates the color of the graph.
     * @param e the ActionEvent.
     */
    public void comboBoxAction(ActionEvent e) {
        String s = colorComboBox.getValue();
        if (s.equals("Red")) {
            nodeFactory.setColor(Color.RED);
            edgeFactory.setColor(Color.RED);
        }
        if (s.equals("Aqua")) {
            nodeFactory.setColor(Color.AQUA);
            edgeFactory.setColor(Color.AQUA);
        }
        if (s.equals("Green")) {
            nodeFactory.setColor(Color.GREEN);
            edgeFactory.setColor(Color.GREEN);
        }
        if (s.equals("Orange")) {
            nodeFactory.setColor(Color.ORANGE);
            edgeFactory.setColor(Color.ORANGE);
        }
    }

    /**
     * This private method helps to represent the graph and switches the scenes(from the meu scene, to the drawing scene).
     * @param graphRepresentation the graph that needs a representation.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    private void changeToDrawingScene(GraphRepresentation graphRepresentation, ActionEvent e) throws IOException {
        //the representation for every vertex
       List<Line> lines = LineGenerator.getLines(graphRepresentation);
       List<Circle> circles = CircleGenerator.getCircles(graphRepresentation);
       List<Text> textList = TextGenerator.getTexts(graphRepresentation);

        //loading the drawing window and adding the representation of the graph
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/drawingScene.fxml"));
        Parent root = loader.load();
        Group root1 = new Group(root);

        stage = (Stage) ((javafx.scene.Node) (e.getSource())).getScene().getWindow();
        for (Line line : lines) {
            root1.getChildren().add(line);

        }
        for (Circle circle : circles) {
            root1.getChildren().add(circle);

        }
        for (Text text1 : textList) {
            root1.getChildren().add(text1);

        }
        this.scene = new Scene(root1);
        this.stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\cristescu\\Desktop\\cristescu_cristian_GraphLayout\\src\\main\\resources\\logo.png"));
        stage.setTitle("Graph Layout");
        this.stage.show();
    }

    /**
     * This method switches the scenes and generates the representation of the custom graph.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    public void generateForceDirectedDrawing(ActionEvent e) throws IOException {
        if (verticesEdgesValidator.validate(textArea.getText())) {
            //setting up the factories to create the graph
            nodeFactory.setNodes(textArea.getText());
            edgeFactory.setS(textArea.getText());
            GraphRepresentation graphRepresentation = graphFactory.getGraph(edgeFactory.getEdges(nodeFactory.getNodes()));

            //applying the algorithm to the graph
            ForceDirected forceDirected = new ForceDirected(graphRepresentation);
            forceDirected.forceDirected(1000, 10000);
            changeToDrawingScene(graphRepresentation, e);
            System.out.println(textArea.getText());
        } else {
            text.setStroke(Color.RED);
            text.setFill(Color.RED);
            text.setText("Invalid edge or vertex!");
        }
    }
    /**
     * This method switches the scenes and generates the representation of the custom graph.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    public void generateCircleLayout(ActionEvent e) throws IOException {
        if (verticesEdgesValidator.validate(textArea.getText())) {
            //setting up the factories to create the graph
            nodeFactory.setNodes(textArea.getText());
            edgeFactory.setS(textArea.getText());
            GraphRepresentation graphRepresentation = graphFactory.getGraph(edgeFactory.getEdges(nodeFactory.getNodes()));

            //applying the algorithm to the graph
            CircleLayoutAlgorithm circleLayoutAlgorithm = new CircleLayoutAlgorithm();
            graphRepresentation = circleLayoutAlgorithm.circleLayoutAlgorithm(graphRepresentation);
            changeToDrawingScene(graphRepresentation, e);
            System.out.println(textArea.getText());
        } else {
            text.setStroke(Color.RED);
            text.setFill(Color.RED);
            text.setText("Invalid edge or vertex!");
        }
    }

    /**
     * This method switches the scenes and generates a random graph and applies the force directed algorithm to the graph.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    public void generateCircleGraph(ActionEvent e) throws IOException {
        GraphRepresentation graphRepresentation = CircleGraphGenerator.generateGraph();
        CircleLayoutAlgorithm circleLayoutAlgorithm = new CircleLayoutAlgorithm();
        graphRepresentation = circleLayoutAlgorithm.circleLayoutAlgorithm(graphRepresentation);
        changeToDrawingScene(graphRepresentation, e);
    }

    /**
     * This method switches the scenes and generates a random graph and applies the circle layout algorithm to the graph.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    public void generateForceGraph(ActionEvent e) throws IOException {
        GraphRepresentation graphRepresentation = ForceGraphGenerator.generateGraph();
        ForceDirected forceDirected = new ForceDirected(graphRepresentation);
        forceDirected.forceDirected(1000, 100000);
        changeToDrawingScene(graphRepresentation, e);
    }

}
