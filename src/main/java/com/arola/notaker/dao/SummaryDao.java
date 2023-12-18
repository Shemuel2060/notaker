package com.arola.notaker.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Refs;
import com.arola.notaker.entities.Summary;
import com.arola.notaker.entities.Tag;
import com.arola.util.SessionUtil;

public class SummaryDao implements IsummaryDao {
	
	private Session session = SessionUtil.getSession();

	public void populateSummaryTable() {

		createSummary("Summary on kinetics");
		createSummary("Summary on the second coming");
		createSummary("Summarized my new memos");
		createSummary("Summarized the contents from lecture 45");
		createSummary("What a disorganized summary of contents");
		
	}

	@Override
	public Summary createSummary(String contents) {
		Transaction tr = session.beginTransaction();
		Summary sum = new Summary(contents);
		session.save(sum);
		tr.commit();
		return sum;
	}

	@Override
	public Summary getSummaryById(int id) {
		Transaction tr = session.beginTransaction();
		Query<Summary> query = session.createQuery("from Summary s where s.id=:ID", 
				Summary.class);
		query.setParameter("ID", id);

		Summary sum = query.uniqueResult();

		tr.commit();
		return sum;
	}
	
	@Override
	public void editSummary(String oldContents, String newContents) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("update Summary set contents=:NEWCONTENTS "
				+ "where contents=:OLDCONTENTS");

		query.setParameter("OLDCONTENTS", oldContents);
		query.setParameter("NEWCONTENTS", newContents);
		query.executeUpdate();
		tr.commit();
		
	}

	@Override
	public void removeSummary(int id) {
		Transaction tr = session.beginTransaction();

		Query<?> query = session.createQuery("delete from Summary where id=:ID");

		query.setParameter("ID", id);
		query.executeUpdate();
		tr.commit();
		
	}

	


}
