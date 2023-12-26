package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainFXMLDocumentController implements Initializable {

	@FXML
	private BorderPane root;

	@FXML
	private Label nameLabel = new Label(); // name label- left top

	@FXML
	private Button newNote; // addNote()

	@FXML
	private Button newNotebook; // addNotebook()

	@FXML
	private TextField tags;


	@FXML
	private ToggleButton themeToggleButton;

	@FXML
	private Text creationDate = new Text(); // Creation date in string form.

	public Text getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Text creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("DEBUGGING POINT --> " + "MainFXMLDocumentController initialized!!");

		// initialization code here...
		nameLabel.setText("Samuel Katongole");
		creationDate.setText(LocalDate.now().toString());

	}

	@FXML
	private void toggleTheme() {
		if (themeToggleButton.isSelected()) {
			// Apply Dark Theme
			root.setStyle("-fx-background-color: #A16946");

		} else {
			root.setStyle("-fx-background-color:#7cafc2");
		}
	}

	@FXML
	/**
	 * brings up a new window to add details about the new note to add or start
	 * writing.
	 */
	private void addNote() {

		try {
			// Load the FXML for the new window
			URL fxmlLocation = getClass().getResource("/com/arola/javafx/view/addNoteWindow.fxml");
			System.out.println("WORKING: " + fxmlLocation);
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Parent root = loader.load();

			// Create a new stage
			Stage addNoteStage = new Stage();
			addNoteStage.initModality(Modality.APPLICATION_MODAL);
			addNoteStage.setTitle("New Note");
			addNoteStage.setScene(new Scene(root));

			// Show the new stage
			addNoteStage.showAndWait();
		} catch (Exception e) {

			System.err.println(e.toString()); // Handle the exception appropriately
		}

	}

	@FXML
	/**
	 * brings up a new window to add details about the new notebook to add or start
	 * writing.
	 */
	private void addNotebook() {

		try {
			// Load the FXML for the new window
			URL fxmlLocation = getClass().getResource("/com/arola/javafx/view/addNotebookWindow.fxml");
//			System.out.println("WORKING: "+ fxmlLocation);
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Parent root = loader.load();

			// Create a new stage
			Stage addNotebookStage = new Stage();
			addNotebookStage.initModality(Modality.APPLICATION_MODAL);
			addNotebookStage.setTitle("New Note Book");
			addNotebookStage.setScene(new Scene(root));

			// Show the new stage
			addNotebookStage.showAndWait();
		} catch (Exception e) {

			System.err.println(e.toString());
			; // Handle the exception appropriately
		}

	}

	@FXML
	private void handleaddTags() {
		
		// detect when enter key is pressed
		
		// append separator+white space to the entered tag
		
		// count length of new input and cut off old text+separator+whitespace
		
		// do that until no imput entered.
		
	    tags.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String tagText = tags.getText().trim();
                    tagText = tagText + " | ";

//                    if (!tagText.isEmpty()) {
//                        // Persist the tagText to the database (replace with your database logic)
//                        persistTagToDatabase(tagText);
//
//                        // Clear the TextField for the next input
//                        tagInput.clear();
//                    }
                }
            }
        });

		
	}

	@FXML
	private void addRefs() {

	}

	public void displayOwnerName(String name) {
		nameLabel.setText(name);

	}

}
