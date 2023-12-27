package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.arola.notaker.dao.NotebookDao;
import com.arola.notaker.entities.Notebook;

/**
 * Contains tests on the Notebook entity.
 */
public class NotebookDaoTests {
 
	NotebookDao notebookDAO = new NotebookDao();
	
	@Test
	public void testCreateNotebook() {		
		Notebook nBook = notebookDAO.createNotebook("Physics", "refresher notes in physics");
		
	}
	
	@Test
	public void testFindNotebook() {
		Notebook nb = notebookDAO.findNotebook("Physics");
		assertNotNull(nb);
		System.out.println(nb.getDescription());
	}
	
	@Test
	public void testUpdateNotebook() {
		notebookDAO.updateNotebook("Chemistry notes", "Chemistry");
		Notebook nb = notebookDAO.findNotebook("Chemistry");
		assertNotNull(nb);
	}
	
	@Test
	public void testDeleteNotebook() {
		notebookDAO.deleteNotebook("Chemistry");
		Notebook nb = notebookDAO.findNotebook("Chemistry");
		assertNull(nb);
	}
	
	
	

}
