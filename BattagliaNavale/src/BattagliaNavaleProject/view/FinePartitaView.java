package BattagliaNavaleProject.view;
import javax.swing.*;

import BattagliaNavaleProject.control.ConnectionControl;
import BattagliaNavaleProject.control.FinePartitaControl;
import BattagliaNavaleProject.control.MenuPrincipaleControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

public class FinePartitaView extends JFrame implements ActionListener {
	private String userName;
	private String msg;
	private JButton menuButton;
	private JButton exitButton;
	MenuPrincipaleView menu;

	public FinePartitaView(String userName, String messaggio) throws IOException, SQLException 
	{	
		this.userName = userName;
		this.msg=messaggio;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 788);
		setVisible(true);
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
		backgroundPanel.setVisible(true);



		final JLabel userLabel = new JLabel(userName);
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(530, 30, 425, 40);
		backgroundPanel.add(userLabel);

		final JLabel lblNewLabel_3 = new JLabel("Vuoi giocare ancora?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(520, 330, 425, 40);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(lblNewLabel_3);

		final JLabel lblNewLabel_4 = new JLabel("Ne sei sicuro?");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(520, 498, 425, 40);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(lblNewLabel_4);

		ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);


		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(200,200));
		logoLabel.setBounds(0, 0, 200, 200);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);
		
		final JLabel menuLabel= new JLabel("TORNA AL MENU");
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		menuLabel.setBounds(545,255,367,80);
		backgroundPanel.add(menuLabel);

		JLabel lblNewLabel_2 = new JLabel(msg);
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(365, 115, 750, 85);
		backgroundPanel.add(lblNewLabel_2);

		final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
		Image buttonImage = hoverIcon.getImage();
		Image scaledButtonImage = buttonImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
		final JLabel imageLabel3 =new JLabel(new ImageIcon(scaledButtonImage));
		imageLabel2.setBounds(456,245,100,100);
		imageLabel3.setBounds(575,405,100,100);
		imageLabel2.setVisible(false);
		imageLabel3.setVisible(false);
		backgroundPanel.add(imageLabel2);
		backgroundPanel.add(imageLabel3);


		menuButton = new JButton("");
		menuButton.setName("menu");
		menuButton.setBackground(new Color(0, 0, 0, 0));
		menuButton.setOpaque(false);
		menuButton.setBounds(455, 250, 538, 90);//275 70
		menuButton.setVisible(true);
		backgroundPanel.add(menuButton);



		menuButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{
				imageLabel2.setVisible(true);
				menuLabel.setForeground(new Color(0, 128, 255));
				menuLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
				menuButton.repaint();
			}


			public void mouseExited(MouseEvent e)
			{
				imageLabel2.setVisible(false);
				menuLabel.setForeground(new Color(0, 0, 0));
				menuLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
				menuButton.repaint();

			}


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

		});

		final JLabel esciLabel= new JLabel("EXIT");
		esciLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esciLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		esciLabel.setBounds(580,415,306,76);
		backgroundPanel.add(esciLabel);

		exitButton = new JButton(".");
		exitButton.setName("esci");
		exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setOpaque(false);
		exitButton.setBounds(455, 409, 538, 90);
		exitButton.setVisible(true);
		backgroundPanel.add(exitButton);

		exitButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{
				imageLabel3.setVisible(true);
				esciLabel.setForeground(new Color(0, 128, 255));
				esciLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
				exitButton.repaint();
			}


			public void mouseExited(MouseEvent e)
			{
				imageLabel3.setVisible(false);
				esciLabel.setForeground(new Color(0, 0, 0));
				esciLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
				exitButton.repaint();

			}


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

		});


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}


	public void aggiungiListenerMenu(ActionListener act)
	{
		menuButton.addActionListener(act);
	}

	public void aggiungiListenerExit(ActionListener act)
	{
		exitButton.addActionListener(act);
	}

}





