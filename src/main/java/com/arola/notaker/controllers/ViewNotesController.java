package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;

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
	@FXML private TableColumn<Note,String> titleColumn;
	@FXML private TableColumn<Note,String> notebook;
	@FXML private TableColumn<Note,String> authorColumn;
	@FXML private TableColumn<Note,LocalDate> creationDate;
	
	
	@FXML private TableColumn<Note,String> actions;
	
//	@FXML private Button openNote;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// get notes from DB - define method in NotesDao that gets all notes
		
		
		
		// put them in an ObservableList via FXCollection
		
		// put the ObservableList in the table
		
		// create columns and their headers
//		notebook = new TableColumn<>("Notebook");
//		titleColumn = new TableColumn<>("Note Title");
//		authorColumn = new TableColumn<>("Author");
//		creationDate = new TableColumn<>("Creation Date");
		
		notebook.setCellValueFactory(new PropertyValueFactory<>("notebook"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
		creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
		
		
		
		
		table.setPlaceholder(new Label("Get data from DB"));
		// add columns to table
		table = new TableView<>();
		table.getColumns().add(notebook);
		table.getColumns().add(titleColumn);
		table.getColumns().add(authorColumn);
		table.getColumns().add(creationDate);
		
		loadNotes();
	}
	
	
	private void loadNotes() {
		
		// initialize notes dao
		
		NotesDao notesDao = new NotesDao();
		
		// get all notes
		
		List<Note> notes = notesDao.getAllNotes();
		
		// add notes to an observableList
		ObservableList<Note> noteList = FXCollections.observableArrayList(notes);
		
		// bind the list to the table view.
        table.setItems(noteList);
		
	}
	
	
	@FXML
	private void openNote() {
		
	}

    
	
	
	// getters and setters
	public TableView<Note> getTable() {
		return table;
	}
	public void setTable(TableView<Note> table) {
		this.table = table;
	}
	public TableColumn<Note, String> getTitleColumn() {
		return titleColumn;
	}
	public void setTitleColumn(TableColumn<Note, String> titleColumn) {
		this.titleColumn = titleColumn;
	}
	public TableColumn<Note, String> getNotebook() {
		return notebook;
	}
	public void setNotebook(TableColumn<Note, String> notebook) {
		this.notebook = notebook;
	}
	
	public TableColumn<Note, String> getAuthorColumn() {
		return authorColumn;
	}
	public void setAuthorColumn(TableColumn<Note, String> authorColumn) {
		this.authorColumn = authorColumn;
	}
//	public TableColumn<Note, String> getCreationDate() {
//		return creationDate;
//	}
//	public void setCreationDate(TableColumn<Note, String> creationDate) {
//		this.creationDate = creationDate;
//	}
	
	
	

}
