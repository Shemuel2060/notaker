package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.time.LocalDate;

import org.testng.annotations.Test;

import com.arola.notaker.dao.ReminderDao;
import com.arola.notaker.entities.Reminder;

/**
 * Contains tests on the Notebook entity.
 */
public class ReminderDaoTests {
	
	ReminderDao reminderDao = new ReminderDao();
	
	@Test
	public void testcreateReminder() {		
		Reminder reminder = reminderDao.createReminder(LocalDate.of(2021, 2, 23));
		assertNotNull(reminder);
	}
	
	@Test
	public void testremoveReminderByDate() {
		LocalDate date = LocalDate.of(2021, 2, 23);
		reminderDao.removeReminderByDate(date);
		// put some assertion ... verfication here...
	}
	
	@Test
	public void testgetReminderById() {
		Reminder r = reminderDao.getReminderById(22);
		
		assertNotNull(r);
		
		System.out.println("\n"+ r.toString()+"\n");
	}
	
	@Test
	public void testupdateReminderById() {
		reminderDao.updateReminderById(21, LocalDate.of(2024, 1, 14));
		Reminder r = reminderDao.getReminderById(21);
		LocalDate date = LocalDate.of(2024, 1, 14);
		assertEquals(r.getReminderDate(), date);
		System.out.println("\nNew Date for reminder: "+r.getReminderDate()+"\n");
	}
	
	
	

}
