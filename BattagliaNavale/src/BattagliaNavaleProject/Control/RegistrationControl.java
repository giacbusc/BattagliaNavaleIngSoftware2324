package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import BattagliaNavaleProject.Database.ConnectionDb;
import BattagliaNavaleProject.form.RegistrationModel;
import BattagliaNavaleProject.formGui.RegistrationView;

public class RegistrationControl implements ActionListener {
	
	RegistrationModel model;
    private RegistrationView view;
    
 
    public RegistrationControl(RegistrationView view ){
        this.view = view;
        //view.addRecListener(this);
    }
    
    public boolean verificaCampi(RegistrationModel user){
		if(user.getName().isEmpty() || user.getSurname().isEmpty() || user.getNickname().isEmpty() || user.getPassword().isEmpty()){
			return false;
		}
		
		return true;
	}
    
    public boolean verificaNameSurname(RegistrationModel user){
		if(!(( user.getName()).matches("[a-zA-Z]+") || !user.getSurname().matches("[a-zA-Z]+"))) {
			return false;
		}
		
		return true;
	}
    
    public boolean verificaNickname(RegistrationModel user) throws SQLException
	{
		ConnectionDb conn = new ConnectionDb();
		
		String nickname = user.getNickname();
		System.out.println(user.getNickname());
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
    public void actionPerformed(ActionEvent e) {
        
        try {
            
            if(e.getSource() instanceof JButton ) {
                JButton clickedButton= (JButton) e.getSource();
                
                if(clickedButton.getText().equals("back")) {
                	System.out.println("martin");
                    view.close();
                }
                
                
                if(clickedButton.getText().equals("Save")) 
                {   
                    model = view.getUser();
                    
                    if( verificaCampi(model) && verificaNameSurname(model) && verificaNickname(model)) 
                    {
                        if(checkUser(model)) 
                        {
                            view.showMessage("Registration complete!");
                           view.openLogin();
                            
                        } 
                        else 
                        {
                            view.showMessage("Incorrect data entered, please re-enter it!");
                        }     
                    } 
                    else 
                    {
                         view.showMessage("Incorrect data entered, please re-enter it");
                    }
                }
                
                
            }
        }
        
        catch (Exception ex) {
            view.showMessage(ex.getMessage()); // <-- Questa è la riga modificata
            ex.printStackTrace(); // <-- Aggiunta per visualizzare il messaggio di errore
            
        }
    }
        

    
    public boolean checkUser(RegistrationModel user) throws Exception {
 
    	String sql = "INSERT INTO UTENTE VALUES (?,?,?,?)";
    	
		ConnectionDb conn = new ConnectionDb();
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getSurname());
		pstmt.setString(3, user.getNickname());
		pstmt.setString(4, user.getPassword());
		boolean resultSet = pstmt.execute();
		return true;

      }
}
