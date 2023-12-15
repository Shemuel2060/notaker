package com.arola.notaker.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Tag;
import com.arola.util.SessionUtil;

public class TagDao implements ItagDao {

	private Session session = SessionUtil.getSession();

	public void populateTagsTable() {

		createTag("saponification");
		createTag("coding-geek");
		createTag("kingdom promises");
		createTag("second coming");
		createTag("reactive");

	}

	@Override
	public Tag createTag(String tagName) {
		Transaction tr = session.beginTransaction();
		Tag tag = new Tag(tagName);
		session.save(tag);
		tr.commit();
		return tag;
	}

	@Override
	public Tag getTagById(int id) {
		Transaction tr = session.beginTransaction();
		Query<Tag> query = session.createQuery("from Tag t where t.id=:ID", Tag.class);
		query.setParameter("ID", id);

		Tag tag = query.uniqueResult();

		tr.commit();
		return tag;
	}

	@Override
	public Tag getTagByName(String name) {
		Transaction tr = session.beginTransaction();
		Query<Tag> query = session.createQuery("from Tag t where t.tagName=:tagName", Tag.class);
		query.setParameter("tagName", name);

		Tag tag = query.uniqueResult();

		tr.commit();
		return tag;
	}

	@Override
	public void updateTagById(int id, String newTagName) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("update Tag set " + "tagName=:newName where tagId=:ID");

		query.setParameter("ID", id);
		query.setParameter("newName", newTagName);
		query.executeUpdate();
		tr.commit();
	}

	@Override
	public void updateTagByName(String oldTagName, String newTagName) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("update Tag set tagName=:newName "
				+ "where tagName=:oldName");

		query.setParameter("oldName", oldTagName);
		query.setParameter("newName", newTagName);
		query.executeUpdate();
		tr.commit();

	}

	@Override
	public void removeTagById(int id) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("delete from Tag where tagId=:ID");

		query.setParameter("ID", id);
		query.executeUpdate();
		tr.commit();
	}

	@Override
	public void removeTagByName(String name) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("delete from Tag where tagName=:NAME");

		query.setParameter("NAME", name);
		query.executeUpdate();
		tr.commit();

	}

}
