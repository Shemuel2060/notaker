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
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USER")
@Data
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "user.findAllUsers",query = "from User u"),
	@NamedQuery(name = "user.findByName",query = "from User u where u.userName=:name"),
	@NamedQuery(name = "user.findById",query = "from User u where u.userId=:ID"),
	@NamedQuery(name="user.updateName", query="update User set userName=:newName "
			+ "where userName=:oldName"),
	@NamedQuery(name="user.updateEmail", query="update User set email=:newEmail "
			+ "where userName=:uName"),
	@NamedQuery(name="user.updatePassword", query="update User set password=:newPass "
			+ "where userName=:uName"),
	@NamedQuery(name="user.deleteByName", query="DELETE FROM User u WHERE u.userName = "
			+ ":user_name"),
	@NamedQuery(name="user.deleteById", query="DELETE FROM User u WHERE u.userId = "
			+ ":user_id")
	})		
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
	
	
	
	
}
