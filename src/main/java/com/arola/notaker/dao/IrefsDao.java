package com.arola.notaker.dao;

import com.arola.notaker.entities.Refs;

public interface IrefsDao {
	
	/**
	 * creates a Ref specifying the title of the Ref 
	 * @param RefTitle
	 * @return Ref
	 */
	public Refs createRef(String author, String RefTitle);
	
	/**
	 * creates a Refs specifying its author, title and url
	 * @param author
	 * @param RefName
	 * @param url
	 * @return Refs 
	 */
	public Refs createRef(String author, String RefTitle, String url);
	
	
	
	/**
	 * retrieves a Refs object by its id
	 * @return Refs
	 */
	public Refs getRefById(int id);
	
	/**
	 * gets a Refs by its title
	 * @param title
	 * @return Refs 
	 */
	public Refs getRefByTitle(String title);
	
	/**
	 * gets a Refs by its author's name.
	 * @param name
	 * @return Refs
	 */
	public Refs getRefByAuthor(String name);
	
	
	/**
	 * updates an existing Refs' title by its id.
	 * @param id 
	 * @param newReferenceName
	 */
	public void updateRefTitle(int id, String newReferenceName);
	
	/**
	 * updates the name of an existing reference.
	 * @param oldReferenceName
	 * @param newReferenceName
	 */
	public void updateRefTitle(String oldReferenceName, String newReferenceName);
	

	
	/**
	 * removes a Ref by its specified id.
	 * @param id
	 */
	public void removeRef(int id);

	
	/**
	 * removes a Ref by its specified name.
	 * @param  name
	 */
	public void removeRef(String name);
	
	
	
	// more methods on user notes, notebooks and Sourcess...

}
