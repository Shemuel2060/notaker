package com.arola.notaker.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TAGS")
@Data
@NoArgsConstructor
public class Tag {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int tagId;
	
	@Column(name ="TAGS")
	private String tagName;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH},
			mappedBy="tags")
	private Set<Note> notes;

	/**
	 * Creates a new tag
	 * @param tagName
	 */
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	
	
	
	
	
	
	
}
