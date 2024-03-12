package BattagliaNavaleProject.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class SchermataAttesaView extends JFrame {
	String msg;
	String username;
	Observer obs;
	
	//Classe che gestisce la visualizzazione della schermata di attesa
	public SchermataAttesaView(String msg,String userName, Observer obs) {
		this.msg=msg;
		this.obs=obs;
		this.username=userName;
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

			// Calcola le coordinate x e y per centrare la finestra
			int x = (dimensioniSchermo.width - getWidth()) / 2;
			int y = (dimensioniSchermo.height - getHeight()) / 2;

			setBounds(x-(650/2),y-(366/2),650,366);



			final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");

			JPanel backgroundPanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
				}
			};

			backgroundPanel.setBounds(100, 100, 650, 366);
			getContentPane().add(backgroundPanel);
			backgroundPanel.setLayout(null);

			JLabel attesaserver = new JLabel(msg);
			attesaserver.setHorizontalAlignment(SwingConstants.CENTER);
			attesaserver.setAlignmentX(CENTER_ALIGNMENT);
			attesaserver.setFont(new Font("Tahoma", Font.PLAIN, 35));
			attesaserver.setBounds(97, 118, 450, 93);
			backgroundPanel.add(attesaserver, BorderLayout.CENTER);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Observer getObserver() {
		// TODO Auto-generated method stub
		return obs;
	}



}



