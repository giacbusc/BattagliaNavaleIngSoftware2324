package BattagliaNavaleProject.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BattagliaNavaleProject.formModel.RegistrationModel;



public class RegistrationView extends JFrame  implements Observer{

	private static final long serialVersionUID = 1L;
	private JTextField txtNickname;
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField txtPassword;

	private Observer obs;
	private JButton btnRegistration;
	private JButton backbutton;
	private RegistrationModel model;

	//Classe che gestisce la visualizzazione della schermata di registration
	public RegistrationView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500,250,650,366);

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

		ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(80,80));
		logoLabel.setBounds(0, 0, 90, 80);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);

		JLabel titleLabel = new JLabel("Registration");
		titleLabel.setBounds(100, 0, 412, 55);
		titleLabel.setForeground(Color.decode("#3380CC"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(titleLabel);

		JLabel nicknameLabel = new JLabel("NICKNAME:");
		nicknameLabel.setForeground(Color.decode("#3380CC"));
		nicknameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nicknameLabel.setBounds(114, 175, 128, 24);
		backgroundPanel.add(nicknameLabel);

		JLabel passwordLabel = new JLabel("PASSWORD:");
		passwordLabel.setBounds(114, 229, 128, 24);
		passwordLabel.setForeground(Color.decode("#3380CC"));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		backgroundPanel.add(passwordLabel);

		txtName = new JTextField(20);
		txtName.setBounds(264, 74, 154, 19);
		backgroundPanel.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField(20);
		txtSurname.setBounds(264, 121, 154, 19);
		backgroundPanel.add(txtSurname);
		txtSurname.setColumns(10);


		txtNickname = new JTextField(20);
		txtNickname.setBounds(264, 175, 154, 19);
		backgroundPanel.add(txtNickname);
		txtNickname.setColumns(10);

		JLabel nameLabel = new JLabel("NAME:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nameLabel.setForeground(Color.decode("#3380CC"));
		nameLabel.setBounds(148, 68, 94, 24);
		backgroundPanel.add(nameLabel);

		JLabel surnameLabel = new JLabel("SURNAME:");
		surnameLabel.setBounds(123, 121, 119, 24);
		surnameLabel.setForeground(Color.decode("#3380CC"));
		surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		backgroundPanel.add(surnameLabel);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(264, 229, 154, 19);
		txtPassword.setEchoChar('*');
		backgroundPanel.add(txtPassword);
		txtPassword.setColumns(10);

		btnRegistration = new JButton("Save");
		btnRegistration.setContentAreaFilled(false);
		btnRegistration.setBounds(31, 284, 108, 25);
		btnRegistration.setBackground(new Color(0, 0, 0, 0));
		btnRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		backgroundPanel.add(btnRegistration);
		
		btnRegistration.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						btnRegistration.setForeground(new Color(0, 128, 255));
			    		btnRegistration.setFont(new Font("Tahoma", Font.BOLD, 18));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						btnRegistration.setForeground(new Color(0, 0, 0));
			    		btnRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
			
				}
				);

		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBounds(507, 284, 108, 25);
		backbutton.setBackground(new Color(0, 0, 0, 0));
		backbutton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backgroundPanel.add(backbutton);

		backbutton.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				backbutton.setForeground(Color.decode("#3380CC"));
	    		backbutton.setFont(new Font("Tahoma", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				backbutton.setForeground(new Color(0, 0, 0));
	    		backbutton.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
	
		}
		);

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	//Metodo per aggiungere un action listener al bottone back
	public void addActionBack(ActionListener act)
	{
		backbutton.addActionListener(act);
	}

	public RegistrationModel getUser(){
		model = new RegistrationModel(txtName.getText(),txtSurname.getText(),txtNickname.getText(),txtPassword.getText());
		return model;       
	}

	public void showMessage(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}
	
	//Metodo per aggiungere un action listener al bottone registration
	public void addRecListener(ActionListener log) {
		btnRegistration.addActionListener(log);
	}

	public void setObserver(Observer obs)
	{
		this.obs = obs;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public Observer getObserver() {
		// TODO Auto-generated method stub
		return obs;
	}

}

