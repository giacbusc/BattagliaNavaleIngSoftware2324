package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;

import BattagliaNavaleProject.Database.ConnectionDb;
import BattagliaNavaleProject.form.LoginModel;
import BattagliaNavaleProject.formGui.LoginView;

public class LoginControl implements ActionListener  
{
	
	private LoginView gui;
	public LoginModel model;
	
	
	public LoginControl(LoginView gui)
	{	
		this.gui = gui;
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
			// TODO Auto-generated method stub
		System.out.println("Ciao");
		try {
			
			model = gui.getUserModel();
			System.out.println(model.getUserName());
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();
				System.out.println("Ciao2");
				if(clickedButton.getText().equals("Login")) 
				{   System.out.println("Ciao3");
	                try {
	                	System.out.println("Ciao4");
						if(checkUser(model)){
							System.out.println("Ciao5");
							gui.showMessage("Login succesfully!");
						    gui.openMenu();
						    
							
						}else{
							System.out.println("Ciao6");
						    gui.showMessage("Invalid username and/or password!");
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Ciao7");
						e1.printStackTrace();
					} 
                
				}
				if(clickedButton.getText().equals("Back")) {
					gui.close();
				}
			}
			
		}catch(Exception e3) {
			e3.printStackTrace();	
			System.out.println("Ciao8");
		}
			
		
        }

	public static boolean checkUser(LoginModel model) throws SQLException, IOException  
	{
		
		//LoginView gui = new LoginView();
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


