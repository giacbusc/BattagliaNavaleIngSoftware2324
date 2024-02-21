
package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.formGui.FinePartitaView;
import BattagliaNavaleProject.formGui.MenuPrincipaleView;
public class FinePartitaControl  {
	private FinePartitaView fpv;
	private String user;
	Observer obs;
	 private MenuPrincipaleView menu;
	public FinePartitaControl(String userName,Observer observer) {
		user=userName;
		this.obs=observer;
	}
		
		
		public void gestisciClick(ActionEvent e ) {
			// TODO Auto-generated method stub
			try {
				if(e.getSource() instanceof JButton ) {
					JButton clickedButton= (JButton) e.getSource();
					
					if(clickedButton.getText().equals("")) 
					{   
		                try {

		           		 MenuPrincipaleView menu =new MenuPrincipaleView(user,obs); 
		           		MenuPrincipaleControl menuc = new MenuPrincipaleControl(user, obs);
		           	   
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


