package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

public class RegistrationTest {
	BattagliaNavaleProject.control.RegistrationControl rc= new BattagliaNavaleProject.control.RegistrationControl();
	
	BattagliaNavaleProject.formModel.RegistrationModel rm= new BattagliaNavaleProject.formModel.RegistrationModel();
	@Test public void Test1() {
		
		rm.setNickname("");
		rm.setName("Federica");
		rm.setSurname("Gervasoni");
		rm.setPassword("1234");
		assertFalse(rc.verificaCampi(rm));// mi aspetto falso perchè il nickname è vuoto
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
		assertTrue(rc.verificaNameSurname(rm));// ho inserito nel modo corretto i dati mi aspetto vero
	}
	
	@Test public void Test4() throws SQLException {

		rm.setNickname("fede");
		rm.setName("!!");
		rm.setSurname("Zenni");
		rm.setPassword("1234");
		assertFalse(rc.verificaNameSurname(rm));// il nome non può contenere caratteri speciali quindi mi aspetto false
	}
	
}