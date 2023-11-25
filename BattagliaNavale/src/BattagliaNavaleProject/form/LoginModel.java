package BattagliaNavaleProject.form;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import BattagliaNavaleProject.client.MenuPrincipale;
import BattagliaNavaleProject.formGui.LoginGUI;
import BattagliaNavaleProject.server.ConnectionDb;

public class LoginModel {
	
	private static String userName;
    private static String password;
 
  
    public LoginModel() {
    }
    
    public LoginModel(String username, String password){
        this.userName = username;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
	
	   /*public void VerificaUtente(String nickname,String password) throws IOException {
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
	                	logGUI.setUsername("");
						logGUI.setPassword("");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	    }
	    private boolean verificaCampi(){
		if(logGUI.getUsername()==""|| logGUI.getPassword()==""){
			return false;
		}
		
		return true;
	}
	;
	*/
	
}
