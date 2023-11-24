package BattagliaNavaleProject.form;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import BattagliaNavaleProject.client.MenuPrincipale;
import BattagliaNavaleProject.formGui.LoginGUI;
import BattagliaNavaleProject.server.ConnectionDb;

public class Login {
	private LoginGUI log;
	
	public Login() {
	}
	   public void VerificaUtente(String nickname,String password) throws IOException {
	         // Prepara la query SQL
	        
	            try {
	            	ConnectionDb conn1 = new ConnectionDb();
	            	String sql = "SELECT * FROM utente WHERE nickname =? AND password = ?";
	                PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);
	                pstmt.setString(1, nickname);
	                pstmt.setString(2, password);

	                ResultSet rs = pstmt.executeQuery();
	                if (rs.next() && verificaCampi()) 
	                { 
	                	JOptionPane.showMessageDialog(null, "Login complete!");
						MenuPrincipale menu;
						menu = new MenuPrincipale(); 
						menu.setVisible(true);
						menu.dispose(); 
	                } 
	                else {
	                	JOptionPane.showMessageDialog(null, "Incorrect data entered, please re-enter it");
	                	log.setUsername("");
						log.setPassword("");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	    }
	    private boolean verificaCampi(){
		if(log.getUsername()==""|| log.getPassword()==""){
			return false;
		}
		
		return true;
	}
	;
	
	
}
