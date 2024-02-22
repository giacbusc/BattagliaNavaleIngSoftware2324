package BattagliaNavaleProject.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
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

import BattagliaNavaleProject.control.DoubleGameGridControl;
import BattagliaNavaleProject.control.SchermataAttesaControl;

import java.awt.BorderLayout;

public class SchermataAttesaView extends JFrame {
String msg;
String username;
Observer obs;

	 public SchermataAttesaView(String msg,String userName, Observer obs) {
		 this.msg=msg;
		 this.obs=obs;
		 this.username=userName;
		 try {
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			 Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		        // Calcola le coordinate x e y per centrare la finestra
		        int x = (dimensioniSchermo.width - getWidth()) / 2;
		        int y = (dimensioniSchermo.height - getHeight()) / 2;
		        
		        setBounds(x-(525/2),y-(200),520,400);
			
	    	
			
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
	    	attesaserver.setAlignmentX(CENTER_ALIGNMENT);
	    	attesaserver.setFont(new Font("Tahoma", Font.PLAIN, 35));
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

	public Observer getObserver() {
		// TODO Auto-generated method stub
		return obs;
	}
	
	
	
}
	


