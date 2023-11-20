package BattagliaNavaleProject.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import java.awt.Color;

//pagina per loggarsi/registrarsi
public class SchermataIniziale extends JFrame implements ActionListener {
	
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
		
		final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");
		
		JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(backgroundPanel);
        backgroundPanel.setLayout(null);
        
		loginButton.setBounds(128, 60, 166, 62);
		
		
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginButton.addActionListener(this);
		loginButton.setBackground(new Color(0, 0, 0, 0));
		loginButton.setOpaque(false);
		backgroundPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	LoginGUI login = new LoginGUI();
	             login.setVisible(true);
	             
	             dispose(); 
		    }
		});
		registerButton.setBounds(128, 163, 166, 62);
       
		
		
		registerButton.addActionListener(this);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		registerButton.setBackground(new Color(0, 0, 0, 0));
		registerButton.setOpaque(false);
		backgroundPanel.add(registerButton);
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
		infoLabel.setBounds(128, 146, 166, 16);
		infoLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
		backgroundPanel.add(infoLabel);
		
		JLabel titleLabel = new JLabel("Welcome in BattleShip game");
		titleLabel.setBounds(55, 10, 354, 40);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		titleLabel.setForeground(new Color(0, 128, 255));
		backgroundPanel.add(titleLabel);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	
	
	