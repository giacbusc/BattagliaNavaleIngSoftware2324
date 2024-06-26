package BattagliaNavaleProject.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

public class MenuPrincipaleView extends JFrame {
	private static String userName;
	private JButton pcSoloButton;
	private JButton pcMultiButton;
	private JButton tutorialButton;
	private Observer obs;
	private JPanel classifica;
	//Classe che gestisce la visualizzazione del menu principale
	public MenuPrincipaleView(String userName, Observer obs) throws IOException, SQLException 
	{
		this.userName = userName;
		this.obs = obs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 788);
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
		Image scaledImage = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		final JLabel pcSoloLabel= new JLabel("Gioca su un solo PC");
		pcSoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		pcSoloLabel.setBounds(523,160,425,70);
		backgroundPanel.add(pcSoloLabel);

		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(200,200));
		logoLabel.setBounds(0, 0, 200, 200);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);

		classifica = new JPanel();
		classifica.setBounds(1163,113,217,531);
		classifica.setOpaque(false);
		classifica.setBorder(null);
		backgroundPanel.add(classifica);
		classifica.setLayout(new BoxLayout(classifica, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_2 = new JLabel("BATTAGLIA NAVALE ");
		lblNewLabel_2.setForeground(new Color(51, 128, 204));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(350, 0, 750, 85);
		backgroundPanel.add(lblNewLabel_2);

		final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
		Image buttonImage = hoverIcon.getImage();
		Image scaledButtonImage = buttonImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		final JLabel imageLabel =new JLabel(new ImageIcon(scaledButtonImage));
		final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
		final JLabel imageLabel3 =new JLabel(new ImageIcon(scaledButtonImage));
		imageLabel.setBounds(455,154,100,100);
		imageLabel2.setBounds(455,315,100,100);
		imageLabel3.setBounds(545,480,100,100);
		imageLabel.setVisible(false);
		imageLabel2.setVisible(false);
		imageLabel3.setVisible(false);
		backgroundPanel.add(imageLabel);
		backgroundPanel.add(imageLabel2);
		backgroundPanel.add(imageLabel3);


		pcSoloButton = new JButton();
		pcSoloButton.setName("Solo");

		pcSoloButton.setPreferredSize(new Dimension(370, 150));
		pcSoloButton.setBackground(new Color(0, 0, 0, 0));
		pcSoloButton.setOpaque(false);
		pcSoloButton.setBounds(461, 154, 538, 90);//275 70
		backgroundPanel.add(pcSoloButton);


		pcSoloButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{
				imageLabel.setVisible(true);
				pcSoloLabel.setForeground(Color.decode("#3380CC"));
				pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				pcSoloButton.repaint();
			}


			public void mouseExited(MouseEvent e)
			{
				imageLabel.setVisible(false);
				pcSoloLabel.setForeground(new Color(0, 0, 0));
				pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				pcSoloButton.repaint();

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

		final JLabel pcMutliLabel= new JLabel("Gioca su piu' pc");
		pcMutliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		pcMutliLabel.setBounds(523,320,425,70);
		backgroundPanel.add(pcMutliLabel);

		pcMultiButton = new JButton("  ");
		pcMultiButton.setName("Multi");
		pcMultiButton.setBackground(new Color(0, 0, 0, 0));
		pcMultiButton.setOpaque(false);
		pcMultiButton.setPreferredSize(new Dimension(170, 50));
		pcMultiButton.setBounds(461, 317, 538, 90);
		backgroundPanel.add(pcMultiButton);

		pcMultiButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{
				imageLabel2.setVisible(true);
				pcMutliLabel.setForeground(Color.decode("#3380CC"));
				pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				pcMultiButton.repaint();
			}


			public void mouseExited(MouseEvent e)
			{
				imageLabel2.setVisible(false);
				pcMutliLabel.setForeground(new Color(0, 0, 0));
				pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				pcMultiButton.repaint();

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



		final JLabel tutorialTextLabel= new JLabel("Tutorial");
		tutorialTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		tutorialTextLabel.setBounds(630,495,200,50);
		backgroundPanel.add(tutorialTextLabel);

		tutorialButton = new JButton("   ");
		tutorialButton.setName("tutorial");
		tutorialButton.setBackground(new Color(0, 0, 0, 0));
		tutorialButton.setOpaque(false);
		//tutorialButton.setBorderPainted(false);
		tutorialButton.setBounds(461, 480, 538, 90);
		tutorialButton.setPreferredSize(new Dimension(170, 50)); // Set the button size in pixels
		backgroundPanel.add(tutorialButton);

		JPanel titolo = new JPanel();
		titolo.setBounds(1092, 69, 286, 50);
		backgroundPanel.add(titolo);
		titolo.setOpaque(false);

		JLabel classificaLabel = new JLabel("Classifica");
		classificaLabel.setForeground(new Color(51, 128, 204));
		classificaLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titolo.add(classificaLabel);

		tutorialButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{
				imageLabel3.setVisible(true);
				tutorialTextLabel.setForeground(Color.decode("#3380CC"));
				tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				tutorialButton.repaint();
			}


			public void mouseExited(MouseEvent e)
			{
				imageLabel3.setVisible(false);
				tutorialTextLabel.setForeground(new Color(0, 0, 0));
				tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				tutorialButton.repaint();

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

	public String getUsername()
	{
		return userName;
	}

	public Observer getObserver()
	{
		return obs;
	}
	//Metodo che aggiunge un action listener al pulsante per avviare la partita su pc diversi
	public void addActionMulti(ActionListener act)
	{
		pcMultiButton.addActionListener(act);
	}
	//Metodo che aggiunge un action listener al pulsante per avviare la partita sullo stesso pc
	public void addActionSolo(ActionListener act)
	{
		pcSoloButton.addActionListener(act);
	}
	//Metodo che aggiunge un action listener al pulsante per il tutorial
	public void addActionTutorial(ActionListener act)
	{
		tutorialButton.addActionListener(act);
	}

	//Metodo che viene utilizzato per mostrare la classifica sulla schermata
	public void mostraClassifica(String user, int vitt, int pos)
	{
		String spazio = "      ";
		JLabel lb = new JLabel(""+pos+") "+user+""+spazio+""+vitt);
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setFont(new Font("Tahoma", Font.BOLD, 16));
		classifica.add(lb);
		classifica.repaint();

	}
}