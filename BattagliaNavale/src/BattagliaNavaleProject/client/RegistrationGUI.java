package BattagliaNavaleProject.client;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

public class RegistrationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(143, 0, 163, 55);
		backgroundPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nickname:");
		lblNewLabel_1.setBounds(38, 143, 76, 20);
		backgroundPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("password:");
		lblNewLabel_2.setBounds(38, 186, 76, 20);
		backgroundPanel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(119, 54, 163, 19);
		backgroundPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 144, 163, 19);
		backgroundPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton saveButton = new JButton("save");
		saveButton.addActionListener(new ActionListener() {
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
		
		saveButton.setBounds(10, 232, 85, 21);
		backgroundPanel.add(saveButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 187, 163, 19);
		backgroundPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setBounds(38, 57, 45, 13);
		backgroundPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Surname:");
		lblNewLabel_4.setBounds(38, 104, 65, 13);
		backgroundPanel.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(119, 101, 163, 19);
		backgroundPanel.add(textField_3);
		textField_3.setColumns(10);
		
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
}
