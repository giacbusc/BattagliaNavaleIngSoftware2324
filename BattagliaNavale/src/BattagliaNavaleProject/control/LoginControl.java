package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.database.ConnectionDb;
import BattagliaNavaleProject.formModel.LoginModel;
import BattagliaNavaleProject.view.LoginView;

public class LoginControl implements ActionListener
{
	
	private LoginView gui;
	public LoginModel model;
	
	
	public LoginControl() 
	{	
		gui = new LoginView();
		gui.setVisible(true);
		gui.addActionBack(this);
		gui.addActionLogin(this);
		
	}
	
	
	public static boolean checkUser(LoginModel model) throws SQLException, IOException  
	{
		
		//LoginView gui = new LoginView();
    	ConnectionDb conn1 = new ConnectionDb();
    	
    	String sql = "SELECT * FROM utente WHERE nickname =? AND password = ?";
        PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);
        pstmt.setString(1, model.getUserName().trim());
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
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			model = gui.getUserModel();
			System.out.println(model.getUserName());
			if(e.getSource() instanceof JButton ) {
				JButton clickedButton= (JButton) e.getSource();
				
				if(clickedButton.getText().equals("Login")) 
				{   
	                try {
						if(checkUser(model)){
							gui.showMessage("Login succesfully!");
							ConnectionDb conn = new ConnectionDb();
							conn.closeConnection();
						    openMenu();
						    
							
						}else{
						    gui.showMessage("Invalid username and/or password!");
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	            
				}
				if(clickedButton.getText().equals("Back")) {
					close();
				}
			}
			
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
	}


	public LoginView getView() {
		// TODO Auto-generated method stub
		return gui;
	}

	public void openMenu() throws IOException, SQLException {
		
		model = gui.getUserModel();
		MenuPrincipaleControl menu = new MenuPrincipaleControl(model.getUserName(), gui.getObserver());
	    //MenuPrincipaleView menu = new MenuPrincipaleView(model.getUserName()); 
		//menu.setVisible(true);
		
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
	
	public void close() {
		gui.dispose();
		gui.getObserver().update();
	}

}     


