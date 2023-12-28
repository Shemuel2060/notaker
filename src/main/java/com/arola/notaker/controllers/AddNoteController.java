package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.dao.UserDao;
import com.arola.notaker.entities.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNoteController {

	@FXML
	private Button button;

	@FXML
	private TextField noteTitle;

	@FXML
	private TextField noteOwner;

	@FXML
	private void addNote() {

		try {
			// get controller of the main view
			

			// Get the stage
			Stage stage = (Stage) button.getScene().getWindow();

			// get creation date == NOT WORKING AS YET
			LocalDate date = LocalDate.now();
			String dated = date.toString();

			// get input for note owner name
			String ownerName = noteOwner.getText();
			

			System.out.println("DEBUGGING POINT--> NAME: " + ownerName);

			// invoke the method to display the name in main controller
			

			// persist note title to DB
			NotesDao notesDao = new NotesDao();
			// get note title input
			String title = noteTitle.getText();
			notesDao.createNote(title, LocalDate.now(), ownerName);
		
			
			System.out.println("Note title: "+title);
			
			System.out.println("Note Title saved in DB");

			// close stage after persisting note to DB
			stage.close();

		} catch (Exception e) {
			System.err.println(e.toString());

		}

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
