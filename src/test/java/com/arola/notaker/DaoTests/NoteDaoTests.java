package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
		User user = new User("Shemuel", "isaacnewton");
		LocalDate today = LocalDate.now();
		Note n = noteDao.createNote("Chemical Kinetics", today, user);
		
	} // END:: testCreateNote()
	
	@Test
	public void testPopulateNotes() {
		NotesDao.populateNotesData();
	}
	
	@Test
	public void testGetNoteByTitle() {
		Note n = noteDao.getNoteByTitle("Chemical Kinetics");
		int userId = n.getNoteId();
		assertEquals(userId,2);
		System.out.println("Note got by title: \n"+n.toString());
	}

} // END:: NoteDaoTests class

