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
@Table(name="REFERENCE_SOURCES")
@Data
@NoArgsConstructor
public class Sources {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="URL")
	private String  url;
	
	@OneToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.DETACH,CascadeType.MERGE, 
			CascadeType.PERSIST,CascadeType.REFRESH
	})
	private List<Note> notes;

}
