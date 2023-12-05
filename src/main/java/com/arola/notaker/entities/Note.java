package com.arola.notaker.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NOTE")
@Data
@NoArgsConstructor
public class Note {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int noteId;
	
	@Column(name="NOTE_TITLE")
	private String title;
	
	@Column(name="NOTE_CONTENTS")
	private String contentS;
	
	@Column(name="NOTE_CREATIONDATE")
	private Date creationDate;
	
	@Column(name="NOTE_LASTMODIFICATIONDATE")
	private Date lastModifiedDate;
	
	
	@OneToOne(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name="NOTEBOOK_ID")
	private Notebook notebook; // notebook to which note belongs
	
	
	@ManyToOne(fetch=FetchType.LAZY,
	cascade= {CascadeType.DETACH,CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="USERS_ID")
	private User user; // user who owns the 

	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="REMINDER_ID")
	private Reminder reminder;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "NOTE_TAGS", joinColumns = @JoinColumn(name = "NOTE_ID"), 
								   inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
	private Set<Tag> tags;
	
	/**
	 * creates a new note within a notebook...
	 * @param title
	 * @param contents
	 * @return
	 */
	public Note createNote(String title, String contents) {
		return null; // for now...
	}
	
	/**
	 * edit an already existing note in a notebook
	 * @return
	 */
	public Note editNote(String title, String newContent) {
		return null;
	}
	
	/**
	 * delete a given note in a given notebook
	 * @param title
	 * @param notebook
	 */
	public void deleteNote(String title, Notebook notebook) {
		
	}

}
