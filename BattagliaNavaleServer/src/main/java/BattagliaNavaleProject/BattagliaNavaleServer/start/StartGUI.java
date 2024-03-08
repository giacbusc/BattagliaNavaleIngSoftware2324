package BattagliaNavaleProject.BattagliaNavaleServer.start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

public class StartGUI extends JFrame {

	private final JButton indirizzoButton;
	public JTextField indirizzoField;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { SelezioneIndirizzoView frame = new
	 * SelezioneIndirizzoView(username); //LoginControl cont = new
	 * LoginControl(frame); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	public StartGUI() {
		// TODO Auto-generated constructor stub

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

		JLabel lblNewLabel_2 = new JLabel("Selezione indirizzo server");
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
		indirizzoLabel.setBounds(102, 91, 162, 25);
		indirizzoLabel.setForeground(Color.decode("#3380CC"));
		indirizzoLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		indirizzoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(indirizzoLabel);

		JLabel infoIndirizzo = new JLabel("<html>Inserire l'indirizzo ipv4 della tua macchina seguito dalla porta<br/>esempio: 192.168.1.2:5555</html>");
		infoIndirizzo.setBounds(259, 111, 243, 68);
		infoIndirizzo.setForeground(Color.black);
		infoIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 12));
		infoIndirizzo.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(infoIndirizzo);
		
		indirizzoField = new JTextField(15);
		indirizzoField.setBounds(259, 91, 243, 25);
		backgroundPanel.add(indirizzoField);

		indirizzoButton = new JButton("Avvia server multiplayer");
		indirizzoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = new String(indirizzoField.getText());
				
				try {
					dispose();
					StartControl.avvioSelection(ip);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		indirizzoButton.setBounds(259, 178, 243, 53);
		indirizzoButton.setBackground(new Color(0, 0, 0, 0));
		indirizzoButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		indirizzoButton.setOpaque(false);
		// loginButton.addActionListener(this);
		backgroundPanel.add(indirizzoButton);
		
		JLabel lblVuoiAvviareUn = new JLabel("Vuoi avviare un server per giocare in locale?");
		lblVuoiAvviareUn.setHorizontalAlignment(SwingConstants.CENTER);
		lblVuoiAvviareUn.setForeground(Color.BLACK);
		lblVuoiAvviareUn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVuoiAvviareUn.setBounds(38, 264, 331, 68);
		backgroundPanel.add(lblVuoiAvviareUn);
		
		JButton btnAvviaServerLocale = new JButton("Avvia server locale");
		btnAvviaServerLocale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					StartControl.avvioSelection("LOCALE");
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAvviaServerLocale.setOpaque(false);
		btnAvviaServerLocale.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAvviaServerLocale.setBackground(new Color(0, 0, 0, 0));
		btnAvviaServerLocale.setBounds(362, 284, 243, 29);
		backgroundPanel.add(btnAvviaServerLocale);

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

		setVisible(true);
	}


}

