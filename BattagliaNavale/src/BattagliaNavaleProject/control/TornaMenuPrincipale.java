package BattagliaNavaleProject.control;

import java.io.IOException;
import java.sql.SQLException;

import BattagliaNavaleProject.view.Observer;

public interface TornaMenuPrincipale {

	void torna(String username, Observer obs) throws IOException, SQLException;
}
