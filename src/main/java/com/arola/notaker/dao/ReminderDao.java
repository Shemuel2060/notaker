package com.arola.notaker.dao;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Reminder;
import com.arola.util.SessionUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReminderDao implements IreminderDao{
	
	private Session session = SessionUtil.getSession();

	@Override
	public Reminder createReminder(LocalDate remindDate) {
		
		try(Session session = SessionUtil.getSession()){
			Transaction t = session.beginTransaction();
			
			Reminder reminder = new Reminder();
			
			reminder.setReminderDate(remindDate);
			
			session.save(reminder);
			
			t.commit();
			
			return reminder;
		}
		
			
		}
	

	@Override
	public void removeReminderByDate(LocalDate date) {
		try(Session session = SessionUtil.getSession()){
			session.beginTransaction();
			Query<?> query = session.createNamedQuery("reminder.deleteByDate");
			query.setParameter("remDate", date);
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}
		
	}

	@Override
	public Reminder getReminderById(int id) {
		Query<Reminder> query = session.createQuery("from Reminder r where"
				+ " r.reminderId=:ID", Reminder.class);
		
		query.setParameter("ID", id);
		Reminder reminder = query.uniqueResult();
		
		return reminder;
	}


	@Override
	public void updateReminderById(int id, LocalDate date) {
		
		session.beginTransaction();
		// query reminder for update
		Query<?> query = session.createQuery("update Reminder set "
				+ "reminderDate=:newDate where reminderId=:ID");
		
		query.setParameter("ID", id);
		query.setParameter("newDate", date);
		
		query.executeUpdate();
		
		session.getTransaction().commit();
		
	}

}
