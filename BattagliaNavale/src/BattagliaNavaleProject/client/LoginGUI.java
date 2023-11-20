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

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image background;
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//vogliamo inserire un'immagine di sfondo
		
		ImageIcon backgroundImageIcon = new ImageIcon("../docs/resources/SfondoTest.jpeg");
        background = backgroundImageIcon.getImage();
        contentPane.setLayout(null);
        
        
        JLabel lblNewLabel_2 = new JLabel("Welcome Back");
        lblNewLabel_2.setBounds(0, 0, 499, 85);
        lblNewLabel_2.setForeground(new Color(0, 0, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        
		contentPane.add(lblNewLabel_2);
		
		
		setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 90, 80, 25);
        getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(110, 90, 150, 25);
        getContentPane().add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 130, 80, 25);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 130, 150, 25);
        getContentPane().add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 180, 80, 25);
        //loginButton.addActionListener(this);
        getContentPane().add(loginButton);
    	
		loginButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // action to perform when the button is used
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
		});
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(391, 232, 85, 21);
        contentPane.add(backButton);
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

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Simulazione di credenziali corrette
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Accesso riuscito!");
            // Puoi aggiungere qui la logica per aprire una nuova finestra o eseguire altre azioni dopo il login
        } else {
            JOptionPane.showMessageDialog(this, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
        }
	}
}
