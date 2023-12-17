package com.arola.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;
import com.arola.notaker.entities.Reminder;
import com.arola.notaker.entities.Refs;
import com.arola.notaker.entities.Tag;
import com.arola.notaker.entities.User;

public class SessionUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure()
            		.addAnnotatedClass(Notebook.class)
            		.addAnnotatedClass(User.class)
            		.addAnnotatedClass(Note.class)
            		.addAnnotatedClass(Tag.class)
            		.addAnnotatedClass(Reminder.class)
            		.addAnnotatedClass(Refs.class)
            		.buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    
    public static void main(String[] args) {
		if(SessionUtil.getSession() !=null) {
			System.out.println("SessionUtil class creates sessions sucessfully");			
		}
	}
}
