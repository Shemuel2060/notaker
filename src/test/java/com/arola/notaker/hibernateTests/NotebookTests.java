package com.arola.notaker.hibernateTests;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.arola.notaker.entities.Notebook;
import com.arola.util.SessionUtil;

/**
 * Contains tests on the Notebook entity.
 */
public class NotebookTests {

	public static void main(String[] args) {
		NotebookTests.saveNotebooks();		
	}
	
	public static void saveNotebooks() {
		try(Session session = SessionUtil.getSession()){
			Transaction tr = session.beginTransaction();
			
			// create notebooks
			Notebook nb1 = new Notebook("Chemstry", "Refresher notes on chemistry");
			Notebook nb2 = new Notebook("Mathematics", "Refresher notes on mathematics");
			Notebook nb3 = new Notebook("Physics", "Refresher notes on physics");
			Notebook nb4 = new Notebook("Bible Study", "Notes on how to study the Bible");
			Notebook nb5 = new Notebook("Jewish Feasts", "Notes on the Jewish feasts");
			
			// save notebooks
			
			session.save(nb1);
			session.save(nb2);
			session.save(nb3);
			session.save(nb4);
			session.save(nb5);			
			
			tr.commit();
		}
	}

}
