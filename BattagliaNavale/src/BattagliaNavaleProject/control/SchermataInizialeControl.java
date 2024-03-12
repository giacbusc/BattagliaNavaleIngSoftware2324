package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SchermataInizialeView;

public class SchermataInizialeControl implements ActionListener, Observer{

	private SchermataInizialeView schermata;

	public SchermataInizialeControl(){
		schermata = new SchermataInizialeView();
		schermata.setVisible(true);
		schermata.addActionLog(this);
		schermata.addActionReg(this);
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
					//Se viene premuto il pulsante Login, viene aperta la finestra di Login 
					//e viene chiusa questa schermata
					openLogin();

				}


				else if(clickedButton.getName().equals("Register")) 
				{   //Se viene premuto il pulsante Register, viene aperta la finestra di registrazione 
					//e viene chiusa questa schermata
					openRegistration();
				}
			}
		}

		catch (Exception ex) {

			ex.printStackTrace(); 

		}

	}
	//Metodo per aprire la finestra di login 
	public void openLogin() {

		LoginControl login = new LoginControl();
		login.getView().setObserver(this);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				//Qui viene chiusa la schermata
				schermata.dispose(); 
				return null;
			}
		};

		worker.execute(); 

	}
	//Metodo per aprire la finestra di registrazione
	public void openRegistration() {

		RegistrationControl reg = new RegistrationControl();
		reg.getView().setObserver(this);

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				//Qui viene chiusa la schermata
				schermata.dispose(); 
				return null;
			}
		};

		worker.execute(); 
	}

	//metodo che viene chiamato dalle classi LoginControl, RegistrationControl e ConnectionControl
	//per riaprire la schermata iniziale
	@Override
	public void update() {
		// TODO Auto-generated method stub
			schermata = new SchermataInizialeView();
			schermata.setVisible(true);
			schermata.addActionLog(this);
			schermata.addActionReg(this);
	}
}
