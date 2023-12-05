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
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH, 
			CascadeType.REFRESH})
	@JoinColumn(name="USER_ID")
	private User user; // reference to the user who owns the notebook
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,
				mappedBy="notebook")
	private List<Note> notes; // notes in a notebooks

	/**
	 * creates a notebook with title an
	 * @param title
	 * @param description
	 */
	public Notebook(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	// getters, setters, toString and No-args constructor added by Lombok
	
	public void addUser(User user) {
		if(user == null) {
			user = new User();
		}
		this.user = user;
	}

} // END:: Notebook class.
