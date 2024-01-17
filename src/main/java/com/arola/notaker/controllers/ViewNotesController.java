package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewNotesController implements Initializable{

	@FXML private TableView table;
	@FXML private TableColumn authorColumn;
	@FXML private TableColumn notebook;
	@FXML private TableColumn titleColumn;
	@FXML private TableColumn creationDate;
	@FXML private TableColumn actions;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	private void loadNotes() {
		
	}
	
	
    
	
}
