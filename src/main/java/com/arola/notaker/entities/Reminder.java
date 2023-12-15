package com.arola.notaker.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="REMINDERS")
@Data
@NoArgsConstructor

@NamedQuery(name="reminder.deleteByDate", query="DELETE FROM Reminder r"
		+ " WHERE r.reminderDate =:remDate")
public class Reminder {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int reminderId;
	
	@Column(name="REMINDER_DATE")
	private LocalDate reminderDate;
	
	@ManyToOne(fetch=FetchType.LAZY, 
			cascade= {CascadeType.PERSIST,
					CascadeType.MERGE, CascadeType.REFRESH, 
					CascadeType.DETACH})
	@JoinColumn(name="NOTE_ID")
	private Note note; 
	
	

}
