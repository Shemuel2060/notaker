package com.arola.javafx.view;

import com.arola.notaker.controllers.RefFXMLDocumentController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;	

public class RefView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RefFXMLDocument.fxml"));
        Parent root = loader.load();

        // Set the controller for the FXML file
        RefFXMLDocumentController controller = loader.getController();

        // Set up the scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Add Reference");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
