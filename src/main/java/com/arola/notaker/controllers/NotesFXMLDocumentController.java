package com.arola.notaker.controllers;

import com.arola.notaker.dao.RefsDao;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NotesFXMLDocumentController {

	@FXML
	private Text noteOwner; // dynamic

	@FXML
	private TextField searchField;
	
	
	@FXML
	private Text dynamicText; // with date of creation

	@FXML
	private void addNote() {
		// Retrieve values from text fields
		String owner = noteOwner.getText();
		String search = searchField.getText();
	
		

		// Perform logic to add the reference (e.g., store in a database or display in
		// console)

		System.out.println("Added Note:");
		System.out.println("Title: " + owner);
		System.out.println("URL: " + search);

		// add to the DB

		// create the notes service/ DAO
		

		// Clear text fields after adding reference
	
		searchField.clear();
	}

}
