package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import BattagliaNavaleProject.formGui.SchermataInizialeView;

public class SchermataInizialeControl implements ActionListener{
	
    private SchermataInizialeView schermata;
    
    public SchermataInizialeControl(){
    	schermata = new SchermataInizialeView();
    	schermata.setVisible(true);
    	schermata.addActionLog(this);
    	schermata.addActionReg(this);
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
	                
	                
	                else if(clickedButton.getName().equals("Register")) 
	                {   
	                   
	                    schermata.openRegistration();
	                }
	        }
		 }
	        
	        catch (Exception ex) {
	           
	            ex.printStackTrace(); // <-- Aggiunta per visualizzare il messaggio di errore
	            
	        }
		
	}

}
