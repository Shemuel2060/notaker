package com.arola.notaker.dao;


import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class NotesDao implements InoteDao{
	
	
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

			tx.commit();
			NotesDao note_dao = new NotesDao();
			Note n0 = note_dao.createNote("Chemical Kinetics", today, user0);
			Note n1 = note_dao.createNote("Quantum Chemistry", yest, user1);
			Note n2 = note_dao.createNote("Electrochemistry", today, user2);
			Note n3 = note_dao.createNote("Statistical Mechanics", past, user3);
			Note n4 = note_dao.createNote("Spectroscopy", today, user4);	
			
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
	
	/* ==================== CREATING notes ====================  */

	@Override
	public Note createNote(String title, LocalDate creationDate, User user) {
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
		        System.out.println("saving user second time....");
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

	@Override
	public Note getNoteByTitle(String tito) {
		Note n;
		Transaction tr = null; // inactivate any active transactions
		try(Session session = SessionUtil.getSession()){
			tr = session.beginTransaction();
			n = findNote(session, tito);
			tr.commit();
		}
		return n;
	} // END:: getNoteByTitle(String tito)

	@Override
	public Note getNoteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNoteTitle(String oldtitle, String newTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNoteByTitle(String title) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	/* ==================== DELETING notes ====================  */
	
	

}
