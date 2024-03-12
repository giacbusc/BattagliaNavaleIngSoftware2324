package BattagliaNavaleProject.control;

import java.io.IOException;
import java.sql.SQLException;

import BattagliaNavaleProject.view.Observer;

//Interfaccia che è stata creata per ridurre l'interdipendenza tra classi e 
//definisce i metodi implementati nella classe MenuPrincipaleControl,
//il quale servono per:
//torna = viene utilizzato per tornare alla schermata del menu principale, creandone una nuova istanza
//chiudi = viene utilizzato per chiudere la schermata del menu principale
public interface TornaMenuPrincipale {

	void torna(String username, Observer obs) throws IOException, SQLException;
	void chiudi();
}
