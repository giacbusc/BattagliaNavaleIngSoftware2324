package BattagliaNavaleProject.control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.SwingWorker;
import org.h2.tools.Server;
import BattagliaNavaleProject.BattagliaNavaleServer.ServerSocket;
import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;
import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.MenuPrincipaleView;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SelezioneIndirizzoView;

public class MenuPrincipaleControl implements ActionListener, TornaMenuPrincipale{
	private MenuPrincipaleView menu;
	private SchermataAttesaControl sac;
	private String username;
	private Observer obs;
	private ConnectionControl c;
	private MenuPrincipaleControl mp;
	private SelezioneIndirizzoControl sic;

	public MenuPrincipaleControl(String username, Observer obs) throws IOException, SQLException {
		menu= new MenuPrincipaleView(username,obs);
		this.username = username;
		this.obs = obs;
		System.out.println("Sono entrato");
		menu.setVisible(true);
		menu.addActionMulti(this);
		menu.addActionSolo(this);
		menu.addActionTutorial(this);
		aggiungiClassifica();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String local="tcp://localhost:5545";
		String filepath = "./music/sceltaMenu3.wav";
	    SoundEffect s = new SoundEffect();
	    s.playMusic(filepath);

		if(e.getSource() instanceof JButton ) 
		{
			JButton clickedButton= (JButton) e.getSource();
			System.out.println("SONO IL GETTEXT: " + clickedButton.getText());
			/*
			 * //se clicco su questo tasto allora voglio giocare su più pc e quindi non gioco in locale
			 *  ma aspetto che un altro client si connetta per giocare
			
			 */
			if(clickedButton.getText().equals("  ")) {
			
				sic = new SelezioneIndirizzoControl(username,this,obs);
			}
			else if(clickedButton.getText().equals("")) {
				/*
				 * gioco in locale su un unico pc
				
				 */
				setConnectionIndirizzo(local);
				DoubleGameGridControl.setIndirizzo(local);
				SchermataAttesaControl.setIndirizzo(local);
				
				try {
					open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(clickedButton.getText().equals("   "))
		{
				/*
				 * ho cliccato su tutorial, mi si apre il link
				 */
				System.out.println("tutorial");
				String path = "../tutorial/tutorial.html";
				File htmlFile = new File(path);
				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
	}
	}

	public void open( ) throws IOException, InterruptedException {
		//apertura della schermata di attesa del secondo client 
		sac= new SchermataAttesaControl("ATTESA AVVERSARIO", menu.getUsername(),menu.getObserver(), this);
		menu.dispose();
		//ConnectionControl c = new ConnectionControl(sin, userName);
		
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            // Esegui le operazioni di connessione qui
	        	creaConnectionControl();
	            return null;
	        }
	    };

	    worker.execute();
	}
	
	public void creaConnectionControl() throws IOException, InterruptedException
	{
		 c = new ConnectionControl(sac, menu.getUsername(), menu.getObserver(), this);
	}
	 
	public void setConnectionIndirizzo(String indirizzo)
	{
		ConnectionControl.setIndirizzo(indirizzo);
	}
	@Override
	public void torna(String username, Observer obs) throws IOException, SQLException {
		// TODO Auto-generated method stub
		mp = new MenuPrincipaleControl(username, obs);
	}
	@Override
	public void chiudi() {
		// TODO Auto-generated method stub
		menu.dispose();
	}
	
	private void aggiungiClassifica()
	{
		try {
            ConnectionDb conn = new ConnectionDb();
            Statement stmt = conn.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nickname, COUNT(*) AS NumeroVittorie FROM UTENTE JOIN PARTITA ON nickname = Vincitore GROUP BY nickname ORDER BY COUNT(*) DESC");
            
            int posizione = 1;

            while (rs.next()) {
                String giocatore = rs.getString("nickname");
                int numeroVittorie = rs.getInt("NumeroVittorie");

                menu.mostraClassifica(giocatore, numeroVittorie, posizione);
                posizione++;
            }
            
            // Chiudi le risorse
            stmt.close();
            rs.close();
            conn.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
