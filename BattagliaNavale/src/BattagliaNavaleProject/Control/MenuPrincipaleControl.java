package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;

import org.h2.tools.Server;

import BattagliaNavaleProject.formGui.MenuPrincipaleView;
import BattagliaNavaleProject.multiplayer.ServerSocket;

public class MenuPrincipaleControl implements ActionListener{
	private MenuPrincipaleView menu;
	
	public MenuPrincipaleControl(String username) throws IOException, SQLException {
		menu= new MenuPrincipaleView(username);
		System.out.println("Sono entrato");
		menu.setVisible(true);
		menu.addActionMulti(this);
		menu.addActionSolo(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tcp= "tcp://172.16.128.203:5530";
		String local="tcp://localhost:5545";
		String[] parti = local.split(":");
		
		
		
		
		if(e.getSource() instanceof JButton ) {
			JButton clickedButton= (JButton) e.getSource();
			if(clickedButton.getText().equals("  ")) {
				try {
					Server.createTcpServer().start();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ServerSocket.setIndirizzo(tcp);
				DoubleGameGridControl.setIndirizzo(tcp);
				//ConnectionControl.setIndirizzo(tcp);
				menu.setConnectionIndirizzo(tcp);
				SchermataAttesaControl.setIndirizzo(tcp);
				System.out.println("tanti pc");
				try {
					menu.open();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(clickedButton.getText().equals("")) {
				ServerSocket.setIndirizzo("tcp://*:" + parti[2]);
				//ConnectionControl.setIndirizzo(local);
				menu.setConnectionIndirizzo(local);
				DoubleGameGridControl.setIndirizzo(local);
				SchermataAttesaControl.setIndirizzo(local);
				System.out.println("un pc");
				try {
					menu.open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
	}
	}

}
