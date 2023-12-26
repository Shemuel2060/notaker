package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;

import com.arola.notaker.dao.NotebookDao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
			NotebookDao.createSingleNotebook(notebookName, desc);
			
			System.out.println("Note Title saved in DB");

			// close stage after persisting notebook title and description to DB
			stage.close();

		} catch (Exception e) {
			System.err.println(e.toString());

		}

	}

}
