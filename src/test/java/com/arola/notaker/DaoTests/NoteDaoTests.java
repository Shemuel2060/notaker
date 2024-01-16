package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.arola.notaker.dao.NotesDao;
import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class NoteDaoTests {
	Session session;
	NotesDao noteDao = new NotesDao();
	
	@BeforeMethod
	public void setUp() {
		session = SessionUtil.getSession();
		
		
	} // END:: setUp() - runs before every test method.
	
	@AfterMethod
	public void shutDown() {
		session.close();		
	}
	
	
	@Test
	public void testCreateNote() {
		User user = new User("Aunt Jo", "josephineJos2999");
		LocalDate today = LocalDate.now();
		Note n = noteDao.createNote("The Beast Proved", today, user);
		
	} // END:: testCreateNote()
	
	
	@Test
	public void testCreateNote2() {
		String userName = "Azurah";
		
		Note n = noteDao.createNote("Kinetics", LocalDate.now(), userName);
		assertEquals(n.getUser().getUserName().length(), userName.length());
	}
	
	@Test
	public void testCreateNote3() {
		Note n = noteDao.createNote("note title", LocalDate.now(),
				"Iona", "My New Notebook2","note-notebook linking2");
	}
	
	@Test // #1
	public void testPopulateNotes() {
		NotesDao.populateNotesData();
	}
	
	@Test // #2
	public void testGetNoteByTitle() {
		Note n = noteDao.getNoteByTitle("Key Events");
		int noteId = n.getNoteId();
		assertEquals(noteId,6);
		System.out.println(n.getCreationDate().toString());
	} // END:: testGetNoteByTitle() 
	
	@Test // #3
	public void testGetNoteById() {
		Note n = noteDao.getNoteById(2);
		String noteTitle = n.getTitle();
		assertEquals(noteTitle,"Chemical Kinetics");
	} // END:: testGetNoteByTitle() 
	
	@Test // #4
	public void testUpdateNoteTitle() {
		
		noteDao.updateNoteTitle("Java Data Programming","DB Management", LocalDate.now());
		Note note = noteDao.getNoteByTitle("Java Data Programming");
		assertNotNull(note.getTitle());
	}
	
	@Test // #5
	public void testeditNoteContent() {
		String newContents = "Database design is a skill that involves "
				+ "coming up with coherent Databases for managing a company's "
				+ "data. It focuses on ensuring that data is consistent and  "
				+ "all redundancies are avoided. The data should also be in  "
				+ "highly integral relational tables that have coherent "
				+ "mappings between them. Skills such are normalization, "
				+ "and others are curcial in ensuring that this happens.";
		
		noteDao.editNoteContent("Seven Churches", newContents);
		Note n = noteDao.getNoteByTitle("Seven Churches");
		assertEquals(n.getContents().length(), newContents.length());
	}
	
	@Test // #6
	public void testDeleteNoteByTitle() {
		Note n = noteDao.getNoteByTitle("Java Data Programming");
		assertNotNull(n);
		noteDao.deleteNoteByTitle("Java Data Programming");
		System.out.println("\nTrying to retrieve note after deleting it\n");
		n = noteDao.getNoteByTitle("Java Data Programming");
		assertNull(n);
		System.out.println("\n...Note successfully deleted...\n");
	}
	
	@Test
	public void testdeleteNoteContents() {
		Note n = noteDao.getNoteByTitle("Java Data Programming");
		String title = n.getTitle();
		String contents = n.getContents();
		
		noteDao.deleteNoteContents(title, contents);
		n = noteDao.getNoteByTitle("Java Data Programming");
		assertNull(n.getContents());
		
	}
	
	@Test
	public void testpopulateNotesData() {
		NotesDao.populateNotesData();
	}
	
	@Test
	public void testGetAllNotes() {
		List<Note> notes = noteDao.getAllNotes();
		
		for(Note n:notes) {
			System.out.println(n.getTitle());
		}
	}

} // END:: NoteDaoTests class

