package BattagliaNavaleProject.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.database.ConnectionDb;
import BattagliaNavaleProject.formModel.RegistrationModel;
import BattagliaNavaleProject.view.RegistrationView;

public class RegistrationControl implements ActionListener {
	
	RegistrationModel model;
    private RegistrationView view;
    
 
    public RegistrationControl( ){
        view = new RegistrationView();
        view.setVisible(true);
        view.addRecListener(this);
        view.addActionBack(this);
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
                
                if(clickedButton.getText().equals("Back")) {
                	System.out.println("martin");
                    close();
                }
                
                
                if(clickedButton.getText().equals("Save")) 
                {   
                    model = view.getUser();
                    
                    if( verificaCampi(model) && verificaNameSurname(model) && verificaNickname(model)) 
                    {
                        if(checkUser(model)) 
                        {
                            view.showMessage("Registration complete!");
                            openLogin();
                            
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

	public RegistrationView getView() {
		// TODO Auto-generated method stub
		return view;
	}
	
	public void openLogin() {
		  
        LoginControl log = new LoginControl();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            // Esegui le operazioni di connessione qui
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
