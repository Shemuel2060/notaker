package com.arola.notaker.dao;

import java.time.LocalDate;

import com.arola.notaker.entities.Reminder;

public interface IreminderDao {
	
	/**
	 * creates a reminder specifying the date when reminder 
	 * does the reminding...
	 * @param remindDate
	 * @return Reminder object
	 */
	public Reminder createReminder(LocalDate remindDate);
	
	/**
	 * retrieves a reminder object
	 * @return Reminder object
	 */
	public Reminder getReminderById(int id);
	
	
	/**
	 * updates an existing reminder.
	 */
	public void updateReminderById(int id, LocalDate date);
	
	/**
	 * removes a reminder from a specified note.
	 * @param note
	 */
	public void removeReminderByDate(LocalDate date);

	
	
	
	// more methods on user notes, notebooks and reminders...
	
}
