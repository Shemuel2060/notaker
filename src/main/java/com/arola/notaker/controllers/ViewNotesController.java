package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewNotesController implements Initializable {

	@FXML
	private TableView<Note> table;
	@FXML
	private TableColumn<Note, String> authorColumn;
	@FXML
	private TableColumn<Note, String> notebook;
	@FXML
	private TableColumn<Note, String> titleColumn;
	@FXML
	private TableColumn<Note, LocalDate> creationDate;

	// buttons
	@FXML
	private Button openNote;
	@FXML
	private Button printNote;
	@FXML
	private Button deleteNote;

	public boolean isDeleteButtonClicked = false;
	public boolean isOpenButtonClicked = false;
	public boolean isPrintButtonClicked = false;
	
	private Note selectedNote;

	private NotesDao notesDao = new NotesDao();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// set data factories for each column
		authorColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUserName()));
		notebook.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getNotebook().getTitle()));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

		table.setItems(loadNotes());

	}

	private ObservableList<Note> loadNotes() {
		NotesDao notesDAO = new NotesDao();
		List<Note> notes = notesDAO.getAllNotes();
		return FXCollections.observableArrayList(notes);

	}

	@FXML
	private void printNote() {
		// get the stage 
		Stage stage = (Stage) printNote.getScene().getWindow();
		isPrintButtonClicked = true; // open button clicked.
		
		// close the view note window
		stage.close();

	}

	@FXML
	private void openNote() {
		// Get the stage
		Stage stage = (Stage) openNote.getScene().getWindow();

		isOpenButtonClicked = true; // open button clicked.
		
		stage.close();
		

	}

	public Note selectedNote() {
		// get the selectionModel
		TableViewSelectionModel<Note> selections = table.getSelectionModel();
		// get selected note
		selectedNote = selections.getSelectedItem();

		return selectedNote;
	}

	@FXML
	public void deleteNote() {
		Stage stage = (Stage) deleteNote.getScene().getWindow();
		// get the stage or window
		
		// get selected note
		selectedNote = selectedNote();
		System.out.println("SELECTED NOTE IN DELETENOTE(): "+ selectedNote.getTitle());

		isDeleteButtonClicked = true; // delete button clicked.
		System.out.println("DELETE BUTTON CLICKED? "+isDeleteButtonClicked);
		
		// delete note from the table
		table.getItems().removeAll(selectedNote);
		
		stage.close();
	}

	/**
	 * Called in the main view window after checking whether the deleteNote button
	 * has been clicked. If yes, then it clears all the content on the main view
	 * window.(similar approach for the open note and print note)
	 * 
	 * @param name
	 * @param noteTitle
	 * @param notebookName
	 * @param notes
	 * @param cues
	 * @param summary
	 * @param comment
	 * @param creationDate
	 */
	public void clearMainViewContents(Note selectedNote, Label name, Label noteTitle, 
			Label notebookName, TextArea notes, TextArea cues,TextArea summary, 
			TextField comment, Text creationDate) {
		
//		System.out.print("\nSELECTED NOTE TITLE: "+selectedNote.getTitle()+"\n");
		System.out.print("\nCURRENT NOTE TITLE: "+ noteTitle.getText()+"\n");
		
		System.out.println("SELECTED NOTE TITLE: "+ selectedNote.getTitle());
		
		
		if(selectedNote.getTitle().equals(noteTitle.getText())) {
			/* check if they belong to the selected note. This is on the assumption
			 * that the titles for all notes are different.*/
			
			name.setText("");
			noteTitle.setText("");
			notebookName.setText("");

			notes.setText("");
			cues.setText("");
			summary.setText("");

			comment.setText("");
			creationDate.setText("");
		
				
		}
		

		

	}
	
	
	

	public TableView<Note> getTable() {
		return table;
	}

	public void setTable(TableView<Note> table) {
		this.table = table;
	}

	public TableColumn<Note, String> getAuthorColumn() {
		return authorColumn;
	}

	public void setAuthorColumn(TableColumn<Note, String> authorColumn) {
		this.authorColumn = authorColumn;
	}

	public TableColumn<Note, String> getNotebook() {
		return notebook;
	}

	public void setNotebook(TableColumn<Note, String> notebook) {
		this.notebook = notebook;
	}

	public TableColumn<Note, String> getTitleColumn() {
		return titleColumn;
	}

	public void setTitleColumn(TableColumn<Note, String> titleColumn) {
		this.titleColumn = titleColumn;
	}

	public TableColumn<Note, LocalDate> getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(TableColumn<Note, LocalDate> creationDate) {
		this.creationDate = creationDate;
	}

	public Button getOpenNote() {
		return openNote;
	}

	public void setOpenNote(Button openNote) {
		this.openNote = openNote;
	}

	public Button getPrintNote() {
		return printNote;
	}

	public void setPrintNote(Button printNote) {
		this.printNote = printNote;
	}

	public Button getDeleteNote() {
		return deleteNote;
	}

	public void setDeleteNote(Button deleteNote) {
		this.deleteNote = deleteNote;
	}

	public Note getSelectedNote() {
		return selectedNote;
	}

	public void setSelectedNote(Note selectedNote) {
		this.selectedNote = selectedNote;
	}
	
	
	

}
