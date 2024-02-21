package BattagliaNavaleProject.formGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
import javax.swing.SwingWorker;

import BattagliaNavaleProject.Control.LoginControl;
import BattagliaNavaleProject.Control.RegistrationControl;
import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.form.RegistrationModel;



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
		titleLabel.setForeground(new Color(0, 128, 255));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(titleLabel);

		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameLabel.setForeground(new Color(0, 128, 255));
		nicknameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nicknameLabel.setBounds(114, 175, 99, 13);
		backgroundPanel.add(nicknameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(114, 229, 99, 13);
		passwordLabel.setForeground(new Color(0, 128, 255));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		backgroundPanel.add(passwordLabel);

		txtName = new JTextField(20);
		txtName.setBounds(232, 68, 154, 19);
		backgroundPanel.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField(20);
		txtSurname.setBounds(232, 121, 154, 19);
		backgroundPanel.add(txtSurname);
		txtSurname.setColumns(10);


		txtNickname = new JTextField(20);
		txtNickname.setBounds(232, 175, 154, 19);
		backgroundPanel.add(txtNickname);
		txtNickname.setColumns(10);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nameLabel.setForeground(new Color(0, 128, 255));
		nameLabel.setBounds(148, 68, 65, 13);
		backgroundPanel.add(nameLabel);

		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(123, 121, 99, 13);
		surnameLabel.setForeground(new Color(0, 128, 255));
		surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		backgroundPanel.add(surnameLabel);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(232, 229, 154, 19);
		txtPassword.setEchoChar('*');
		backgroundPanel.add(txtPassword);
		txtPassword.setColumns(10);

		btnRegistration = new JButton("Save");
		btnRegistration.setContentAreaFilled(false);
		btnRegistration.setBounds(30, 284, 108, 25);
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
		backbutton.setBounds(527, 284, 108, 25);
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
				backbutton.setForeground(new Color(0, 128, 255));
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

	public void addActionReg(ActionListener act)
	{
		btnRegistration.addActionListener(act);
	}

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

	public void addRecListener(ActionListener log) {
		btnRegistration.addActionListener(log);
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationView frame = new RegistrationView();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

