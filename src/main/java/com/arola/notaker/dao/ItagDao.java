package com.arola.notaker.dao;

import com.arola.notaker.entities.Tag;

public interface ItagDao {
	
	/**
	 * creates a Tag specifying the name of the Tag 
	 * @param tagName
	 * @return Tag object
	 */
	public Tag createTag(String tagName);
	
	/**
	 * retrieves a Tag object
	 * @return Tag object
	 */
	public Tag getTagById(int id);
	
	/**
	 * gets a tag by its name
	 * @param name
	 * @return Tag object
	 */
	public Tag getTagByName(String name);
	
	
	/**
	 * updates an existing Tag.
	 * @param id 
	 * @param newTagName
	 */
	public void updateTagById(int id, String newTagName);
	
	/**
	 * updates the name of an existing tag.
	 * @param oldTagName
	 * @param newTagName
	 */
	public void updateTagByName(String oldTagName, String newTagName);
	/**
	 * removes a Tag by its specified id.
	 * @param id
	 */
	public void removeTagById(int id);

	
	/**
	 * removes a Tag by its specified name.
	 * @param  name
	 */
	public void removeTagByName(String name);
	
	
	
	// more methods on user notes, notebooks and Tags...

}
