/**
 * @author Cristescu Cristian
 */
package com.example.demo1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class GraphLayout extends Application {
    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException if the fxml file cannot be found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphLayout.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Graph Layout");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("C:\\Users\\cristescu\\Desktop\\cristescu_cristian_GraphLayout\\src\\main\\resources\\logo.png"));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}