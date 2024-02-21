package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.client.SoundEffect;
import BattagliaNavaleProject.formGui.SchermataInizialeView;

public class SchermataInizialeControl implements ActionListener, Observer{

	private SchermataInizialeView schermata;

	public SchermataInizialeControl(){
		schermata = new SchermataInizialeView();
		schermata.setVisible(true);
		schermata.addActionLog(this);
		schermata.addActionReg(this);
		//view.addRecListener(this);
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		String filepath = "./music/sceltaMenu3.wav";
	    SoundEffect s = new SoundEffect();
	    s.playMusic(filepath);
		try {

			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();
				
				if(clickedButton.getName().equals("Login")) {

					openLogin();

				}


				else if(clickedButton.getName().equals("Register")) 
				{   

					openRegistration();
				}
			}
		}

		catch (Exception ex) {

			ex.printStackTrace(); // <-- Aggiunta per visualizzare il messaggio di errore

		}

	}
	public void openLogin() {

		LoginControl login = new LoginControl();
		login.getView().setObserver(this);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				// Esegui le operazioni di connessione qui
				schermata.dispose(); 
				return null;
			}
		};

		worker.execute(); 

	}

	public void openRegistration() {

		RegistrationControl reg = new RegistrationControl();
		reg.getView().setObserver(this);

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				// Esegui le operazioni di connessione qui
				schermata.dispose(); 
				return null;
			}
		};

		worker.execute(); 
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
			schermata = new SchermataInizialeView();
			schermata.setVisible(true);
			schermata.addActionLog(this);
			schermata.addActionReg(this);
	}
}
