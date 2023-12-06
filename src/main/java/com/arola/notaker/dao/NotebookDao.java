package com.arola.notaker.dao;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Notebook;
import com.arola.util.SessionUtil;

public class NotebookDao {
	

	public static Session session = SessionUtil.getSession();
	
	/**
	 * method creates a single notebook during a transaction 
	 * and saves it into the DB.
	 * @param session
	 * @param title
	 * @param description
	 * @return created notebook
	 */
	public static Notebook createSingleNotebook(Session session, 
			String title, String description) {
		
		
			Transaction tr = session.beginTransaction();
			
			Notebook notebook = new Notebook(title, description);
			
			session.save(notebook);
			tr.commit();
			
			return notebook;
		
	} // END:: createSingleNotebook(Session session)
	
	
	/**
	 * allows user to edit a notebook's title and description.
	 */
	public static void editNotebookTitle( Session session, String oldTitle, String newTitle) {
		
		Notebook nb = getNotebookByTitle(session, oldTitle);
		
		Transaction tr = session.beginTransaction();
		
		if(nb.getTitle()==oldTitle) {				
			nb.setTitle(newTitle);	
		}
			
		tr.commit();
			
			
		
	} // END:: editNotebookTitle( String oldTitle, String newTitle)
	
	public static Notebook getNotebookByTitle(Session session, String title) {
		
		Notebook notebook = new Notebook();
		// query a notebook
		Transaction tr = session.beginTransaction();
		Query<Notebook> query = session.createQuery("from Notebook nb"
				+ " where nb.title=:tito", Notebook.class);
		
		query.setParameter("tito", title);
		
		notebook = query.uniqueResult();
		tr.commit();
		
		return notebook;
	} // END:: getNotebookByTitle(Session session, String title)
	
	
}