package com.arola.notaker.entities;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="REFS")
@Data
@NoArgsConstructor
public class Refs {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="URL")
	private String  url;
	
	@Column(name="AUTHOR")
	private String author; /*I more than one authors for a given
							reference, take the main author.*/
	
	@OneToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.DETACH,CascadeType.MERGE, 
			CascadeType.PERSIST,CascadeType.REFRESH
	})
	private List<Note> notes;
	
	public Refs(String author, String refName) {
		this.author = author;
		this.title = refName;
	}

	public Refs(String author, String refTitle, String url) {
		this.author = author;
		this.title = refTitle;
		this.url = url;
	}

}
