package com.arola.notaker.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Refs;
import com.arola.notaker.entities.Tag;
import com.arola.util.SessionUtil;

public class RefsDao implements IrefsDao {
	
	private Session session = SessionUtil.getSession();

	public void populateRefsTable() {

		createRef("Ellen G. White","The Great Controversy");
		createRef("Kineticker1","Kinetics of reversible reactions");
		createRef("Quantist 1","Quantum chemistry in soap");
		createRef("Electro Author1","Electrochemical soap");
		createRef("Spectroscoper1","Nature of spectra deviation in different soaps");
	}

	@Override
	public Refs createRef(String author, String RefName) {
		Transaction tr = session.beginTransaction();
		Refs ref = new Refs(author, RefName);
		session.save(ref);
		tr.commit();
		return ref;
	}
	

	@Override
	public Refs createRef(String author, String RefTitle, String url) {
		Transaction tr = session.beginTransaction();
		Refs ref = new Refs(author, RefTitle, url);
		session.save(ref);
		tr.commit();
		return ref;
	}

	@Override
	public Refs getRefById(int id) {
		Transaction tr = session.beginTransaction();
		Query<Refs> query = session.createQuery("from Refs r where r.id=:ID", Refs.class);
		query.setParameter("ID", id);

		Refs ref = query.uniqueResult();

		tr.commit();
		return ref;
	}

	@Override
	public Refs getRefByTitle(String title) {
		Transaction tr = session.beginTransaction();
		Query<Refs> query = session.createQuery("from Refs r where r.title=:TITLE", 
				Refs.class);
		query.setParameter("TITLE", title);

		Refs ref = query.uniqueResult();

		tr.commit();
		return ref;
	}
	
	@Override
	public Refs getRefByAuthor(String name) {
		Transaction tr = session.beginTransaction();
		Query<Refs> query = session.createQuery("from Refs r where r.author=:NAME", 
				Refs.class);
		query.setParameter("NAME", name);

		Refs ref = query.uniqueResult();

		tr.commit();
		return ref;
	}

	@Override
	public void updateRefTitle(int id, String newReferenceTitle) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("update Refs set title=:NEWTITLE where "
				+ "id=:ID");

		query.setParameter("ID", id);
		query.setParameter("NEWTITLE", newReferenceTitle);
		query.executeUpdate();
		tr.commit();
	}

	@Override
	public void updateRefTitle(String oldReferenceTitle, String newReferenceTitle) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("update Refs set title=:NEWTITLE where "
				+ "title=:OLDTITLE");

		query.setParameter("OLDTITLE", oldReferenceTitle);
		query.setParameter("NEWTITLE", newReferenceTitle);
		query.executeUpdate();
		tr.commit();

	}

	@Override
	public void removeRef(int id) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("delete from Refs where id=:ID");

		query.setParameter("ID", id);
		query.executeUpdate();
		tr.commit();

	}

	@Override
	public void removeRef(String title) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("delete from Refs where title=:TITLE");

		query.setParameter("TITLE", title);
		query.executeUpdate();
		tr.commit();
	}


}
