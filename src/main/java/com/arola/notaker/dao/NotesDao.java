package com.arola.notaker.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class NotesDao implements InoteDao {

	public static void populateNotesData() {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			User user0 = new User("Shemuel", "isaacnewton");
			User user1 = new User("Berith Milcah Katongole", "berithbuth");
			User user2 = new User("Kizito Ibrahim", "kz2019");
			User user3 = new User("Iona Katongole", "i-preach-20");
			User user4 = new User("Asahel Katongole", "weHomeHeaven2060");

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

	/* ==================== RETRIEVING notes ==================== */

	/**
	 * A method to find a note by title...
	 * 
	 * @param session
	 * @param title
	 * @return Note
	 */
	public static Note findNote(Session session, String title) {

		Query<Note> query = session.createQuery("from Note n where n.title=:tito", Note.class);

		query.setParameter("tito", title);
		Note note = query.uniqueResult();
		if (note != null) {
			return note;
		} else {
			return null;
		}

	} // END:: findNote(Session session, String title)

	/* ==================== CREATING notes ==================== */

	@Override
	public Note createNote(String title, LocalDate creationDate, String userName) {
		Session session = SessionUtil.getSession();
		creationDate = LocalDate.now();

		// Check if the user exists
		User existingUser = UserDao.findUser(session, userName);

		session.beginTransaction();
		if (existingUser == null) { // If the user doesn't exist, create a new user
			existingUser = new User(userName);
			session.save(existingUser);
		}

		// Create a new note
		Note newNote = new Note();
		newNote.setTitle(title);
		newNote.setCreationDate(creationDate);
		newNote.setUser(existingUser);

		// Save the note
		session.save(newNote);

		session.getTransaction().commit();
		return newNote;

	} // END:: createNote(String title, LocalDate creationDate, String content)

	@Override
	public Note createNote(String title, LocalDate creationDate, String userName, String notebookName,
			String notebookDesc) {
		Session session = SessionUtil.getSession();
		creationDate = LocalDate.now();

		// Check if the user exists
		User existingUser = UserDao.findUser(session, userName);

		session.beginTransaction();
		if (existingUser == null) { // If the user doesn't exist, create a new user
			existingUser = new User(userName);
			session.save(existingUser);
		}
		
		// check if the notebook exists
		NotebookDao notebookDAO = new NotebookDao();
		Notebook existingNotebook = notebookDAO.findNotebook(notebookName);
		
		if(existingNotebook == null) { // notebook does note exist
			existingNotebook = new Notebook(notebookName, notebookDesc);
			existingNotebook.setUser(existingUser); // link user to notebook
			session.save(existingNotebook);			
		}

		// Create a new note
		Note newNote = new Note();
		newNote.setTitle(title);
		newNote.setCreationDate(creationDate);
		newNote.setUser(existingUser); // link user to note
		newNote.setNotebook(existingNotebook); // link note to notebook
		
		// set empty contents
		newNote.setContents("");
		newNote.setSummary("");
		newNote.setCues("");

		// Save the note
		session.save(newNote);

		session.getTransaction().commit();
		return newNote;
	}

	@Override
	public Note getNoteByTitle(String tito) {
		
		Note n = null;
		Transaction tr = null; // in-activate any active transactions
		try(Session session = SessionUtil.getSession()) {
			
			tr = session.beginTransaction();
			n = findNote(session, tito);
			tr.commit();
			

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return n;
		
	} // END:: getNoteByTitle(String tito)

	@Override
	public Note getNoteById(int identity) {
		Session session = SessionUtil.getSession();
		Query<Note> query = session.createQuery("from Note n where n.noteId=:id", Note.class);

		query.setParameter("id", identity);
		Note note = query.uniqueResult();
		if (note != null) {
			return note;
		} else {
			return null;
		}

	} // END:: getNoteById(int id)

	/* ==================== UPDATING notes ==================== */

	@Override
	public void updateNoteTitle(String oldTitle, String newTitle, LocalDate date) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, oldTitle);
			// check if note exists
			if (note == null) { // if not there, add it with new title
				String contents = "e.g. put notes contents here....";
				LocalDate today = LocalDate.now();
				note = createNote(oldTitle, today, contents);
			} else { // note already exists
				Query<?> query = session.createQuery("update Note set title=:newTito where " + "title=:oldTito");
				query.setParameter("oldTito", oldTitle);
				query.setParameter("newTito", newTitle);
				query.executeUpdate();
			}

			tr.commit();
		}
	} // END:: updateNoteTitle(String oldTitle, String newTitle)

	@Override
	public void editNoteContent(String noteTitle, String newContent) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, noteTitle);
			// check if note exists
			if (note == null) { // if not there, add it with new title
				System.out.println("Note with title: " + noteTitle + " does not exist");
			} else { // note already exists
				Query<?> query = session
						.createQuery("update Note set contents=:noteContents where " + "title=:noteTitle");
				query.setParameter("noteTitle", noteTitle);
				query.setParameter("noteContents", newContent);
				query.executeUpdate();
			}

			tr.commit();
		}

	}
	
	@Override
	public void editNoteSummary(String title, String newSummary) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, title);
			// check if note exists
			if (note != null) { 
				Query<?> query = session
						.createQuery("update Note set summary=:noteSummary where " 
				+ "title=:noteTitle");
				query.setParameter("noteTitle", title);
				query.setParameter("noteSummary", newSummary);
				query.executeUpdate();
			}

			tr.commit();
		}
		
	}

	@Override
	public void editNoteCues(String title, String newCues) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, title);
			// check if note exists
			if (note != null) { 
				Query<?> query = session
						.createQuery("update Note set cues=:noteCues where " 
				+ "title=:noteTitle");
				query.setParameter("noteTitle", title);
				query.setParameter("noteCues", newCues);
				query.executeUpdate();
			}

			tr.commit();
		}
		
	}
	
	/* ==================== DELETING notes ==================== */

	@Override
	public void deleteNoteByTitle(String noteTitle) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, noteTitle);
			// check if note exists
			if (note == null) { // if not there, add it with new title
				System.out.println("Note with title: " + noteTitle + " does not exist");
			} else { // note already exists
				session.delete(note);
			}

			tr.commit();
		}

	}

	@Override
	public void deleteNoteContents(String noteTitle, String noteContents) {
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			// find note if its title exists
			Note note = findNote(session, noteTitle);
			// check if note exists
			if (note == null) { // if not there, add it with new title
				System.out.println("Note with title: " + noteTitle + " does not exist");
			} else { // note already exists
				note.setContents(null);
			}
			tr.commit();
		}

	}

	@Override
	public Note createNote(String title, LocalDate creationDate, User user) {
		// TODO Auto-generated method stub
		return null;
	}



}
