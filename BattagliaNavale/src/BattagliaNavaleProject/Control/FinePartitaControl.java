
package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import org.h2.tools.Server;

import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.form.LoginModel;
import BattagliaNavaleProject.formGui.FinePartitaView;
import BattagliaNavaleProject.formGui.MenuPrincipaleView;
import BattagliaNavaleProject.formGui.SchermataAttesaView;
import BattagliaNavaleProject.multiplayer.ServerSocket;

public class FinePartitaControl  {
	private FinePartitaView fpv;
	private String user;
	public FinePartitaControl(String userName) {
		user=userName;
	}
		
		
		public void gestisciClick(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				if(e.getSource() instanceof JButton ) {
					JButton clickedButton= (JButton) e.getSource();
					
					if(clickedButton.getText().equals("MENU")) 
					{   
		                try {
							    openMenu();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
		            
					}
					if(clickedButton.getText().equals("EXIT")) {
						System.exit(0);
						fpv.dispose();
					}
				}
				
			}catch(Exception e3) {
				e3.printStackTrace();	
			}
		}


		


		public void openMenu() throws IOException, SQLException {
			 MenuPrincipaleView menu = new MenuPrincipaleView(user,menu.getObserver()); 
			MenuPrincipaleControl menuc = new MenuPrincipaleControl(user, menu.getObserver());
		   
			menu.setVisible(true);
			
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					// TODO Auto-generated method stub
					fpv.dispose(); 
					return null;
				}
				
			};
			worker.execute();
			
		}
	}     


