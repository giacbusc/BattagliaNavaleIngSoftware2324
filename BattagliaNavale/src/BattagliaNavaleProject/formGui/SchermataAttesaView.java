package BattagliaNavaleProject.formGui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.zeromq.ZMQ.Socket;

import BattagliaNavaleProject.Control.SchermataAttesaControl;
import BattagliaNavaleProject.form.SchermataAttesaModel;

import java.awt.BorderLayout;

public class SchermataAttesaView extends JFrame implements ActionListener{
String msg;
String username;
	 public SchermataAttesaView(String msg,String userName) {
		 this.msg=msg;
		 this.username=userName;
		 SchermataAttesaControl sac= new SchermataAttesaControl();
		 try {
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setBounds(550,230,600,400);
			
			final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");
			
			JPanel backgroundPanel = new JPanel() {
				@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        
	        backgroundPanel.setBounds(100, 100, 516, 405);
	        getContentPane().add(backgroundPanel);
	    	backgroundPanel.setLayout(null);
	    	
	    	JLabel attesaserver = new JLabel(msg);
	    	
	    	if(msg.equals("ATTESA POSIZIONAMENTO")) {
	    		sac.attesa(userName);
	    	}
	    	attesaserver.setFont(new Font("Tahoma", Font.PLAIN, 42));
			attesaserver.setBounds(29, 135, 450, 93);
			backgroundPanel.add(attesaserver, BorderLayout.CENTER);
			
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
	/* public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	SchermataAttesaView frame = new SchermataAttesaView();
	                    frame.setVisible(true);
	                   
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void close(Socket socket) {
		try {
			DoubleGameGridView DGGV= new DoubleGameGridView();
			DGGV.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispose();
	}
	
	
	
}
	


