package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;

import javafx.beans.property.SimpleStringProperty;
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

	@FXML private TableView<Note> table;
	@FXML private TableColumn<Note,String> authorColumn;
	@FXML private TableColumn<Note,String> notebook;
	@FXML private TableColumn<Note,String> titleColumn;
	@FXML private TableColumn<Note,LocalDate> creationDate;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// set data factories for each column
		authorColumn.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getUser().getUserName()));
		notebook.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getNotebook().getTitle()));
		titleColumn.setCellValueFactory(new 
				PropertyValueFactory<>("title"));
		creationDate.setCellValueFactory(new 
				PropertyValueFactory<>("creationDate"));
		
		table.setItems(loadNotes());
		
	}
	
	private ObservableList<Note> loadNotes() {
		NotesDao notesDAO = new NotesDao();
		List<Note> notes = notesDAO.getAllNotes();
		return FXCollections.observableArrayList(notes);
		
	}
	
	
    
	
}
