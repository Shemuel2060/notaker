package com.arola.notaker.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.colors.Color;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainFXMLDocumentController implements Initializable {

	@FXML
	private BorderPane root;

	@FXML
	private Button newNote; // addNote()

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
	private Button viewNotes;

	@FXML
	private TextField searchField;
	/* ============= END:: NOT YET ==================== */

	@FXML
	private Button printNote;

	
	@FXML
	private TextArea notesArea;

	@FXML
	private TextArea summaryArea;

	@FXML
	private TextArea cueArea;

	@FXML
	private TextField comment;

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
	private void viewNotes() {
		
		try {
			// Load the FXML for the new window
			URL fxmlLocation = getClass().getResource("/com/arola/javafx/view/viewNotesWindow.fxml");
			System.out.println("WORKING: " + fxmlLocation);
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Parent root = loader.load();
			ViewNotesController controller = loader.getController();

			// Create a new stage
			Stage viewNotesStage = new Stage();
			viewNotesStage.initModality(Modality.APPLICATION_MODAL);
			viewNotesStage.setTitle("View Notes");
			viewNotesStage.getIcons().add(new Image("/images_icons/arola-logo.png"));
			viewNotesStage.setScene(new Scene(root));

			// Show the new stage
			viewNotesStage.showAndWait();
			
			
			// get access to deleteNote button
			if(controller.isDeleteButtonClicked){
				
				System.out.println("----> In the main view controller");
				
				Note selectedNote = controller.getSelectedNote();
				System.out.println("----> "+selectedNote.getTitle());
				
				controller.clearMainViewContents(selectedNote,nameLabel, currentNoteTitle, 
						currentNotebookName, notesArea, cueArea, summaryArea, 
						comment, creationDate);	
				
				System.out.println("CLEARED FIELDS ");
				// also empty the fields in the DB.
				NotesDao notesDAO = new NotesDao();
				notesDAO.deleteNoteByTitle(selectedNote.getTitle());
				
				
				
			}
			
			if(controller.isOpenButtonClicked) {
				Note selectedNote = controller.selectedNote();
				
				//use selected note to populate the fields
				nameLabel.setText(selectedNote.getUser().getUserName());
				currentNoteTitle.setText(selectedNote.getTitle());
				currentNotebookName.setText(selectedNote.getNotebook().getTitle());
				creationDate.setText(selectedNote.getCreationDate().toString());
				
				// the areas
				notesArea.setText(selectedNote.getContents());
				cueArea.setText(selectedNote.getCues());
				summaryArea.setText(selectedNote.getSummary());
				
			}
			
			if(controller.isPrintButtonClicked) {
				Note selectedNote = controller.selectedNote();				
				printNotes();
			}
			// check if it is clicked
			// if yes, call the method that clears areas on the main view
			// if not, then leave them there...
			
			// do the same for the other two buttons.
			
			
			
			
			
		}catch(Exception e) {
			System.err.println(e.toString());
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
			
			// make the typing areas blank
			notesArea.setText("");
			summaryArea.setText("");
			cueArea.setText("");
			comment.setText("");
			
			

		} catch (Exception e) {

			System.err.println(e.toString()); // Handle the exception appropriately
		}

	}

	@FXML
	private void saveNotesContent(KeyEvent ke) {
		// get current note

		String currentNoteByTitle = currentNoteTitle.getText();

		// get Note object by its title
		NotesDao notesDAO = new NotesDao();
		Note note = notesDAO.getNoteByTitle(currentNoteByTitle);

		// check if note's content is empty, if yes, add notes being types
		if (note.getContents() == "") { // note content is empty
			// add typed content to the note
			note.setContents(notesArea.getText());

		} else { // note content is not empty,
			// update existing note content
			String newContent = notesArea.getText();
			notesDAO.editNoteContent(currentNoteByTitle, newContent);

		}

	}

	@FXML
	private void saveNotesSummary(KeyEvent ke) {
		// get current note

		String currentNoteByTitle = currentNoteTitle.getText();

		// get Note object by its title
		NotesDao notesDAO = new NotesDao();
		Note note = notesDAO.getNoteByTitle(currentNoteByTitle);

		// check if note's content is empty, if yes, add notes being types
		if (note.getSummary() == "") { // note content is empty
			// add typed summary to the note

			note.setSummary(summaryArea.getText());

		} else { // note content is not empty,
			// update existing note content
			String newSummary = summaryArea.getText();
			notesDAO.editNoteSummary(currentNoteByTitle, newSummary);

		}

	}

	@FXML
	private void saveNotesCues(KeyEvent ke) {

		// get current note

		String currentNoteByTitle = currentNoteTitle.getText();

		// get Note object by its title
		NotesDao notesDAO = new NotesDao();
		Note note = notesDAO.getNoteByTitle(currentNoteByTitle);

		// check if note's content is empty, if yes, add notes being types
		if (note.getCues() == "") { // note content is empty

			note.setCues(cueArea.getText());

		} else { // note content is not empty,
			// update existing note content
			String newCues = cueArea.getText();
			notesDAO.editNoteCues(currentNoteByTitle, newCues);

		}

	}

	@FXML
	private void addComment(KeyEvent ke) {
		// get current note

		String currentNoteByTitle = currentNoteTitle.getText();

		// get Note object by its title
		NotesDao notesDAO = new NotesDao();
		Note note = notesDAO.getNoteByTitle(currentNoteByTitle);

		// check if note's content is empty, if yes, add notes being types
		if (note.getComment() == "") { // note content is empty
			// add typed summary to the note

			note.setComment(comment.getText());

		} else { // note content is not empty,
			// update existing note content
			String newComment = comment.getText();
			notesDAO.editNoteComment(currentNoteByTitle, newComment);

		}

	}

	@FXML
	private void printNotes() {
		FileChooser fileChooser = new FileChooser();

	    fileChooser.setTitle("Save PDF File");
	    fileChooser.getExtensionFilters().add(
	    		new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

	    // Show the file save dialog and get the selected file
	    File selectedFile = fileChooser.showSaveDialog(null);
	    
	 // Check if a file was selected
	    if (selectedFile != null) {
	        try {
	            FileOutputStream fileName = new FileOutputStream(selectedFile);

	         // create the document
				PdfWriter writer = new PdfWriter(fileName);
				PdfDocument pdf = new PdfDocument(writer);

				pdf.setDefaultPageSize(PageSize.A4);

				Document document = new Document(pdf);
				
				Color headerColor = new DeviceRgb(0, 0, 255);

				// get notes, cues and summary from the DB
				NotesDao notesDAO = new NotesDao();
				Note wantedNote = notesDAO.getNoteByTitle(currentNoteTitle.getText());

				// notes details
				Paragraph author = new Paragraph(nameLabel.getText().toUpperCase());
				Paragraph nbName = new Paragraph("Notebook: " + 
						currentNotebookName.getText());
				Paragraph noteDate = new Paragraph("Printed on: " + LocalDate.now().toString());

				// notes
				
				Paragraph title = new Paragraph(currentNoteTitle.getText())
						.setFontColor(headerColor, 1)
						.setBold().setCharacterSpacing(0.5f);
				
				Paragraph notes = new Paragraph(wantedNote.getContents() + "\n");

				// cues
				Paragraph cues = new Paragraph(wantedNote.getCues() + "\n");

				// summary
				Paragraph summary = new Paragraph(wantedNote.getSummary() + "\n");

				// create header table with two columns

				float[] headerWidth = { 200f, 400f };

				Table header = new Table(headerWidth);

				// add block element, e.g. paragraph to a cell
				Border b = new SolidBorder(1f/2f);
				Border noBorder = Border.NO_BORDER;
				
				// put author in the first column of the header
				float[] headerLeftWidth = {200f};
				Table headerLeft = new Table(headerLeftWidth);
				
				headerLeft.addCell(new Cell()
						.add(author.setFontColor(headerColor, 1)
								.setBold().setCharacterSpacing(0.5f))
						.setBorder(noBorder)
						.setTextAlignment(TextAlignment.CENTER)
						.setBold()
						.setItalic()
						);
				
				
				/*put title and date into another table, add it to the cell
				 * of the header's second column */
				
				float[] headerRightWidth = {400f};
				Table headerRight = new Table(headerRightWidth);
				
				headerRight.addCell(new Cell().add(nbName.setFontColor(headerColor, 1)
						.setBold().setCharacterSpacing(0.5f))
						.setBorder(noBorder));
				headerRight.addCell(new Cell()
						.add(new Paragraph("\n"))
						.setBorder(noBorder)
						.setWidth(2.5f));
				headerRight.addCell(new Cell().add(noteDate.setFontColor(headerColor, 1)
						.setBold().setCharacterSpacing(0.5f))
						.setBorder(noBorder));
				
				
				// add headerRight and headerLeft to header
				header.addCell(new Cell().add(headerLeft)
						.setBorderBottom(b)
						.setBorderRight(b));
				header.addCell(new Cell().add(headerRight).
						setBorderBottom(b));
				
				
				/*Future:
				 * 1. remove the borders...
				 * 2. create similar thing for the notes, cues and summary sections
				 * 3. text styling
				 * 4. page numbering */
				
				// create table for cues and notes
				Table cueTable = new Table(new float[] {200f});
				Table noteTable = new Table(new float[] {400f});
				Table summaryTable = new Table(new float[] {600f});
				
				Table notes_cues = new Table(new float[] {200f, 400f});
				
				
				// add two cells to each of the tables plus content
				
				cueTable.addCell(new Cell()
						.add(new Paragraph("Ideas & questions")
								.setFontColor(headerColor, 1))
						.setBorder(noBorder));
				
				cueTable.addCell(new Cell()
						.add(cues)
						.setBorder(noBorder));
				
				
				noteTable.addCell(new Cell()
						.add(title)
						.setBorder(noBorder));
				
				noteTable.addCell(new Cell()
						.add(notes)
						.setBorder(noBorder));
				
				
				summaryTable.addCell(new Cell()
						.add(new Paragraph("Summary").setFontColor(headerColor, 1)
								.setBold().setCharacterSpacing(0.5f))
						.setBorder(noBorder));
				
				summaryTable.addCell(new Cell().add(summary));
				
				notes_cues.addCell(cueTable);
				notes_cues.addCell(noteTable);

				document.add(header);
				document.add(new Paragraph("\n"));
				document.add(notes_cues);
				document.add(new Paragraph("\n"));
				document.add(summaryTable);
			

				document.close();


	            fileName.close();
	            Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setContentText("File saved at: "+selectedFile.getAbsolutePath());
	        	alert.show();

	   
	        } catch (FileNotFoundException e) {
	        	Alert alert = new Alert(AlertType.ERROR);
	        	alert.setContentText("An Error Occurred while saving file");
	        	alert.show();
	        } catch (java.io.IOException e) {
	        	Alert alert = new Alert(AlertType.ERROR);
	        	alert.setContentText("An Error Occurred while saving file");
	        	alert.show();
			}
	    } else {
	    	Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("Operation cancelled!");
        	alert.show();
	    }
		
		

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
