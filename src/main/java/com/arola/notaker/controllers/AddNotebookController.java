package com.arola.notaker.controllers;

import com.arola.notaker.dao.NotebookDao;
import com.arola.notaker.entities.Notebook;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNotebookController {

	@FXML
	private Button button;

	@FXML
	private TextField notebookTitle;

	@FXML
	private TextField description;

	@FXML
	private void addNotebook() {

		try {
			// get controller of the main view
			
			
		

			// Get the stage
			Stage stage = (Stage) button.getScene().getWindow();

			// get input for note owner name
			String owner = description.getText();


			// invoke the method to display the name in main controller
			

			// get inputs
			String notebookName = notebookTitle.getText();
			String desc = description.getText();
			
			// persist note title to DB
			NotebookDao notebookDAO = new NotebookDao();
			Notebook created = notebookDAO.createNotebook(notebookName, desc);
			if(created==null) {
				 // Create a new alert
		        Alert alert = new Alert(AlertType.INFORMATION);

		        // Set the title and content text
		        alert.setTitle("Info");
		        alert.setHeaderText(null); // No header
		        alert.setContentText(notebookName.toUpperCase()+ " already exists!");

		        // Show the alert
		        alert.showAndWait();
			}
			
			System.out.println("Note Title saved in DB");

			// close stage after persisting notebook title and description to DB
			stage.close();

		} catch (Exception e) {
			System.err.println(e.toString());

		}

	}

	public TextField getNotebookTitle() {
		return notebookTitle;
	}

	public void setNotebookTitle(TextField notebookTitle) {
		this.notebookTitle = notebookTitle;
	}

	public TextField getDescription() {
		return description;
	}

	public void setDescription(TextField description) {
		this.description = description;
	}
	
	

}
