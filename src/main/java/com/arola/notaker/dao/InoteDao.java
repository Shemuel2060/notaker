package com.arola.notaker.dao;

import java.time.LocalDate;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Reminder;
import com.arola.notaker.entities.User;

public interface InoteDao {
	
	/**
	 * Creates a note and persists it into the DB plus the 
	 * user who created it and its creation date.
	 * @param title
	 * @param creationDate
	 * @param user
	 * @return the created note object
	 */
	public Note createNote(String title, LocalDate creationDate, User user);
	
	/**
	 * creates a note with a given title and content
	 * @param title
	 * @param creationDate
	 * @return Note
	 */
	public Note createNote(String title, LocalDate creationDate, String content);
	
	
	/**
	 * retrieves a Note object from the DB by its title.
	 * @param tito
	 * @return Note object
	 */
	public Note getNoteByTitle(String tito);
	
	/**
	 * Retrieves a Note object from the DB by its id.
	 * @param id
	 * @return
	 */
	public Note getNoteById(int id);
	
	
	/**
	 * updates the title of the note from the old title to 
	 * the new title.
	 * @param oldtitle
	 * @param newTitle
	 */
	public void updateNoteTitle(String oldtitle, String newTitle, LocalDate date);
	
	/**
	 * edits the contents of an existing note. It does not create
	 * a note if the specified note by title does not exist.
	 * @param title
	 * @param newContent
	 */
	public void editNoteContent(String title, String newContent);
	
	/**
	 * Deletes a note by its title.
	 * @param title
	 */
	public void deleteNoteByTitle(String title);
	
	/**
	 * deletes contents of a note. 
	 * @param noteContents
	 */
	public void deleteNoteContents(String title, String noteContents);
	
	// OTHER METHODS...
	
	
//	public Reminder getReminder(Note note);
//	public void addReminder(Note note);
//	public User getUser(Note note);
//	public void addUser(Note note);
//	public Sources getSources(Note note);
//	public void addSources(Note note);
//	public Tags getTags(Note note);
//	public void addTags(Note note);

}
