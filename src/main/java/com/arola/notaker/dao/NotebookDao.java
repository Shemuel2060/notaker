package com.arola.notaker.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Notebook;
import com.arola.util.SessionUtil;

public class NotebookDao implements InoteBookDao {

	public static Session session = SessionUtil.getSession();

	@Override
	public Notebook createNotebook(String title, String description) {
		try(Session session = SessionUtil.getSession()){
			// check if notebook already exists
			Notebook nbook = findNotebook(title);

			Transaction tr = session.beginTransaction();

			if (nbook == null) { // create it and save it to DB
				nbook = new Notebook(title, description);
				session.save(nbook);
			} else {
				System.out.println("Notebook " + nbook.getTitle().toUpperCase() + " already exists.");
				return null;
			}

			tr.commit();

			return nbook;
			
		}
	
	}

	@Override
	public Notebook findNotebook(String title) {
		try(Session session = SessionUtil.getSession()){
			Query<Notebook> query = session.createQuery("from Notebook n where n.title=:tito", Notebook.class);

			query.setParameter("tito", title);
			Notebook notebook = query.uniqueResult();
			if (notebook != null) {
				return notebook;
			} else {
				return null;
			}
		}
	
	}

	@Override
	public void updateNotebook(String oldTitle, String newTitle) {

		try(Session session = SessionUtil.getSession()){
			
			Transaction tr = session.beginTransaction();
			// find notebook if its title exists
			Notebook notebook = findNotebook(oldTitle);
			// check if note exists
			if (notebook == null) { // if not there, add it with new title
				System.out.println("Sorry, notebook with title, " + oldTitle.toUpperCase() + ", does not exist");
			} else { // note already exists
				Query<?> query = session.createQuery("update Notebook set title=:newTito where " + "title=:oldTito");
				query.setParameter("oldTito", oldTitle);
				query.setParameter("newTito", newTitle);
				query.executeUpdate();
			}

			tr.commit();
		}

	}

	@Override
	public void deleteNotebook(String title) {
		try(Session session = SessionUtil.getSession()){
			// get the notebook to delete by its title
			Transaction tr = session.beginTransaction();
			
			Notebook notebook = findNotebook(title);
			// check if note exists
			if (notebook == null) { 
				System.out.println("Sorry, notebook with title, " + title.toUpperCase() + ", does not exist");
			}else {
				Query<?> query = session.createQuery("DELETE FROM Notebook nb where nb.title=:tito");
				query.setParameter("tito", title);
//				Notebook nb = (Notebook) query.uniqueResult();
				// delete the notebook
				session.delete(notebook);
				query.executeUpdate();			
			}

			tr.commit();
		}

	}

	

	public static void populateNotebooks() {
		session.beginTransaction();
		NotebookDao dao = new NotebookDao();
		Notebook nb1 = dao.createNotebook("Physics", "starter physics");
		Notebook nb2 = dao.createNotebook("Mathematics", "calculus maths");
		Notebook nb3 = dao.createNotebook("Chemical Kinetics", "molecular kinetics");
		Notebook nb4 = dao.createNotebook("Electrochem", "molecular electrochemistry");
		Notebook nb5 = dao.createNotebook("organic biomolecules", "organic chemicals of life");

		session.save(nb1);
		session.save(nb2);
		session.save(nb3);
		session.save(nb4);
		session.save(nb5);

		session.getTransaction().commit();
	} // END:: populateNotebooks()

}