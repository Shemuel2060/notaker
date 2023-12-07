package com.arola.notaker.DaoTests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.arola.notaker.dao.NotebookDao;
import com.arola.notaker.entities.Notebook;
import com.arola.util.SessionUtil;

/**
 * Contains tests on the Notebook entity.
 */
public class NotebookDaoTests {
 
//	NotebookDao notebookDAO = new NotebookDao();
	
	
	@Test
	public void saveSingleNotebookTest() {		
			
			
			Notebook nb = NotebookDao.createSingleNotebook(NotebookDao.session, "Chemistry", "My chem notes");
			
			String actualTitle = nb.getTitle();
			
			assertEquals(actualTitle, "Chemistry");
			// check the count in the DB and test with that..
	} // END:: saveSingleNotebookTest()
	
	@Test
	public void getNotebookByTitleTest() {
		Notebook nb = NotebookDao.getNotebookByTitle(NotebookDao.session, "Chemistry");
		
		assertNotNull(nb);
		
	}
	
	@Test
	public void editNotebookTitleTest() {
		Notebook nb = NotebookDao.getNotebookByTitle(NotebookDao.session, "Chemistry");
		NotebookDao.editNotebookTitle( NotebookDao.session, nb.getTitle(), "Organic Chemistry");
		assertEquals(nb.getTitle(),"Organic Chemistry");
	}
	
	@Test
	public void deleteNotebookByTitleTest() {
		Notebook nb = NotebookDao.getNotebookByTitle(NotebookDao.session, "Chemistry");
		NotebookDao.deleteNotebookByTitle(NotebookDao.session, "Chemistry");
		assertNull(nb.getTitle());
	}
	
	@BeforeTest
	public void populaNotebooksTest() {
		NotebookDao.populateNotebooks();
	}

}
