package com.arola.notaker.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainFXMLDocumentController implements Initializable {

	@FXML
	private BorderPane root;

	@FXML
	private Button newNote; // addNote()

	@FXML
	private Button newNotebook; // addNotebook()

	@FXML
	private TextField tags;

	@FXML
	private ToggleButton themeToggleButton;

	@FXML
	private Text creationDate = new Text(); // Creation date in string form.

	@FXML
	private Label nameLabel;

	@FXML
	private Label currentNoteTitle;

	@FXML
	private Label currentNotebookName;
	
	/* ============= NOT YET ==================== */
	
	@FXML
	private Button openNote;
	
	@FXML
	private TextField searchField;
	
	@FXML
	private TextArea notesArea;
	
	/* ============= END:: NOT YET ==================== */

	public Text getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Text creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("DEBUGGING POINT --> " + "MainFXMLDocumentController initialized!!");

		// initialization code here...

//		creationDate.setText(LocalDate.now().toString());

//		nameLabel.setText("Samuel Katongole");
//		creationDate.setText(LocalDate.now().toString());
//		creationDate.setStyle("-fx-text-fill:white");


	}

	@FXML
	private void toggleTheme() {
		if (themeToggleButton.isSelected()) {
			// Apply Dark Theme
			root.setStyle("-fx-background-color: #A16946");

		} else {
			root.setStyle("-fx-background-color:#7cafc2");
		}
	}

	@FXML
	/**
	 * brings up a new window to add details about the new note to add or start
	 * writing.
	 */
	private void addNote() {

		try {
			// Load the FXML for the new window
			URL fxmlLocation = getClass().getResource("/com/arola/javafx/view/addNoteWindow.fxml");
			System.out.println("WORKING: " + fxmlLocation);
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Parent root = loader.load();
			AddNoteController controller = loader.getController();

			// Create a new stage
			Stage addNoteStage = new Stage();
			addNoteStage.initModality(Modality.APPLICATION_MODAL);
			addNoteStage.setTitle("New Note");
			addNoteStage.getIcons().add(new Image("/images_icons/arola-logo.png"));
			addNoteStage.setScene(new Scene(root));

			// Show the new stage
			addNoteStage.showAndWait();

			// get fields from the AddNoteController
			creationDate.setText(LocalDate.now().toString());
			// set the note title
			TextField field = controller.getNoteTitle();
			String newTitle = field.getText();
			currentNoteTitle.setText(newTitle);

			// set the note owner name
			String noteOwner = controller.getNoteOwner().getText();
			nameLabel.setText(noteOwner);
			
			// set the notebook name
			String notebookName = controller.getNotebookTitle().getText().toUpperCase();
			currentNotebookName.setText(notebookName);

		} catch (Exception e) {

			System.err.println(e.toString()); // Handle the exception appropriately
		}

	}

	@FXML
	private void openNote() {
		
	}
	
	@FXML
	private void saveNotesContent(KeyEvent ke) {
		// get current note
		
		String currentNoteByTitle = currentNoteTitle.getText();
		
		// get Note object by its title
		NotesDao notesDAO = new NotesDao();
		Note note = notesDAO.getNoteByTitle(currentNoteByTitle);
		
		// check if note's content is empty, if yes, add notes being types
		if(note.getContents()=="") { // note content is empty
			// add typed content to the note
			note.setContents(notesArea.getText());	
			
		}else { // note content is not empty, 
			// update existing note content
			String newContent=notesArea.getText();
			notesDAO.editNoteContent(currentNoteByTitle, newContent);
			
		}
		
		// if not, update existing notes with the new notes
		
		
	}
	
	@FXML
	private void handleaddTags() {

		// detect when enter key is pressed

		// append separator+white space to the entered tag

		// count length of new input and cut off old text+separator+whitespace

		// do that until no imput entered.

		tags.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					String tagText = tags.getText().trim();
					tagText = tagText + " | ";

//                    if (!tagText.isEmpty()) {
//                        // Persist the tagText to the database (replace with your database logic)
//                        persistTagToDatabase(tagText);
//
//                        // Clear the TextField for the next input
//                        tagInput.clear();
//                    }
				}
			}
		});

	}

	@FXML
	private void addRefs() {

	}

	public void displayOwnerName(String name) {
		nameLabel.setText(name);

	}

}
