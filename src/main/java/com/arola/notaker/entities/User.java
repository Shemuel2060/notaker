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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USER")
@Data
@NoArgsConstructor
public class User {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId; 
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email; // may omit this..

	@OneToMany(fetch=FetchType.LAZY, cascade=
			CascadeType.ALL, mappedBy="user")
	private List<Notebook> notebooks;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,
			mappedBy="user")
	private List<Note> notes; // notes for a user

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,
			mappedBy="user")
	private List<Reminder> reminders; // reminders for a user
	/**
	 * Create user with name and password fields only.
	 * @param userName
	 * @param password
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	// OTHER METHODS
	
	/**
	 * registers a user
	 * @param name
	 * @param pass
	 * @param email
	 */
	public void registerUser(String name, String pass, String email) {
		
	}
	
	/**
	 * Performs user authentication...during login
	 * @param name
	 * @param pass
	 */
	public void loginUser(String name, String pass) {
		
	}
	
	/**
	 * add notebooks to a user..
	 * @param notebooks
	 */
	public void addNotebooks(List<Notebook> notebooks) {
		if(notebooks == null) {
			notebooks = new ArrayList<>();
		}
		this.notebooks = notebooks;
	}
	
	
	
}
