package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import org.h2.tools.Server;

import BattagliaNavaleProject.BattagliaNavaleServer.ServerSocket;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SelezioneIndirizzoView;

public class SelezioneIndirizzoControl implements ActionListener {

	private String username;
	private SelezioneIndirizzoView siv;
	private SchermataAttesaControl sac;
	private String ind;
	private TornaMenuPrincipale tmp;
	private Observer obs;
	public SelezioneIndirizzoControl(String username, TornaMenuPrincipale tmp, Observer obs)
	{	
		this.obs = obs;
		this.tmp = tmp;
		ind="tcp://";
		this.username = username;
		siv = new SelezioneIndirizzoView(username);
		siv.aggiungiListenerNext(this);
		siv.aggiungiListenerBack(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton)
		{
			JButton clickedButton= (JButton) e.getSource();
			if(clickedButton.getText().equals("Gioca"))
			{
				try {
					Server.createTcpServer().start();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ServerSocket.setIndirizzo(ind+siv.indirizzoField.getText());
				DoubleGameGridControl.setIndirizzo(ind+siv.indirizzoField.getText());
				//ConnectionControl.setIndirizzo(tcp);
				setConnectionIndirizzo(ind+siv.indirizzoField.getText());
				SchermataAttesaControl.setIndirizzo(ind+siv.indirizzoField.getText());
				System.out.println("tanti pc");
				try {
					open();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(clickedButton.getText().equals("Back"))
			{
				try {
					close();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void open() throws IOException, InterruptedException
	{
		sac= new SchermataAttesaControl("ATTESA AVVERSARIO", username ,obs, tmp);
		siv.dispose();
		tmp.chiudi();
		
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
		 ConnectionControl c = new ConnectionControl(sac, username, obs, tmp);
	}
	
	public void setConnectionIndirizzo(String indirizzo)
	{
		ConnectionControl.setIndirizzo(indirizzo);
	}
	
	public void close() throws IOException, SQLException
	{
		tmp.torna(username, obs);
		siv.dispose();
	}
}
