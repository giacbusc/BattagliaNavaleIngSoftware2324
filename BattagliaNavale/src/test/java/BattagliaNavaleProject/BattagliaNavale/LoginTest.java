package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import BattagliaNavaleProject.control.LoginControl;
import BattagliaNavaleProject.formModel.LoginModel;

public class LoginTest {

@Test public void Test1() throws IOException, InterruptedException, SQLException {
BattagliaNavaleProject.control.LoginControl lc= new BattagliaNavaleProject.control.LoginControl();

BattagliaNavaleProject.formModel.LoginModel lm=new LoginModel();
lm.setPassword("");
lm.setUserName("fedegerva");
assertFalse(lc.verificaCampi(lm));

}

@Test public void Test2() throws IOException, InterruptedException, SQLException {
LoginControl lc= new LoginControl();

LoginModel lm=new LoginModel();
lm.setUserName("fedegerva");;

lm.setPassword("1234");
assertTrue(lc.checkUser(lm));
}
}