package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import BattagliaNavaleProject.client.MenuPrincipale;
import BattagliaNavaleProject.form.LoginModel;
import BattagliaNavaleProject.form.SchermataIniziale;
import BattagliaNavaleProject.formGui.LoginGUI;
import BattagliaNavaleProject.server.ConnectionDb;

public class LoginControl implements ActionListener  {
	private static LoginModel model;
    private static LoginGUI gui;
/*    
 public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		LoginGUI gui;
		try {
			gui = new LoginGUI ();
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= ( JButton) e.getSource();
				if(clickedButton.getText().equals("Login")) {
					String user = gui.getUser();
					System.out.println(user);
			    	 String pw = gui.getPassword();
		              LoginModel model=new LoginModel(user,pw);
                try {
					if(checkUser(model)){
					    gui.showMessage("Login succesfully!");
					}else{
					    gui.showMessage("Invalid username and/or password!");
					}
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                
				}
				if(clickedButton.getText().equals("Back")) {
					
					SchermataIniziale inizio;
					inizio = new SchermataIniziale(); 
					
					
					inizio.setVisible(true);
					gui.setVisible(false);
			
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
        }


*/



	public static boolean checkUser(LoginModel user) throws SQLException, IOException  {
	
    	ConnectionDb conn1 = new ConnectionDb();
    	String sql = "SELECT * FROM utente WHERE nickname =? AND password = ?";
        PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);
        pstmt.setString(1, model.getUserName());
        pstmt.setString(2, model.getPassword());

        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next() && verificaCampi()) 
        { 
        	try {
				gui.showMessage("Login complete!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuPrincipale menu;
			menu = new MenuPrincipale(); 
			menu.setVisible(true);
			gui.dispose(); //gui o menu
			return true;
			
        } 
        else {
        	gui.showMessage( "Incorrect data entered, please re-enter it");
        	model.setUserName("");
			model.setPassword("");
			return false;
        }
        }
    
    private static boolean verificaCampi(){
		if(model.getUserName()==""|| model.getPassword()==""){
			return false;
		}
		
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	

}     


