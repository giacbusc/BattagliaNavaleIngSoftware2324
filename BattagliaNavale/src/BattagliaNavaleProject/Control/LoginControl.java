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

public class LoginControl implements ActionListener  
{
	
	private LoginGUI gui;
	public LoginModel model;
	
	
	public LoginControl(LoginGUI gui)
	{	
		this.gui = gui;
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
			// TODO Auto-generated method stub
		
		try {
			
			model = gui.getUserModel();
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();
				
				if(clickedButton.getText().equals("Login")) 
				{   
	                try {
						if(checkUser(model)){
						    gui.showMessage("Login succesfully!");
						    gui.openMenu();
						    
							
						}else{
						    gui.showMessage("Invalid username and/or password!");
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
                
				}
				if(clickedButton.getText().equals("Back")) {
					gui.close();
				}
			}
			
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
			
		
        }

	public static boolean checkUser(LoginModel model) throws SQLException, IOException  
	{
		
		LoginGUI gui = new LoginGUI();
    	ConnectionDb conn1 = new ConnectionDb();
    	
    	String sql = "SELECT * FROM utente WHERE nickname =? AND password = ?";
        PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);
        pstmt.setString(1, model.getUserName());
        pstmt.setString(2, model.getPassword());

        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next() && verificaCampi(model)) 
        { 
			
			return true;
			
        } 
        else {
        	model.setUserName("");
			model.setPassword("");
			return false;
        }
        }
    
    private static boolean verificaCampi(LoginModel model)
    {
		if(model.getUserName()=="" || model.getPassword()==""){
			return false;
		}
		
		return true;
	}

	

	

	

}     


