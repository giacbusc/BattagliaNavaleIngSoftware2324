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

public class RegistrationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image background;
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//PROVA
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(143, 0, 163, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nickname:");
		lblNewLabel_1.setBounds(38, 143, 76, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("password:");
		lblNewLabel_2.setBounds(38, 186, 76, 20);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(119, 54, 163, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 144, 163, 19);
		contentPane.add(textField_1);
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
		
		saveButton.setBounds(38, 232, 85, 21);
		contentPane.add(saveButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 187, 163, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setBounds(38, 57, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Surname:");
		lblNewLabel_4.setBounds(38, 104, 65, 13);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(119, 101, 163, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton backbutton = new JButton("Back");
        backbutton.setBounds(341, 232, 85, 21);
        contentPane.add(backbutton);
        backbutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Azioni da eseguire quando il pulsante viene premuto
		    	SchermataIniziale back = new SchermataIniziale();
	             back.setVisible(true);
	             
	             dispose(); 
		    }
		});
       
		
		ImageIcon backgroundImageIcon = new ImageIcon("../docs/resources/SfondoTest.jpeg");
        background = backgroundImageIcon.getImage();
        
	}
	@Override
    public void paint(Graphics g) {
        super.paint(g);

        // Disegna l'immagine di sfondo
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        
    }
}
