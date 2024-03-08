
package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.BattagliaNavaleServer.ServerSocket;
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

	public void actionPerformed (ActionEvent e ) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();

				if(clickedButton.getText().equals("")) 
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
				if(clickedButton.getText().equals(".")) {
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


