package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SelezioneIndirizzoView;

public class SelezioneIndirizzoControl implements ActionListener {

	private String username;
	private SelezioneIndirizzoView siv;
	private SchermataAttesaControl sac;
	private String ind;
	private TornaMenuPrincipale tmp;
	private Observer obs;
	private ConnectionControl c;
	//Questa classe viene creata nel momento in cui l'utente vuole giocare su più pc differenti.
	//In questo caso sarà necessario selezionare l'indirizzo a cui collegarsi
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
			//Se l'utente clicca il pulsante Gioca viene impostato l'indirizzo nella classe DoubleGameGridControl
			//e nella SchermataAttesaControl
			if(clickedButton.getText().equals("Gioca"))
			{	
				DoubleGameGridControl.setIndirizzo(ind+siv.indirizzoField.getText());
				setConnectionIndirizzo(ind+siv.indirizzoField.getText());
				SchermataAttesaControl.setIndirizzo(ind+siv.indirizzoField.getText());

				try {
					//metodo per iniziare la connessione e chiudere questa schermata
					open();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//Se l'utente clicca il pulsante Back viene chiamato il metodo close
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

	//Metodo che chiude questa schermata, crea una nuova instanza della classe SchermataAttesaControl
	//e una nuova istanza della classe ConnectionControl
	public void open() throws IOException, InterruptedException
	{
		sac= new SchermataAttesaControl("ATTESA AVVERSARIO", username ,obs, tmp);
		siv.dispose();
		tmp.chiudi();

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				//Qui viene chiamato il metodo che crea la classe ConnectionControl
				creaConnectionControl();
				return null;
			}
		};

		worker.execute();
	}

	public void creaConnectionControl() throws IOException, InterruptedException
	{
		c = new ConnectionControl(sac, username, obs, tmp);
	}

	public void setConnectionIndirizzo(String indirizzo)
	{
		ConnectionControl.setIndirizzo(indirizzo);
	}

	//Metodo che chiude la schermata attuale e riapre la schermata del menu principale
	public void close() throws IOException, SQLException
	{
		tmp.torna(username, obs);
		siv.dispose();
	}
}
