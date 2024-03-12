package BattagliaNavaleProject.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BattagliaNavaleProject.formModel.LoginModel;

import java.awt.Dimension;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private LoginModel model;
	private JButton backButton;
	private Observer obs;

	//Classe che gestisce la visualizzazione della schermata di login
	public LoginView() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		// Calcola le coordinate x e y per centrare la finestra
		int x = (dimensioniSchermo.width - getWidth()) / 2;
		int y = (dimensioniSchermo.height - getHeight()) / 2;

		setBounds(x-(650/2),y-(336/2),650,366);

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
		lblNewLabel_2.setBounds(0, 0, 620, 85);
		lblNewLabel_2.setForeground(Color.decode("#3380CC"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		backgroundPanel.add(lblNewLabel_2);


		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 366);


		ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(80,80));
		logoLabel.setBounds(0, 0, 90, 80);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);

		usernameLabel = new JLabel("USERNAME:");
		usernameLabel.setBounds(106, 128, 162, 25);
		usernameLabel.setForeground(Color.decode("#3380CC"));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(usernameLabel);

		usernameField = new JTextField(15);
		usernameField.setBounds(278, 128, 150, 25);
		backgroundPanel.add(usernameField);

		passwordLabel = new JLabel("PASSWORD:");
		passwordLabel.setBounds(106, 182, 162, 25);
		passwordLabel.setForeground(Color.decode("#3380CC"));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(passwordLabel);

		passwordField = new JPasswordField(15);
		passwordField.setBounds(278, 182, 150, 25);
		backgroundPanel.add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(69, 264, 108, 25);
		loginButton.setBackground(new Color(0, 0, 0, 0));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginButton.setOpaque(false);
		//loginButton.addActionListener(this);
		backgroundPanel.add(loginButton);



		loginButton.addMouseListener(new MouseListener()
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
				loginButton.setForeground(Color.decode("#3380CC"));
				loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				loginButton.setForeground(new Color(0, 0, 0));
				loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			}

		});


		backButton = new JButton("Back");
		backButton.setBounds(500, 266, 108, 25);
		backButton.setBackground(new Color(0, 0, 0, 0));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setOpaque(false);
		backgroundPanel.add(backButton);

		backButton.addMouseListener(new MouseListener()
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
				backButton.setForeground(Color.decode("#3380CC"));
				backButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				backButton.setForeground(new Color(0, 0, 0));
				backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			}

		});
	}
	//Metodo che aggiunge un action listener al pulsante login
	public void addActionLogin(ActionListener act)
	{
		loginButton.addActionListener(act);
	}
	//Metodo che aggiunge un action listener al pulsante back
	public void addActionBack(ActionListener act)
	{
		backButton.addActionListener(act);
	}

	public LoginModel getUserModel(){
		model = new LoginModel(usernameField.getText(), passwordField.getText());
		return model;       
	}

	public void showMessage(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}


	public String getUser() {
		return usernameField.getText();

	}

	public void setObserver(Observer obs)
	{
		this.obs = obs;
	}

	public Observer getObserver()
	{
		return obs;
	}
}
