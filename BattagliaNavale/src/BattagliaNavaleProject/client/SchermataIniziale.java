package BattagliaNavaleProject.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Color;
//pagina per loggarsi/registrarsi
public class SchermataIniziale extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	JButton loginButton = new JButton("LOGIN");
	JButton registerButton = new JButton("REGISTER");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchermataIniziale frame = new SchermataIniziale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SchermataIniziale() 
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		loginButton.addActionListener(this);
		loginButton.setBounds(144, 82, 166, 70);
		contentPane.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	LoginGUI login = new LoginGUI();
	             login.setVisible(true);
	             
	             dispose(); 
		    }
		});
       
		
		
		registerButton.addActionListener(this);
		registerButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		registerButton.setBounds(144, 181, 166, 70);
		contentPane.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	RegistrationGUI registrationGUI = new RegistrationGUI();
	            registrationGUI.setVisible(true);
	             dispose(); 
		    }
		});
		
		JLabel infoLabel = new JLabel("New player? Start here!");
		infoLabel.setFont(new Font("Shree Devanagari 714", Font.ITALIC, 11));
		infoLabel.setBounds(165, 164, 132, 16);
		contentPane.add(infoLabel);
		
		JLabel titleLabel = new JLabel("Welcome in BattleShip game");
		titleLabel.setFont(new Font("Shree Devanagari 714", Font.PLAIN, 26));
		titleLabel.setBounds(62, 6, 354, 75);
		contentPane.add(titleLabel);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	
	
	