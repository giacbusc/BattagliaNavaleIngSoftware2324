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

import BattagliaNavaleProject.Control.LoginControl;
import BattagliaNavaleProject.form.LoginModel;
import BattagliaNavaleProject.form.SchermataIniziale;
import BattagliaNavaleProject.server.ConnectionDb;

public class LoginGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JLabel usernameLabel, passwordLabel;
	    private static JTextField usernameField;
	    private JPasswordField passwordField;
	    private JButton loginButton;
	    LoginModel model= new LoginModel();
	    
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
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public LoginGUI() throws SQLException, IOException {
		LoginModel model;
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
    	
        JButton backButton = new JButton("Back");
        backButton.setBounds(391, 232, 85, 21);
        backgroundPanel.add(backButton);
       
		loginButton.addActionListener(this);
	 backButton.addActionListener(this);

       
        
		/*{
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 String user = usernameField.getText();
		    	 String pw = passwordField.getText();
	              try {
					log.VerificaUtente(user,pw);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		    }
		});
		
		*/
	}
        
        public LoginModel getUserModel(){
            model = new LoginModel(usernameField.getText(), passwordField.getText());
            return model;       
        }
     
        public void showMessage(String msg){
            JOptionPane.showMessageDialog(this, msg);
        }
     
        public void addLoginListener(ActionListener log) {
              loginButton.addActionListener(log);
            }
	
    
        

		   
        
		
        		
        		/*new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	SchermataIniziale back = new SchermataIniziale();
	             back.setVisible(true);
	             
	             dispose(); 
		    }
        
		});
		*/
    

	public static String getUser() {
		try{
			return usernameField.getText();
			
		}
		catch (NullPointerException e){
			return "DEFAULTUSER";
		}
	}

	public static void setUsername(String username) {
		usernameField.setText(username);
	}

	public String getPassword() {
		return passwordField.getText();
	}

	public void setPassword(String password) {
		passwordField.setText(password);;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
