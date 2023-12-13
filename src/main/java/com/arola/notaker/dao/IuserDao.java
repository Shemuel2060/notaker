package com.arola.notaker.dao;

import java.time.LocalDate;

import com.arola.notaker.entities.User;
import com.arola.notaker.entities.User;

public interface IuserDao {
	
	/**
	 * Creates a User only if the user does not exist and persists
	 * him/her into the DB.
	 * @param name
	 * @return the created User object
	 */
	public User createUser(String name);
	
	/**
	 * Creates a user with the name and email only if
	 * the user does not exist.
	 * @param name
	 * @param email
	 * @return User object
	 */
	public User createUser(String name, String email);
	
	/**
	 * creates a User with a given name, email and password only
	 * when the user does not exist.
	 * @param name
	 * @param email
	 * @return password
	 */
	public User createUser(String name, String email, String password);
	
	
	/**
	 * retrieves a User object from the DB by his/her name.
	 * @param name
	 * @return User object
	 */
	public User findUserByName(String name);
	
	/**
	 * Retrieves a User object from the DB by his/her id.
	 * @param id
	 * @return User object
	 */
	public User findUserById(int id);
	
	
	/**
	 * updates the name of the User from the old name to 
	 * the new name.
	 * @param oldtitle
	 * @param newTitle
	 */
	public void updateUserName(String oldName, String newName);
	
	/**
	 * edits the email of an existing User. It does not create
	 * a User if the specified User by name does not exist but
	 * it creates an email for the user if the user exists but
	 * without an email. Otherwise, it just updates/edits the 
	 * already existing user email.
	 * @param name
	 * @param email
	 */
	public void editUserEmail(String name, String email);
	
	/**
	 * edits the password of an existing User. It does not create
	 * a User if the specified User by name does not exist.
	 * @param name
	 * @param password
	 */
	public void editUserPassword(String name, String password);
	
	
	/**
	 * Deletes a User by his/her name.
	 * @param name
	 */
	public void deleteUserByName(String name);
	
	
	/**
	 * Deletes a user by his/her id
	 * @param id
	 */
	public void deleteUserById(int id);
	
	
	// more methods on user notes, notebooks and reminders...
	
	
	
	

}
