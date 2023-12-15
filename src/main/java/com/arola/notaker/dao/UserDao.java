package com.arola.notaker.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class UserDao implements IuserDao {

	public static void populateUsers() {
		try (Session session = SessionUtil.getSession()) {
			Transaction tx = session.beginTransaction();

			User user0 = new User("Michael Maseruka", "michaelM");
			User user1 = new User("Kelvin Mulindwa", "kelMul");
			User user2 = new User("Moureen Katongole", "mkatom-z");
			User user3 = new User("Azurah Katongole", "azrhk-z");
			User user4 = new User("Gabriel Sebakigye", "gabygab");
			User user5 = new User("Melody Namulindwa", "melonam");
			User user6 = new User("Harmony Other", "harmyO");

			session.save(user0);
			session.save(user1);
			session.save(user2);
			session.save(user3);
			session.save(user4);
			session.save(user5);
			session.save(user6);

			tx.commit();

		}

	}

	public static User findUser(Session session, String name) {

		Query<User> query = session.createQuery("from User u where u.userName=:name", User.class);

		query.setParameter("name", name);
		User user = query.uniqueResult();
		if (user != null) {
			return user;
		} else {
			return null;
		}

	} // END:: findNote(Session session, String title)

	@Override
	public User createUser(String name) {
		// Create a new User instance

		Session session = SessionUtil.getSession();
		User user = findUser(session, name);
		User theUser = new User();

		if (user == null) {
			// Set Note properties
			user = new User(); // first instantiate it
			user.setUserName(name);
			// Save the User to the database
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();

				// Assuming you have a Hibernate session available (session variable)
				System.out.println("saving user second time....");
				session.save(user);

				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace(); // Handle the exception appropriately in your application
			}

			theUser = user;
		} else {
			System.out.println("User already existing!");
			theUser = null; // use Logger class to log some message here...

		}
		return theUser;

	} // END:: createUser(String name)

	@Override
	public User createUser(String name, String email) {
		Session session = SessionUtil.getSession();
		User user = findUser(session, name);
		User theUser = new User();

		if (user == null) {
			// Set Note properties
			user = new User(); // first instantiate it
			user.setUserName(name);
			user.setEmail(email);
			// Save the User to the database
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();

				// Assuming you have a Hibernate session available (session variable)
				System.out.println("saving user second time....");
				session.save(user);

				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace(); // Handle the exception appropriately in your application
			}

			theUser = user;
		} else {
			System.out.println("User already existing!");
			theUser = null; // use Logger class to log some message here...

		}
		return theUser;
	} // END:: createUser(String name, String email)

	@Override
	public User createUser(String name, String email, String password) {
		Session session = SessionUtil.getSession();
		User user = findUser(session, name);
		User theUser = new User();

		if (user == null) {
			// Set Note properties
			user = new User(); // first instantiate it
			user.setUserName(name);
			user.setEmail(email);
			user.setPassword(password);
			// Save the User to the database
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();

				// Assuming you have a Hibernate session available (session variable)
				System.out.println("saving user second time....");
				session.save(user);

				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace(); // Handle the exception appropriately in your application
			}

			theUser = user;
		} else {
			System.out.println("User already existing!");
			theUser = null; // use Logger class to log some message here...

		}
		return theUser;
	}

	@Override
	public User findUserByName(String name) {
		User user;
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			Query<User> query = session.createNamedQuery("user.findByName", User.class);
			query.setParameter("name", name);

			user = query.getSingleResult();

			tr.commit();
		}

		return user;
	}

	@Override
	public User findUserById(int id) {
		User user;
		try (Session session = SessionUtil.getSession()) {
			Transaction tr = session.beginTransaction();
			Query<User> query = session.createNamedQuery("user.findById", User.class);
			query.setParameter("ID", id);

			user = query.uniqueResult();
			tr.commit();
		}

		return user;
	}

	@Override
	public void updateUserName(String oldName, String newName) {
		try (Session session = SessionUtil.getSession()) {
			session.beginTransaction();
			Query<?> query = session.createNamedQuery("user.updateName");
			query.setParameter("oldName", oldName);
			query.setParameter("newName", newName);

			query.executeUpdate();

			session.getTransaction().commit();
		}

	}

	@Override
	public void editUserEmail(String name, String email) {
		// get the email to update
		try (Session session = SessionUtil.getSession()) {
			session.beginTransaction();

			// find user
			User user = findUser(session, name);
			if (user.getEmail() != null) {
				Query<?> query = session.createNamedQuery("user.updateEmail");
				query.setParameter("uName", name);
				query.setParameter("newEmail", email);

				query.executeUpdate();
			} else { // user has no email
				user.setEmail(email);
			}
			session.getTransaction().commit();
		}

	}

	@Override
	public void editUserPassword(String name, String password) {
		// get the email to update
		try (Session session = SessionUtil.getSession()) {
			session.beginTransaction();

			// find user
			User user = findUser(session, name);
			if (user.getPassword() != null) {
				Query<?> query = session.createNamedQuery("user.updatePassword");
				query.setParameter("uName", name);
				query.setParameter("newPass", password);

				query.executeUpdate();
			} else { // user has no email
				user.setPassword(password);
			}
			session.getTransaction().commit();
		}
	}

	@Override
	public void deleteUserByName(String name) {
		try(Session session = SessionUtil.getSession()){
			Transaction tx = session.beginTransaction();
			
			Query<?> query = session.createNamedQuery("user.deleteByName");
			
			query.setParameter("user_name", name);
			
			query.executeUpdate();
			
			tx.commit();
		}

	}

	@Override
	public void deleteUserById(int id) {
		try(Session session = SessionUtil.getSession()){
			Transaction tx = session.beginTransaction();
			
			Query<?> query = session.createNamedQuery("user.deleteById");
			
			query.setParameter("user_id", id);
			
			query.executeUpdate();
			
			tx.commit();
		}
	}

//	// OTHER METHODS
//	
//		/**
//		 * registers a user
//		 * @param name
//		 * @param pass
//		 * @param email
//		 */
//		public void registerUser(String name, String pass, String email) {
//			
//		}
//		
//		/**
//		 * Performs user authentication...during login
//		 * @param name
//		 * @param pass
//		 */
//		public void loginUser(String name, String pass) {
//			
//		}
//		
//		/**
//		 * add notebooks to a user..
//		 * @param notebooks
//		 */
//		public void addNotebooks(List<Notebook> notebooks) {
//			if(notebooks == null) {
//				notebooks = new ArrayList<>();
//			}
//			this.notebooks = notebooks;
//		}
//		

}
