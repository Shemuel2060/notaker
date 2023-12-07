package com.arola.notaker.dao;

import java.time.LocalDate;

import com.arola.notaker.entities.Note;
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
	public void updateNoteTitle(String oldtitle, String newTitle);
	
	/**
	 * Deletes a note by its title.
	 * @param title
	 */
	public void deleteNoteByTitle(String title);

}
