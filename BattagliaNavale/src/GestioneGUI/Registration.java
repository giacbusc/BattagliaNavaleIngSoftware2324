package GestioneGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import BattagliaNavaleProject.client.LoginGUI;
import BattagliaNavaleProject.client.RegistrationGUI;
import BattagliaNavaleProject.server.ConnectionDb;

public class Registration {
	private RegistrationGUI regGUI;
	public ActionListener actionlistener;
	
	public Registration() {
		
	}
	
	public boolean verificaNomeCognome(){
		if(!(( regGUI.getNameField()).matches("[a-zA-Z]+") || !regGUI.getSurnameField().matches("[a-zA-Z]+"))) {
			return false;
		}
		
		return true;
	}
	

	public boolean verificaNickname() throws SQLException
	{
		ConnectionDb conn = new ConnectionDb();
		
		String nickname = regGUI.getNicknameField();
		String sql = "SELECT nickname FROM UTENTE WHERE nickname = ?";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
		pstmt.setString(1, nickname);

		ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
       	 return false;
         }
         else 
 		{
 			return true;
 		}
		
	}
        public boolean salvaDati() throws SQLException
    	{
    		String name = regGUI.getNameField();
    		String surname = regGUI.getSurnameField();
    		String nickname = regGUI.getNicknameField();
    		String password = regGUI.getPasswordField();
    		String sql = "INSERT INTO UTENTE VALUES ('"+name+"','"+surname+"','"+nickname+"','"+password+"')";
    	
    		ConnectionDb conn = new ConnectionDb();
    		boolean resultSet = conn.insertQuery(sql);
    		return true;
    		
    	}
        
    	public boolean verificaCampi(){
    		if(regGUI.getNameField().isEmpty() || regGUI.getSurnameField().isEmpty() || regGUI.getNicknameField().isEmpty() || regGUI.getPasswordField().isEmpty()){
    			return false;
    		}
    		
    		return true;
    	}
    	 public void actionPerformed(ActionEvent e) {
		        // action to perform when the button is used
		    	
		    	try {
					if(verificaCampi() && verificaNomeCognome() && verificaNickname()){
						if(salvaDati()) {
							JOptionPane.showMessageDialog(null, "Registration complete!");
							LoginGUI login;
							login = new LoginGUI(); 
							login.setVisible(true);
							login.dispose(); 
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Incorrect data entered, please re-enter it!!!");
							regGUI.nameField.setText("");
							regGUI.surnameField.setText("");
							regGUI.nicknameField.setText("");
							regGUI.passwordField.setText("");
						}
					}
					else {
							JOptionPane.showMessageDialog(null, "Incorrect data entered, please re-enter it");
					}
						

					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	             
	             
		    }


		public ActionListener getActionListener() {
			return actionlistener;
		}
    	
	}

