package com.arola.notaker.DaoTests;

import static org.testng.Assert.assertNotNull;

import org.hibernate.Session;
import org.testng.annotations.Test;

import com.arola.util.SessionUtil;

public class SessionBuilderTest {
	
	@Test
	public void testSessionFactory() {
		try(Session session = SessionUtil.getSession()){
			assertNotNull(session);
		}
	}

}
