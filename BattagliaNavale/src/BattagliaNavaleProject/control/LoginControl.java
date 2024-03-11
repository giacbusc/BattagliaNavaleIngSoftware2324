package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;
import BattagliaNavaleProject.formModel.LoginModel;
import BattagliaNavaleProject.view.LoginView;

public class LoginControl implements ActionListener {

	private LoginView gui;
	private LoginModel model;
	private MenuPrincipaleControl menu;

	public LoginControl() {
		gui = new LoginView();
		gui.setVisible(true);
		gui.addActionBack(this);
		gui.addActionLogin(this);

	}

	public static boolean checkUser(LoginModel model) throws SQLException, IOException {

		// LoginView gui = new LoginView();
		ConnectionDb conn1 = new ConnectionDb();
		System.out.println(conn1.getConnection());
		String sql = "SELECT * FROM utente WHERE nickname =? AND password = ?";
		//istruzioni per il database
		PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);
		pstmt.setString(1, model.getUserName().trim());
		pstmt.setString(2, model.getPassword());

		ResultSet rs = pstmt.executeQuery();
		//faccio una verifica nel database
		if (rs.next() && verificaCampi(model)) {
			
			pstmt.close();
			return true;

		} else {
			model.setUserName("");
			model.setPassword("");
			pstmt.close();
			return false;
		}
		
	}

	public static boolean verificaCampi(LoginModel model) {
		if (model.getUserName().equals("") || model.getPassword().equals("")) {
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {

			model = gui.getUserModel();
			System.out.println(model.getUserName());
			/*
			 * verifico quale click su quale tasto abbia chiamato la funzione 
			 */
			if (e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();

				if (clickedButton.getText().equals("Login")) {
					try {
						if (checkUser(model)) {//controllo credenziali nel db
							gui.showMessage("Login succesfully!");
							ConnectionDb conn = new ConnectionDb();
							conn.closeConnection();
							openMenu();// se i controlli vado bene apro ilmenu

						} else {//trovo errori negli inserimenti
							gui.showMessage("Invalid username and/or password!");
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}//se voglio tornare indietro devo chiudere la schermata
				if (clickedButton.getText().equals("Back")) {
					close();
				}
			}

		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

	public LoginView getView() {
		// TODO Auto-generated method stub
		return gui;
	}

	public void openMenu() throws IOException, SQLException {
		//se vanno bene i dati apro il menu
		model = gui.getUserModel();
		menu = new MenuPrincipaleControl(model.getUserName(), gui.getObserver());
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				gui.dispose();
				return null;
			}

		};
		worker.execute();

	}
	//chiudo il login e torno al menu principale
	public void close() {
		gui.dispose();
		gui.getObserver().update();
	}

}
