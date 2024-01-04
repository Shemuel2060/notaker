package com.arola.notaker.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.sun.prism.paint.Color;

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

	@FXML
	private Button printNote;

	/* ============= END:: NOT YET ==================== */
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
			// add typed summary to the note

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
		// get the path where to print the pdf and its name
		File file = new File("E:\\outputPDF\\testoutput4.pdf");
		FileOutputStream fileName = null;
		try {
			fileName = new FileOutputStream(file);
			// create the document
			PdfWriter writer = new PdfWriter(fileName);
			PdfDocument pdf = new PdfDocument(writer);

			pdf.setDefaultPageSize(PageSize.A4);

			Document document = new Document(pdf);

			// testing

			System.out.println("\n>>>>>>>>>> TESTING PDF PRINTING >>>>>>>>>>\n");

			// get notes, cues and summary from the DB
			NotesDao notesDAO = new NotesDao();
			Note wantedNote = notesDAO.getNoteByTitle(currentNoteTitle.getText());

			// notes details
			Paragraph author = new Paragraph(nameLabel.getText().toUpperCase());
			Paragraph title = new Paragraph("Topic: " + currentNoteTitle.getText());
			Paragraph noteDate = new Paragraph("Printed on: " + LocalDate.now().toString());

			// notes
			Paragraph notes = new Paragraph("NOTES\n" + wantedNote.getContents() + "\n");

			// cues
			Paragraph cues = new Paragraph("IDEAS & QUESTIONS\n" + wantedNote.getCues() + "\n");

			// summary
			Paragraph summary = new Paragraph("SUMMARY\n" + wantedNote.getSummary() + "\n");

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
					.add(author)
					.setBorder(noBorder)
					.setTextAlignment(TextAlignment.CENTER)
					.setBold()
					.setItalic()
					);
			
			
			/*put title and date into another table, add it to the cell
			 * of the header's second column */
			
			float[] headerRightWidth = {400f};
			Table headerRight = new Table(headerRightWidth);
			
			headerRight.addCell(new Cell().add(title)
					.setBorder(noBorder));
			headerRight.addCell(new Cell()
					.add(new Paragraph("\n"))
					.setBorder(noBorder)
					.setWidth(2.5f));
			headerRight.addCell(new Cell().add(noteDate)
					.setBorder(noBorder));
			
			
			// add headerRight and headerLeft to header
			header.addCell(new Cell().add(headerLeft)
					.setBorderBottom(b)
					.setBorderRight(b));
			header.addCell(new Cell().add(headerRight).
					setBorderBottom(b));
			
			
			/*Next:
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
			cueTable.addCell(new Cell().add(cues).setBorder(noBorder));
			noteTable.addCell(new Cell().add(notes).setBorder(noBorder));
			summaryTable.addCell(new Cell().add(summary));
			
			notes_cues.addCell(cueTable);
			notes_cues.addCell(noteTable);

			document.add(header);
			document.add(new Paragraph("\n"));
			document.add(notes_cues);
			document.add(new Paragraph("\n"));
			document.add(summaryTable);
		

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
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
