package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;

import com.arola.notaker.dao.NotebookDao;
import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.dao.UserDao;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNoteController {

	@FXML
	private Button button;

	// notebook details

	@FXML
	private TextField notebookTitle;

	@FXML
	private TextField description;

	// note details

	@FXML
	private TextField noteTitle;

	@FXML
	private TextField noteOwner;

	@FXML
	private void addNote() {

		try {
			
			// Get the stage
			Stage stage = (Stage) button.getScene().getWindow();
			
			/* =================notebook logic =================*/


			// get inputs
			String notebookName = notebookTitle.getText();
			String desc = description.getText();

			// persist note title to DB
			
			// check if notebook already exists before persisting it and the note
			
			// if yes, create note without persisting the notebook again, but
			// link the two
			
			// if not, create note and notebook, linking them together
			
			
			
			
			
//			NotebookDao notebookDAO = new NotebookDao();
//			Notebook created = notebookDAO.createNotebook(notebookName, desc);
			
//			if (created == null) { // notebook already exists, so add note to it...Later
//				// Create a new alert
//				Alert alert = new Alert(AlertType.INFORMATION);
//
//				// Set the title and content text
//				alert.setTitle("Info");
//				alert.setHeaderText(null); // No header
//				alert.setContentText(notebookName.toUpperCase() + " already exists!");
//
//				// Show the alert
//				alert.showAndWait();
//			}

		
			/* =================note logic =================*/
			
			// LATER...
			LocalDate date = LocalDate.now();
			String dated = date.toString();

			// get input for note owner name
			String ownerName = noteOwner.getText();

			System.out.println("DEBUGGING POINT--> NAME: " + ownerName);


			// persist note title to DB
			NotesDao notesDao = new NotesDao();
			// get note title input
			String title = noteTitle.getText();
			
			notesDao.createNote(title, date, ownerName, notebookName, desc);

			System.out.println("Note title: " + title);

			System.out.println("Note Title saved in DB");

			// close stage after persisting note to DB
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

	public TextField getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(TextField noteTitle) {
		this.noteTitle = noteTitle;
	}

	public TextField getNoteOwner() {
		return noteOwner;
	}

	public void setNoteOwner(TextField noteOwner) {
		this.noteOwner = noteOwner;
	}

}
