package com.arola.notaker.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SUMMARY")
public class Summary {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="SUMMARY")
	private String contents;
	
	
	@OneToOne(fetch=FetchType.LAZY, cascade= {
			CascadeType.DETACH,CascadeType.MERGE, 
			CascadeType.PERSIST,CascadeType.REFRESH
	})
	private Note notes;


	public Summary() {} // noArgs constructor
	
	public Summary(String contents) {
		this.contents = contents;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public Note getNotes() {
		return notes;
	}


	public void setNotes(Note notes) {
		this.notes = notes;
	}


	@Override
	public String toString() {
		return "Summary [id=" + id + ", contents=" + contents + ", notes=" + notes + "]";
	}
	
	
	
	

}
