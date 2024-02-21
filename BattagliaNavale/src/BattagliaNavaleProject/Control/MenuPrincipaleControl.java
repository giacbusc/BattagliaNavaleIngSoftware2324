package BattagliaNavaleProject.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import org.h2.tools.Server;

import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.client.SoundEffect;
import BattagliaNavaleProject.formGui.MenuPrincipaleView;
import BattagliaNavaleProject.multiplayer.ServerSocket;

public class MenuPrincipaleControl implements ActionListener{
	private MenuPrincipaleView menu;
	private SoundEffect s;
	public MenuPrincipaleControl(String username, Observer obs) throws IOException, SQLException {
		menu= new MenuPrincipaleView(username,obs);
		s = new SoundEffect();
		System.out.println("Sono entrato");
		menu.setVisible(true);
		menu.addActionMulti(this);
		menu.addActionSolo(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tcp= "tcp://172.16.128.203:5535";
		String local="tcp://localhost:5545";
		String[] parti = local.split(":");
		

		if(e.getSource() instanceof JButton ) {
			JButton clickedButton= (JButton) e.getSource();
			try {
				s.musica();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(clickedButton.getText().equals("  ")) {
				try {
					Server.createTcpServer().start();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ServerSocket.setIndirizzo(tcp);
				DoubleGameGridControl.setIndirizzo(tcp);
				//ConnectionControl.setIndirizzo(tcp);
				setConnectionIndirizzo(tcp);
				SchermataAttesaControl.setIndirizzo(tcp);
				System.out.println("tanti pc");
				try { 
					open();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(clickedButton.getText().equals("")) {
				ServerSocket.setIndirizzo("tcp://*:" + parti[2]);
				//ConnectionControl.setIndirizzo(local);
				setConnectionIndirizzo(local);
				DoubleGameGridControl.setIndirizzo(local);
				SchermataAttesaControl.setIndirizzo(local);
				System.out.println("un pc");
				try {
					open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
	}
	}

	public void open( ) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		final SchermataAttesaControl sac= new SchermataAttesaControl("ATTESA AVVERSARIO", menu.getUsername());
		menu.dispose();
		//ConnectionControl c = new ConnectionControl(sin, userName);
		
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            // Esegui le operazioni di connessione qui
	            ConnectionControl c = new ConnectionControl(sac, menu.getUsername(), menu.getObserver());
	            return null;
	        }
	    };

	    worker.execute();
	}
	 
	public void setConnectionIndirizzo(String indirizzo)
	{
		ConnectionControl.setIndirizzo(indirizzo);
	}
}
