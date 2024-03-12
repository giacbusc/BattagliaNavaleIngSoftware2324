
package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.view.FinePartitaView;
import BattagliaNavaleProject.view.Observer;

public class FinePartitaControl  implements ActionListener{
	private FinePartitaView fpv;
	private String user;
	Observer obs;
	private TornaMenuPrincipale tmp;
	
	public FinePartitaControl(String userName, String messaggio, TornaMenuPrincipale tmp, Observer obs) throws IOException, SQLException {
		this.obs = obs;
		this.tmp = tmp;
		this.user=userName;
		fpv = new FinePartitaView(user, messaggio);
		fpv.setVisible(true);
		fpv.aggiungiListenerMenu(this);
		fpv.aggiungiListenerExit(this);
	}

	//Questo metodo actionPerfomerd gestisce la selezione dell'utente nel momento in cui finisce la partita
	//L'utente avrà due opzioni collegate a due diversi pulsanti
	public void actionPerformed (ActionEvent e ) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();

				//Se viene cliccato questo bottone l'utente tornerà al menu principale
				if(clickedButton.getName().equals("menu")) 
				{   
					tmp.torna(user, obs);
					
					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					
						@Override
						protected Void doInBackground() throws Exception {
							// TODO Auto-generated method stub
							fpv.dispose(); 
							//s.startServer(s.getIndirizzo());
							return null;
						}

					};
					worker.execute(); 

				}
				//Se l'utente clicca questo bottone il gioco verrà chiuso
				if(clickedButton.getName().equals("esci")) {
					//System.exit(0);
					fpv.dispose();
					throw new RuntimeException();
				}
			}

		}catch(Exception e3) {
			System.err.println("Fine Partita");	
		}
	}

}     


