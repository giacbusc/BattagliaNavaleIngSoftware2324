package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import BattagliaNavaleProject.form.RegistrationModel;
import BattagliaNavaleProject.formGui.LoginView;
import BattagliaNavaleProject.formGui.RegistrationView;
import BattagliaNavaleProject.formGui.SchermataInizialeView;

public class SchermataInizialeControl implements ActionListener{
	
    private SchermataInizialeView schermata;
    
    public SchermataInizialeControl(SchermataInizialeView schermata ){
        this.schermata = schermata;
        //view.addRecListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		 try {
	            
	            if(e.getSource() instanceof JButton ) {
	                JButton clickedButton= (JButton) e.getSource();
	                
	                if(clickedButton.getName().equals("Login")) {
	              
	                	schermata.openLogin();
	                    
	                }
	                
	                
	                if(clickedButton.getName().equals("Register")) 
	                {   
	                   
	                    schermata.openRegistration();
	                }
	        }
		 }
	        
	        catch (Exception ex) {
	           
	            ex.printStackTrace(); // <-- Aggiunta per visualizzare il messaggio di errore
	            
	        }
		// TODO Auto-generated method stub
		
	}

}
