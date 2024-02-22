
package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.view.FinePartitaView;
import BattagliaNavaleProject.view.MenuPrincipaleView;
import BattagliaNavaleProject.view.Observer;
public class FinePartitaControl  implements ActionListener{
	private FinePartitaView fpv;
	private String user;
	private String messaggio;
	Observer obs;
	public FinePartitaControl(String userName, String messaggio) throws IOException, SQLException {
		this.user=userName;
		this.messaggio = messaggio;
		fpv = new FinePartitaView(user, messaggio);
		fpv.setVisible(true);
		fpv.aggiungiListenerMenu(this);
		fpv.aggiungiListenerExit(this);
	}
	public static void main(String[] args) throws IOException, SQLException
	{
		String msg = "lucaCiancio";
		String msg1= "Hai vinto";
		FinePartitaControl fp = new FinePartitaControl(msg, msg1);
	}

	public void actionPerformed (ActionEvent e ) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();

				if(clickedButton.getText().equals("")) 
				{   
					try {
						MenuPrincipaleControl menuc = new MenuPrincipaleControl(user, obs);

						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

							@Override
							protected Void doInBackground() throws Exception {
								// TODO Auto-generated method stub
								fpv.dispose(); 
								return null;
							}

						};
						worker.execute();

					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

				}
				if(clickedButton.getText().equals(".")) {
					System.exit(0);
					fpv.dispose();
				}
			}

		}catch(Exception e3) {
			e3.printStackTrace();	
		}
	}

}     


