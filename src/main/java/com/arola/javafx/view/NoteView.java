package com.arola.javafx.view;

import com.arola.notaker.controllers.NotesFXMLDocumentController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;	

public class NoteView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NoteFXMLDocument.fxml"));
        Parent root = loader.load();

        // Set the controller for the FXML file
        NotesFXMLDocumentController controller = loader.getController();

        // Set up the scene
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Arola Notaker");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
