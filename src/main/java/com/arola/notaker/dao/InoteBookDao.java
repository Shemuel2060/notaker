package com.arola.notaker.dao;

import com.arola.notaker.entities.Notebook;

public interface InoteBookDao {
	
	/**
	 * checks if notebook exists using its title and the findNote(). 
	 * If existing, prints out that it already exists, otherwise
	 * creates it.
	 * @param title
	 * @param description
	 * @return Notebook object
	 */
	public Notebook createNotebook(String title, String description);
	
	/**
	 *Finds a notebook by its title. If exists, returns the 
	 *notebook; otherwise returns null
	 * 
	 * @param title
	 * @return Notebook object or null
	 */
	public Notebook findNotebook(String title);
	
	/**
	 * checks if notebook exists, and if so, updates its
	 * title, otherwise, messages that note does not exist.
	 * @param oldtitle
	 * @param newtitle
	 */
	public void updateNotebook(String oldtitle, String newtitle);
	
	public void deleteNotebook(String title);

}
