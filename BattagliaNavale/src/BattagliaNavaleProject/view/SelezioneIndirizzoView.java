package BattagliaNavaleProject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.SwingWorker;

public class SelezioneIndirizzoView extends JFrame {

	private static String username;
	private final JButton indirizzoButton;
	private final JButton backButton;
	public JTextField indirizzoField;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { SelezioneIndirizzoView frame = new
	 * SelezioneIndirizzoView(username); //LoginControl cont = new
	 * LoginControl(frame); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	public SelezioneIndirizzoView(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		// Calcola le coordinate x e y per centrare la finestra
		int x = (dimensioniSchermo.width - getWidth()) / 2;
		int y = (dimensioniSchermo.height - getHeight()) / 2;

		setBounds(x - (650 / 2), y - (336 / 2), 650, 366);

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

		JLabel lblNewLabel_2 = new JLabel("Inserisci indirizzo server");
		lblNewLabel_2.setBounds(20, 0, 620, 85);
		lblNewLabel_2.setForeground(Color.decode("#3380CC"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(lblNewLabel_2);

		setTitle("Selezione Indirizzo");

		ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(80, 80));
		logoLabel.setBounds(0, 0, 90, 80);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);

		JLabel indirizzoLabel = new JLabel("Indirizzo:");
		indirizzoLabel.setBounds(106, 128, 162, 25);
		indirizzoLabel.setForeground(Color.decode("#3380CC"));
		indirizzoLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		indirizzoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(indirizzoLabel);

		JLabel infoIndirizzo = new JLabel("Inserire l'indirizzo ipv4 del server e la porta");
		infoIndirizzo.setBounds(260, 159, 257, 20);
		infoIndirizzo.setForeground(Color.black);
		infoIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 10));
		infoIndirizzo.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(infoIndirizzo);
		JLabel infoIndirizzo2 = new JLabel("esempio: 192.168.1.2:5555");
		infoIndirizzo2.setBounds(286, 178, 180, 20);
		infoIndirizzo2.setForeground(Color.black);
		infoIndirizzo2.setFont(new Font("Tahoma", Font.BOLD, 10));
		infoIndirizzo2.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(infoIndirizzo2);
		
		indirizzoField = new JTextField(15);
		indirizzoField.setBounds(259, 128, 243, 25);
		backgroundPanel.add(indirizzoField);

		indirizzoButton = new JButton("Gioca");
		indirizzoButton.setBounds(69, 264, 108, 25);
		indirizzoButton.setBackground(new Color(0, 0, 0, 0));
		indirizzoButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		indirizzoButton.setOpaque(false);
		// loginButton.addActionListener(this);
		backgroundPanel.add(indirizzoButton);

		indirizzoButton.addMouseListener(new MouseListener() {

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
				indirizzoButton.setForeground(Color.decode("#3380CC"));
				indirizzoButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				indirizzoButton.setForeground(new Color(0, 0, 0));
				indirizzoButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			}

		});

		backButton = new JButton("Back");
		backButton.setBounds(500, 266, 108, 25);
		backButton.setBackground(new Color(0, 0, 0, 0));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setOpaque(false);
		backgroundPanel.add(backButton);

		backButton.addMouseListener(new MouseListener() {

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

		setVisible(true);
	}

	public void aggiungiListenerNext(ActionListener act) {
		// TODO Auto-generated method stub
		indirizzoButton.addActionListener(act);
	}

	public void aggiungiListenerBack(ActionListener act) {
		// TODO Auto-generated method stub
		backButton.addActionListener(act);
	}

}
