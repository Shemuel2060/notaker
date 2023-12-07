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
	
	/* ===== creating notebooks ===== */
	
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
	
	
	
	/* ===== updating notebooks ===== */
	
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
	
	/* ===== retrieving notebooks ===== */
	
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
	
	
	/* ===== deleting notebooks ===== */
	
	public static void deleteNotebookByTitle(Session session, String title) {
		// get the notebook to delete by its title
		Transaction tr = session.beginTransaction();
		
		Query<Notebook> query = session.createQuery("from Notebook nb"
				+ " where nb.title=:tito", Notebook.class);
		query.setParameter("tito",title);
		Notebook nb = query.uniqueResult();
		// delete the notebook
		session.delete(nb);
		query.executeUpdate();	
		
		tr.commit();
	} // END::deleteNotebookByTitle(Session session, String title)
	
	public static void populateNotebooks() {
		session.beginTransaction();
		
		Notebook nb1 = NotebookDao.createSingleNotebook(session, "Physics", "starter physics");
		Notebook nb2 = NotebookDao.createSingleNotebook(session, "Mathematics", "calculus maths");
		Notebook nb3 = NotebookDao.createSingleNotebook(session, "Chemical Kinetics", 
				"molecular kinetics");
		Notebook nb4 = NotebookDao.createSingleNotebook(session, "Electrochem", 
				"molecular electrochemistry");
		Notebook nb5 = NotebookDao.createSingleNotebook(session, 
				"organic biomolecules", "organic chemicals of life");
		
		session.save(nb1);
		session.save(nb2);
		session.save(nb3);
		session.save(nb4);
		session.save(nb5);
		
		session.getTransaction().commit();
	} // END:: populateNotebooks()
	
	
}