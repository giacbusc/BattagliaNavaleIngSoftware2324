package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import BattagliaNavaleProject.control.LoginControl;
import BattagliaNavaleProject.formModel.LoginModel;

public class LoginTest {

	private LoginControl lc;
	@Test public void test1() throws IOException, InterruptedException, SQLException {
		 lc= new LoginControl();

		LoginModel lm=new LoginModel();
		lm.setPassword("");
		lm.setUserName("fedegerva");
		assertFalse(lc.verificaCampi(lm));// mi aspetto falso perchè non ho messo la password

	}

	@Test public void test2() throws IOException, InterruptedException, SQLException {
		lc= new LoginControl();

		LoginModel lm=new LoginModel();
		lm.setUserName("fedegerva");;

		lm.setPassword("1234");
		assertTrue(lc.checkUser(lm));// mi aspetto vero perchè ho inserito i campi corretti
	}
}