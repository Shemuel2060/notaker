package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.arola.notaker.dao.SummaryDao;
import com.arola.notaker.entities.Summary;

public class SummaryDaoTests {
	private SummaryDao sumsDao = new SummaryDao();
	
	@BeforeClass
	public void testPopulateRefsData() {		sumsDao.populateSummaryTable();		
	}
	
	
	@Test
	public void testcreateSummary() {
		sumsDao.createSummary("@BeforeClass and @AfterClass used to "
				+ "setup data and clean up needed for all tests in a "
				+ "given test class.");
	}
	
	@Test
	public void testgetSummaryById() {
		Summary sum = sumsDao.getSummaryById(6);
		assertNotNull(sum);
		System.out.println(sum.toString());
	}
	
	@Test
	public void testremoveSummary() {
		sumsDao.removeSummary(1);
		Summary sum = sumsDao.getSummaryById(1);
		assertNull(sum);
	}
	
}










