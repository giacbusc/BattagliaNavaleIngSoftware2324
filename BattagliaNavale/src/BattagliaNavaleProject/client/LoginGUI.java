package BattagliaNavaleProject.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BattagliaNavaleProject.server.ConnectionDb;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JLabel usernameLabel, passwordLabel;
	    private JTextField usernameField;
	    private JPasswordField passwordField;
	    private JButton loginButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
        
        
        JLabel lblNewLabel_2 = new JLabel("Welcome Back");
        lblNewLabel_2.setBounds(0, 0, 499, 85);
        lblNewLabel_2.setForeground(new Color(0, 0, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        
		backgroundPanel.add(lblNewLabel_2);
		
		
		setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 90, 80, 25);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(110, 90, 150, 25);
        backgroundPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 130, 80, 25);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 130, 150, 25);
        backgroundPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 180, 80, 25);
        //loginButton.addActionListener(this);
        backgroundPanel.add(loginButton);
    	
		loginButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 String user = usernameField.getText();
		    	 String pw = passwordField.getText();
	              VerificaUtente(user,pw);
		    	MenuPrincipale menu;
				try {
					menu = new MenuPrincipale(); 
					menu.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	           
	             
	             dispose(); 
		    }

		    private void VerificaUtente(String nickname,String password) {
		         // Prepara la query SQL
		        
		            try {
		                String sql = "SELECT * FROM utente WHERE nickname = ? AND password = ?";
		                Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "1234");
		                PreparedStatement pstmt = conn.prepareStatement(sql);
		                pstmt.setString(1, nickname);
		                pstmt.setString(2, password);

		                ResultSet rs = pstmt.executeQuery();
		                if (rs.next()) {
		                    // Utente trovato
		                    System.out.println("Utente trovato!");
		                } else {
		                    // Utente non trovato
		                    System.out.println("Utente non trovato!");
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		    }
		});
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(391, 232, 85, 21);
        backgroundPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	SchermataIniziale back = new SchermataIniziale();
	             back.setVisible(true);
	             
	             dispose(); 
		    }
		});
    }
}
