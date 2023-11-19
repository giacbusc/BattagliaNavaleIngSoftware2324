package BattagliaNavaleProject.client;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image background;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//vogliamo inserire un'immagine di sfondo
		
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
