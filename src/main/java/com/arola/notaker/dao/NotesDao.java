package com.arola.notaker.dao;


import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class NotesDao {
	
	/* ==================== CREATING notes ====================  */
	
	/**
	 * create a note if it does not exist in the DB. It first
	 * searches for the note and if it exists, it logs a message
	 * indicating that the note already exists, otherwise, it
	 * creates the note.
	 * 
	 * @param title
	 * @param creationDate
	 * @param user
	 * @return
	 */
	public static Note createNote(String title, LocalDate creationDate, User user) {
	    // Create a new Note instance
	   
	    Session session = SessionUtil.getSession();
	    Note note = findNote(session,title);
	    Note theNote = new Note();
	    
	    if(note == null) {
	    	// Set Note properties
	    	note = new Note(); // first instantiate it
		    note.setTitle(title);
		    note.setCreationDate(creationDate);
		    note.setUser(user); // Associate the Note with the provided User

		    // Save the Note to the database
		    Transaction transaction = null;
		    try {
		        transaction = session.beginTransaction();

		        // Assuming you have a Hibernate session available (session variable)
		        session.save(user);
		        session.save(note);

		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace(); // Handle the exception appropriately in your application
		    }

		    theNote = note;
	    }else {
	    	System.out.println("Note already existing!");
	    	theNote = null; // use Logger class to log some message here...
	    	
	    }
		return theNote; 
	} // END:: createNote(String title, LocalDate creationDate, User user)
	
	public static void populateNotesData() {
		try(Session session = SessionUtil.getSession()){
			Transaction tx = session.beginTransaction();
			
			User user0 = new User("Shemuel", "isaacnewton");
			User user1 = new User("Berith", "berithbuth");
			User user2= new User("Azurah", "az2019");
			User user3 = new User("Iona", "i-preach-20");
			User user4= new User("Asahel", "weHomeHeaven2060");
			
			LocalDate today = LocalDate.now();
			LocalDate yest = LocalDate.of(2022, 7, 14);
			LocalDate past = LocalDate.of(2021, 9, 23);
			
			
//			session.save(user0);
//			session.save(user1);
//			session.save(user2);
//			session.save(user3);
//			session.save(user4);
			tx.commit();
			
			Note n0 = NotesDao.createNote("Chemical Kinetics", today, user0);
			Note n1 = NotesDao.createNote("Quantum Chemistry", yest, user1);
			Note n2 = NotesDao.createNote("Electrochemistry", today, user2);
			Note n3 = NotesDao.createNote("Statistical Mechanics", past, user3);
			Note n4 = NotesDao.createNote("Spectroscopy", today, user4);	
			
		}
		
	}
	
	
	/* ==================== RETRIEVING notes ====================  */
	
	/**
	 * A method to find a note by title...
	 * @param session
	 * @param title
	 * @return Note
	 */
	public static Note findNote(Session session, String title) {
		
		Query<Note> query = session.createQuery(
		"from Note n where n.title=:tito",Note.class);
		
		query.setParameter("tito", title);
		Note note = query.uniqueResult();
		if(note!=null) {
			return note;
		}else {
			return null;
		}
		
	} // END:: findNote(Session session, String title)
	
	
	
	
	/* ==================== UPDATING notes ====================  */
	
	/**
	 * edit an already existing note in a notebook
	 * @return
	 */
	public Note editNote(String title, String newContent) {
		return null;
	}
	
	/**
	 * delete a given note in a given notebook
	 * @param title
	 * @param notebook
	 */
	public void deleteNote(String title, Notebook notebook) {
		
	}
	
	
	
	
	
	
	
	/* ==================== DELETING notes ====================  */
	
	

}
