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

import BattagliaNavaleProject.form.SchermataAttesaModel;

import java.awt.BorderLayout;

public class SchermataAttesa extends JFrame implements ActionListener{

	 public SchermataAttesa() {
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 516, 405);
			
			final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");
			
			JPanel backgroundPanel = new JPanel() {
	            
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        getContentPane().add(backgroundPanel);
	    	backgroundPanel.setLayout(null);
	    	
	    	JLabel attesaserver = new JLabel("ATTESA AVVERSARIO...");
	    	attesaserver.setFont(new Font("Tahoma", Font.PLAIN, 42));
			attesaserver.setBounds(31, 49, 471, 259);
			backgroundPanel.add(attesaserver);
	 }
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	SchermataAttesa frame = new SchermataAttesa();
	                    frame.setVisible(true);
	                   
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
	


