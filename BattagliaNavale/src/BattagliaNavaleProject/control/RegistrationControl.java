package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;
import BattagliaNavaleProject.formModel.RegistrationModel;
import BattagliaNavaleProject.view.RegistrationView;

public class RegistrationControl implements ActionListener {

	private RegistrationModel model;
	private RegistrationView view;
	private LoginControl log;

	public RegistrationControl() {
		view = new RegistrationView();
		view.setVisible(true);
		view.addRecListener(this);
		view.addActionBack(this);
	}

	// metodo che verifica se tutti i campi hanno al loro interno qualcosa
	public boolean verificaCampi(RegistrationModel user) {
		if (user.getName().isEmpty() || user.getSurname().isEmpty() || user.getNickname().isEmpty()
				|| user.getPassword().isEmpty()) {
			return false;
		}

		return true;
	}

	// metodo che verifica sei il nome e il cognome è composto da sole lettere
	public boolean verificaNameSurname(RegistrationModel user) {
		if (!((user.getName()).matches("[a-zA-Z]+") || !user.getSurname().matches("[a-zA-Z]+"))) {
			return false;
		}

		return true;
	}

	// metodo che verifica se il nickname è già presente all'interno del database
	public boolean verificaNickname(RegistrationModel user) throws SQLException {
		ConnectionDb conn = new ConnectionDb();

		String nickname = user.getNickname();
		System.out.println(user.getNickname());
		String sql = "SELECT nickname FROM UTENTE WHERE nickname = ?";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
		pstmt.setString(1, nickname);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			pstmt.close();
			return false;
		} else {
			pstmt.close();
			return true;
		}

	}

	public void actionPerformed(ActionEvent e) {

		try {

			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();

				// Se l'utente preme il pulsante Back viene chiamato il metodo close presente in
				// questa classe
				if (clickedButton.getText().equals("Back")) {
					close();
				}

				// Se l'utente preme il pulsante Save viene verificata la correttezza dei dati
				if (clickedButton.getText().equals("Save")) {
					model = view.getUser();

					if (verificaCampi(model) && verificaNameSurname(model) && verificaNickname(model)) {
						if (checkUser(model)) {
							// Se i dati sono corretti allora viene aperta la finestra di login
							view.showMessage("Registration complete!");
							openLogin();

						} else {
							// Se i dati non sono corretti l'utente deve re-inserirli
							view.showMessage("Incorrect data entered, please re-enter it!");
						}
					} else {
						view.showMessage("Incorrect data entered, please re-enter it");
					}
				}

			}
		}

		catch (Exception ex) {
			view.showMessage(ex.getMessage());
			ex.printStackTrace();

		}
	}

	// Metodo che inserisce all'interno del database i dati dell'utente che ha
	// appena effettuato la registrazione
	public boolean checkUser(RegistrationModel user) throws Exception {

		String sql = "INSERT INTO UTENTE VALUES (?,?,?,?)";

		ConnectionDb conn = new ConnectionDb();
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getSurname());
		pstmt.setString(3, user.getNickname());
		pstmt.setString(4, user.getPassword());
		pstmt.execute();
		pstmt.close();
		return true;

	}

	public RegistrationView getView() {
		// TODO Auto-generated method stub
		return view;
	}

	// Metodo utilizzato per aprire la finestra di Login
	public void openLogin() {

		log = new LoginControl();
		log.getView().setObserver(this.getView().getObserver());
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				// Chiudiamo qui la finestra di registrazione
				view.dispose();
				return null;
			}
		};

		worker.execute();
	}

	public void close() {
		view.dispose();
		view.getObserver().update();
	}
}
