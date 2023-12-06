package com.arola.notaker.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NOTEBOOK")
@Data
@NoArgsConstructor
public class Notebook {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int notebookId;
	
	@Column(name="TITLE", unique=true)
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	public Notebook(String subject, String description) {
		this.title = subject;
		this.description = description;	
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH, 
			CascadeType.REFRESH})
	@JoinColumn(name="USER_ID")
	private User user; // reference to the user who owns the notebook
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,
				mappedBy="notebook")
	private List<Note> notes; // notes in a notebooks
	
	// getters, setters, toString and No-args constructor added by Lombok
	
	public void addUser(User user) {
		if(user == null) {
			user = new User();
		}
		this.user = user;
	}
	
	public Notebook addNotebook() {
		
		return null;
	}
	
	
} // END:: Notebook class.
