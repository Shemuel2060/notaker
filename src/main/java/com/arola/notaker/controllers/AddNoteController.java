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

			/* =================note logic ================= */

			// get inputs
			String notebookName = notebookTitle.getText();
			String desc = description.getText();
			// get input for note owner name
			String ownerName = noteOwner.getText();

			LocalDate date = LocalDate.now();
			String dated = date.toString();

			// persist note title to DB
			NotesDao notesDao = new NotesDao();
			// get note title input
			String title = noteTitle.getText();

			notesDao.createNote(title, date, ownerName, notebookName, desc);

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
