package com.arola.notaker.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NOTE")
@Data
@NoArgsConstructor
@AllArgsConstructor 
@NamedQuery(name="byTitle",  query = "from Note n where n.title=:tito")
public class Note {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int noteId;
	
	@Column(name="NOTE_TITLE")
	private String title;
	
	@Column(name="NOTE_CONTENTS")
	private String contents;
	
	@Column(name="NOTE_CREATIONDATE")
	private LocalDate creationDate;
	
	@Column(name="NOTE_LASTMODIFICATIONDATE")
	private LocalDate lastModifiedDate;
	
	
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "notes",
			cascade= {
					CascadeType.DETACH,CascadeType.MERGE, 
					CascadeType.PERSIST,CascadeType.REFRESH
			})
    private List<Sources> referenced;

	public Note(String title, LocalDate creationDate, User user) {
		this.title = title;
		this.creationDate = creationDate;
		this.user = user;
	}
	
	

	
	
	
	

}
