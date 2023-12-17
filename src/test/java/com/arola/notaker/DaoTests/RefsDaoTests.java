package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.arola.notaker.dao.RefsDao;
import com.arola.notaker.entities.Refs;

public class RefsDaoTests {
	private RefsDao refsDao = new RefsDao();
	
	@Test
	public void testPopulateRefsData() {
		refsDao.populateRefsTable();		
	}
	
	@Test
	public void testCreateRef() {
		refsDao.	createRef("Unknown","Chemical Kinetics", 
				"https://www.vssut.ac.in/lecture_notes/lecture1425072667.pdf");
		
	}
	
	@Test
	public void testGetRefById() {
		Refs ref = refsDao.getRefById(3);
		assertNotNull(ref);
		System.out.println("\nRetrieved reference: "+ref.toString()+"\n");
		
	}
	
	@Test
	public void testGetRefByTitle() {
		Refs ref = refsDao.getRefByTitle("The Great Controversy");
		assertNotNull(ref);
		System.out.println("\nRetrieved reference: "+ref.toString()+"\n");
	}
	
	@Test
	public void testGetRefByAuthor() {
		Refs ref = refsDao.getRefByAuthor("Joseph Bates");
		assertEquals(ref.getId(), 1);
		System.out.println("\nRetrieved reference: "+ref.toString()+"\n");
	}
	
	@Test
	public void testUpdateRefTitleById() {
		refsDao.updateRefTitle(1, "Certainty of the second coming");
		Refs ref = refsDao.getRefByTitle("The nature of the second coming");
		assertNull(ref);		
	}
	
	@Test
	public void testUpdateRefTitleByTitle() {
		refsDao.updateRefTitle("Kinetics of reversible reactions", 
				"General concepts in chemical kinetics");
		Refs ref = refsDao.getRefByTitle("General concepts in chemical kinetics");
		assertNotNull(ref);		
	}
	
	@Test
	public void testRemoveRefById() {
		refsDao.removeRef(5);
		Refs ref = refsDao.getRefById(5);
		assertNull(ref);	
	}
	
	@Test
	public void testRemoveRefByTitle() {
		refsDao.removeRef("Quantum chemistry in soap");
		Refs ref = refsDao.getRefById(4);
		assertNull(ref);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
