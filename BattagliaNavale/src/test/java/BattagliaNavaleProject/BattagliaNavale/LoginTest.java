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
		assertFalse(lc.verificaCampi(lm));

	}

	@Test public void test2() throws IOException, InterruptedException, SQLException {
		lc= new LoginControl();

		LoginModel lm=new LoginModel();
		lm.setUserName("fedegerva");;

		lm.setPassword("1234");
		assertTrue(lc.checkUser(lm));
	}
}