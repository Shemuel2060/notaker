package com.arola.notaker.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="REMINDERS")
@Data
@NoArgsConstructor
public class Reminder {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int reminderId;
	
	@Column(name="REMINDER_DATE")
	private Date reminderDate;
	
	@ManyToOne(fetch=FetchType.LAZY, 
			cascade= {CascadeType.PERSIST,
					CascadeType.MERGE, CascadeType.REFRESH, 
					CascadeType.DETACH})
	@JoinColumn(name="NOTE_ID")
	private Note note; 
	
	@OneToOne(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH, 
			CascadeType.DETACH})
	private User user;
	
	/**
	 * sets a remider for a given note
	 * @param note
	 * @param reminder
	 */
	public void setNoteReminder(Note note, Reminder reminder) {
		
	}
	
	/**
	 * deletea a reminder for a given note...
	 * @param note
	 * @param reminder
	 */
	public void deleteNoteReminder(Note note, Reminder reminder) {
		
	}

}
