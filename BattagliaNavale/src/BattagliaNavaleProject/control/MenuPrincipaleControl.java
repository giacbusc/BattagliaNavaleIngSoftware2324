package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import org.h2.tools.Server;

import BattagliaNavaleProject.BattagliaNavaleServer.ServerSocket;
import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.MenuPrincipaleView;
import BattagliaNavaleProject.view.Observer;

public class MenuPrincipaleControl implements ActionListener, TornaMenuPrincipale{
	private MenuPrincipaleView menu;
	private SchermataAttesaControl sac;
	private String username;
	private Observer obs;
	public MenuPrincipaleControl(String username, Observer obs) throws IOException, SQLException {
		menu= new MenuPrincipaleView(username,obs);
		this.username = username;
		this.obs = obs;
		System.out.println("Sono entrato");
		menu.setVisible(true);
		menu.addActionMulti(this);
		menu.addActionSolo(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tcp= "tcp://172.16.128.203:5554";
		String local="tcp://localhost:5545";
		String[] parti = tcp.split(":");
		String filepath = "./music/sceltaMenu3.wav";
	    SoundEffect s = new SoundEffect();
	    s.playMusic(filepath,true);

		if(e.getSource() instanceof JButton ) {
			JButton clickedButton= (JButton) e.getSource();
			
			if(clickedButton.getText().equals("  ")) {
				/*try {
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
				}*/
				SelezioneIndirizzoControl sic = new SelezioneIndirizzoControl(username,this,obs);
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
		sac= new SchermataAttesaControl("ATTESA AVVERSARIO", menu.getUsername(),menu.getObserver(), this);
		menu.dispose();
		//ConnectionControl c = new ConnectionControl(sin, userName);
		
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            // Esegui le operazioni di connessione qui
	        	creaConnectionControl();
	            return null;
	        }
	    };

	    worker.execute();
	}
	
	public void creaConnectionControl() throws IOException, InterruptedException
	{
		 ConnectionControl c = new ConnectionControl(sac, menu.getUsername(), menu.getObserver(), this);
	}
	 
	public void setConnectionIndirizzo(String indirizzo)
	{
		ConnectionControl.setIndirizzo(indirizzo);
	}
	@Override
	public void torna(String username, Observer obs) throws IOException, SQLException {
		// TODO Auto-generated method stub
		MenuPrincipaleControl mp = new MenuPrincipaleControl(username, obs);
	}
	@Override
	public void chiudi() {
		// TODO Auto-generated method stub
		menu.dispose();
	}
}
