package BattagliaNavaleProject.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;

import org.h2.tools.Server;

import BattagliaNavaleProject.formGui.SceltaGiocoView;
import BattagliaNavaleProject.multiplayer.ServerSocket;

public class SceltaGiocoControl implements ActionListener{
	private String user;
	SceltaGiocoView s;
public SceltaGiocoControl( SceltaGiocoView s) {
		//this.user=userName;
		this.s=s;
	}

 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tcp= "tcp://172.16.128.120:5530";
		String local="tcp://localhost:5547";
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
				ConnectionControl.setIndirizzo(tcp);
				SchermataAttesaControl.setIndirizzo(tcp);
				System.out.println("tanti pc");
				try {
					s.open();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(clickedButton.getText().equals("")) {
				ServerSocket.setIndirizzo("tcp://*:" + parti[2]);
				ConnectionControl.setIndirizzo(local);
				SchermataAttesaControl.setIndirizzo(local);
				System.out.println("un pc");
				try {
					s.open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
	}
		

}
}
