package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.arola.notaker.dao.TagDao;
import com.arola.notaker.entities.Tag;

/**
 * Contains tests on the Notebook entity.
 */
public class TagDaoTests {
	private TagDao tagDao = new TagDao();
	
	@Test
	public void tagSetup() {
		tagDao.populateTagsTable();		
	}
	
	@Test
	public void testcreateTag() {		
		Tag tag = tagDao.createTag("kingdom promises");
		assertNotNull(tag);
	}
	
	@Test
	public void testgetTagById() {
		Tag tag = tagDao.getTagById(31);
		assertNotNull(tag);
		System.out.println("\nFound tag: "+tag.getTagName()+"\n");
	}
	
	@Test
	public void testgetTagByName() {
		Tag tag = tagDao.getTagByName("Saponification");
		assertNotNull(tag);
		System.out.println("\nFound tag ID: "+tag.getTagId()+"\n");
	}
	
	@Test
	public void testupdateTagById() {
		tagDao.updateTagById(30,"revisting chemistry");
		Tag tag = tagDao.getTagById(30);
		assertEquals(tag.getTagName(),"revisting chemistry");
	}
	
	@Test
	public void testupdateTagByName() {
		tagDao.updateTagByName("revisting chemistry","lab chem");
		Tag tag = tagDao.getTagByName("lab chem");
		assertNotNull(tag);
	}
	
	@Test
	public void testremoveTagById() {
		tagDao.removeTagById(35);
		Tag tag = tagDao.getTagByName("reactive");
		assertNull(tag);
	}
	
	@Test
	public void testremoveTagByName() {
		tagDao.removeTagByName("kingdom promises");
		Tag tag = tagDao.getTagByName("kingdom promises");
		assertNull(tag);
	}
	
	
	

}
