package BattagliaNavaleProject.client;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import BattagliaNavaleProject.server.ConnectionDb;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

public class RegistrationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField nicknameField;
	private JTextField passwordField;
	private JTextField surnameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationGUI frame = new RegistrationGUI();
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
	public RegistrationGUI() {
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
		
		JLabel titleLabel = new JLabel("Registration");
		titleLabel.setForeground(new Color(0, 0, 255));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(143, 0, 163, 55);
		backgroundPanel.add(titleLabel);
		
		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameLabel.setBounds(38, 143, 76, 20);
		backgroundPanel.add(nicknameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(38, 186, 76, 20);
		backgroundPanel.add(passwordLabel);
		
		nameField = new JTextField();
		nameField.setBounds(119, 54, 163, 19);
		backgroundPanel.add(nameField);
		nameField.setColumns(10);
		
		nicknameField = new JTextField();
		nicknameField.setBounds(119, 144, 163, 19);
		backgroundPanel.add(nicknameField);
		nicknameField.setColumns(10);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // action to perform when the button is used
		    	
		    	try {
					if(verificaCampi() && verificaNomeCognome() && verificaNickname()){
						if(salvaDati()) {
							JOptionPane.showMessageDialog(null, "Registration complete!");
							LoginGUI login;
							login = new LoginGUI(); 
							login.setVisible(true);
							dispose(); 
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Incorrect data entered, please re-enter it!!!");
							nameField.setText("");
							surnameField.setText("");
							nicknameField.setText("");
							passwordField.setText("");
						}
					}
					else {
							JOptionPane.showMessageDialog(null, "Incorrect data entered, please re-enter it");
					}
						

					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	             
	             
		    }
		});
		
		saveButton.setBounds(10, 232, 85, 21);
		backgroundPanel.add(saveButton);
		
		passwordField = new JTextField();
		passwordField.setBounds(119, 187, 163, 19);
		backgroundPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(38, 57, 45, 13);
		backgroundPanel.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(38, 104, 65, 13);
		backgroundPanel.add(surnameLabel);
		
		surnameField = new JTextField();
		surnameField.setBounds(119, 101, 163, 19);
		backgroundPanel.add(surnameField);
		surnameField.setColumns(10);
		
		JButton backbutton = new JButton("Back");
        backbutton.setBounds(341, 232, 85, 21);
        backgroundPanel.add(backbutton);
        backbutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	SchermataIniziale back = new SchermataIniziale();
	             back.setVisible(true);
	             
	             dispose(); 
		    }
		});
        
	}
	
	private boolean verificaCampi(){
		if(nameField.getText().isEmpty() || surnameField.getText().isEmpty() || nicknameField.getText().isEmpty() || passwordField.getText().isEmpty()){
			return false;
		}
		
		return true;
	}
	
	private boolean verificaNomeCognome(){
		if(!nameField.getText().matches("[a-zA-Z]+") || !surnameField.getText().matches("[a-zA-Z]+")) {
			return false;
		}
		
		return true;
	}
	
	private boolean verificaNickname() throws SQLException
	{
		ConnectionDb conn = new ConnectionDb();
		
		String nickname = nicknameField.getText();
		String sql = "SELECT nickname FROM UTENTE WHERE nickname = ?";
		PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
		pstmt.setString(1, nickname);

		ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
       	 return false;
         }
         else 
 		{
 			return true;
 		}
		
	}
	
	private boolean salvaDati() throws SQLException
	{
		String name = nameField.getText();
		String surname = surnameField.getText();
		String nickname = nicknameField.getText();
		String password = passwordField.getText();
		String sql = "INSERT INTO UTENTE VALUES ('"+name+"','"+surname+"','"+nickname+"','"+password+"')";
	
		ConnectionDb conn = new ConnectionDb();
		boolean resultSet = conn.insertQuery(sql);
		return true;
		
	}
}
