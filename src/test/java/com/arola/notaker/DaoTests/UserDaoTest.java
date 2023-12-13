package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.hibernate.Session;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.arola.notaker.dao.UserDao;
import com.arola.notaker.entities.User;
import com.arola.util.SessionUtil;

public class UserDaoTest {
	
	
	UserDao userDao = new UserDao();
	Session session = SessionUtil.getSession();
	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1, groups="setup")
	public void testPopulateUsers() {
		UserDao.populateUsers();
	}
	
	@Test(priority = 2, groups="find-user, setup")
	public void testFindUser() {
		User user = UserDao.findUser(session,"Kelvin Mulindwa");
		soft.assertEquals(user.getNotes().isEmpty(),true, "this fails");
		soft.assertEquals(user.getUserId(), 19);
	}
	
	
	@Test(priority = 3, groups="create-user")
	public void testCreateUser() {
		userDao.createUser("Meridith Katongole");
	}
	
	@Test(priority = 4, groups="create-user")
	public void testCreaterUserWithEmail() {
		userDao.createUser("Monira Mange", "monman@gmail.com");
	}
	
	@Test(priority = 5, groups="create-user")
	public void testCreateUserWithEmailAndPassword() {
		userDao.createUser("Aisetu Namwanje","ais@arola.com","ais_123_nam");
	}
	
	@Test(priority = 6, groups="find-user", enabled=false)
	public void testFindUserByName() {
		User user = userDao.findUserByName("Azurah");
		assertNotNull(user);
	}
	
	@Test(priority = 7, groups="find-user")
	public void testFindUserById() {
		User user = userDao.findUserById(3);
		assertNotNull(user);
	}
	
	@Test(priority = 8, groups="update-user")
	public void testupdateUserName() {
		userDao.updateUserName("Azurah Katongole", "Azurah Milcah Katongole");
		User user = userDao.findUserByName("Azurah Milcah Katongole");
		assertNotNull(user);
	}
	
	@Test 
	public void testeditUserEmail() {
		System.out.print("\nTesting when user has an email originally\n");
		User user = userDao.findUserByName("Monira Mange");
		String oldEmail = user.getEmail();
		soft.assertEquals(oldEmail,"monman@gmail.com");
		userDao.editUserEmail("Monira Mange", "munira.arola@info.com");
		String newEmail = user.getEmail();
		soft.assertEquals(newEmail,"munira.arola@info.com");
		
		System.out.print("\nTesting when user has no email originally\n");
		user = userDao.findUserByName("Kelvin Mulindwa");
		oldEmail = user.getEmail();
		soft.assertEquals(oldEmail,null);
		userDao.editUserEmail("Kelvin Mulindwa", "kelm@arola.org");
		newEmail = user.getEmail();
		soft.assertEquals(newEmail,"kelm@arola.org");
	}
	
	@Test
	public void testeditUserPassword() {
		System.out.print("\nTesting when user has a password originally\n");
		User user = userDao.findUserByName("Gabriel Sebakigye");
		String oldPass = user.getPassword();
		soft.assertEquals(oldPass,"gabygab");
		userDao.editUserPassword("Gabriel Sebakigye", "Ga-12?<(*by");
		String newPass = user.getPassword();
		soft.assertEquals(newPass,"Ga-12?<(*by");
		
		System.out.print("\nTesting when user has no password originally\n");
		user = userDao.findUserByName("Meridith Katongole");
		oldPass = user.getPassword();
		soft.assertEquals(oldPass,null);
		userDao.editUserPassword("Meridith Katongole", "*;?0&melK");
		newPass = user.getPassword();
		soft.assertEquals(newPass,"*;?0&melK");
	}
	
	@Test(groups = "delete-user")
	public void testDeleteUserById() {		
		userDao.deleteUserById(1);			
	}
	
	@Test(groups="delete-user")
	public void testDeleteUserByName() {		
		userDao.deleteUserByName("Harmony Other");	
		
	}
	
	
	
	
	
}
