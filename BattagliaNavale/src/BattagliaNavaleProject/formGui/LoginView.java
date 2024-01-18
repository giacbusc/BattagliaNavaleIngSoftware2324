package BattagliaNavaleProject.formGui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

import BattagliaNavaleProject.Control.LoginControl;
import BattagliaNavaleProject.form.LoginModel;

public class LoginView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private LoginModel model;
	private JButton backButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					//LoginControl cont = new LoginControl(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public LoginView() 
	{
		//LoginModel model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		LoginControl cont;
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
        //setLocationRelativeTo(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 90, 80, 25);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField(15);
        usernameField.setBounds(110, 90, 150, 25);
        backgroundPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 130, 80, 25);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(110, 130, 150, 25);
        backgroundPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 180, 80, 25);
        //loginButton.addActionListener(this);
        backgroundPanel.add(loginButton);
    	
        backButton = new JButton("Back");
        backButton.setBounds(391, 232, 85, 21);
        backgroundPanel.add(backButton);
       
		loginButton.addActionListener(new LoginControl(this));
		backButton.addActionListener(new LoginControl(this));
        
       
       
	}
        
        
		public LoginModel getUserModel(){
            model = new LoginModel(usernameField.getText(), passwordField.getText());
            return model;       
        }
     
        public void showMessage(String msg){
            JOptionPane.showMessageDialog(this, msg);
        }
     
        public void addLoginListener(ActionListener log) 
        {
              loginButton.addActionListener(log);
        }
    

	public String getUser() {
		try{
			return usernameField.getText();
			
		}
		catch (NullPointerException e){
			return "DEFAULTUSER";
		}
	}
	
	public void close() {
		SchermataInizialeView inizio;
		inizio = new SchermataInizialeView(); 
		dispose();
		inizio.setVisible(true);
	}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}



public void openMenu() throws IOException, SQLException {

    MenuPrincipaleView menu = new MenuPrincipaleView(); 
	menu.setVisible(true);
	dispose(); 
}

	
}
