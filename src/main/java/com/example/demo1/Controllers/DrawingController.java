package com.example.demo1.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import java.io.File;
import java.io.IOException;

/**
 * @author Cristescu Cristian
 * This class is used to handle the events of the Drawing Scene
 */
public class DrawingController {
    @FXML
    private Button closeButton = new Button();
    @FXML
    private Button exportButton = new Button();
    Stage stage;
    Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * This method switches the scenes and returns to the main menu.
     * @param e the ActionEvent.
     * @throws IOException if the fxml file cannot be found.
     */
    public void goBackToInitialMenu(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/hello-view.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\cristescu\\Desktop\\cristescu_cristian_GraphLayout\\src\\main\\resources\\logo.png"));
        stage.setTitle("Graph Layout");
        stage.show();
    }
}
