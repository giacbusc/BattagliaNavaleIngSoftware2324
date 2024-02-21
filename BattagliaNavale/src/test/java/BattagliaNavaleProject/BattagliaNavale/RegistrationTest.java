package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import BattagliaNavaleProject.Control.RegistrationControl;
import BattagliaNavaleProject.form.RegistrationModel;

public class RegistrationTest {
	RegistrationControl rc= new RegistrationControl();
	
	RegistrationModel rm= new RegistrationModel();
	@Test public void Test1() {
		
		rm.setNickname("");
		rm.setName("Federica");
		rm.setSurname("Gervasoni");
		rm.setPassword("1234");
		assertFalse(rc.verificaCampi(rm));
	}
	
	@Test public void Test2() {
		
		rm.setNickname("fedegerva");
		rm.setName("Federica");
		rm.setSurname("Gervasoni");
		rm.setPassword("1234");
		assertTrue(rc.verificaCampi(rm));
	}
	
	@Test public void Test3() throws SQLException {

		rm.setNickname("fede");
		rm.setName("Federica");
		rm.setSurname("Gervasoni");
		rm.setPassword("1234");
		assertTrue(rc.verificaNameSurname(rm));
	}
	
	@Test public void Test4() throws SQLException {

		rm.setNickname("fede");
		rm.setName("!!");
		rm.setSurname("Zenni");
		rm.setPassword("1234");
		assertFalse(rc.verificaNameSurname(rm));
	}
	
}
