package BattagliaNavaleProject.client;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class MenuPrincipale extends JFrame {
	private JPanel panel = new JPanel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					MenuPrincipale frame = new MenuPrincipale();
				    frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}

	
	public MenuPrincipale() throws IOException 
	{
	   
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(771, 600);
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
		
		
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.jpeg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        // scaled icon
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel lblNewLabel = new JLabel(scaledIcon);
        
        
        
        lblNewLabel.setPreferredSize(new Dimension(60,60));
        lblNewLabel.setBounds(0, 0, 119, 85);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel);
        
        ImageIcon icon2 = new ImageIcon("2.png");
        JLabel lblNewLabel_1 = new JLabel("foto profilo");
        lblNewLabel_1.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
        lblNewLabel_1.setBounds(638, 0, 119, 85);
        backgroundPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BATTAGLIA NAVALE ");
        lblNewLabel_2.setForeground(new Color(0, 128, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 42));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(129, 0, 499, 85);
        backgroundPanel.add(lblNewLabel_2);
	    

	    JButton multiplayerButton = new JButton("Multiplayer");
	    multiplayerButton.setPreferredSize(new Dimension(170, 50));
	    multiplayerButton.setBackground(new Color(0, 0, 0, 0));
		multiplayerButton.setOpaque(false);
	    multiplayerButton.setBounds(258, 124, 275, 70);
	    backgroundPanel.add(multiplayerButton);
	    
	    JButton singleplayer = new JButton("Singleplayer");
	   
        
	    singleplayer.setBackground(new Color(0, 0, 255));
	    singleplayer.setBackground(new Color(0, 0, 0, 0));
		singleplayer.setOpaque(false);
	    singleplayer.setPreferredSize(new Dimension(170, 50));
	    singleplayer.setBounds(258, 247, 275, 70);
	    backgroundPanel.add(singleplayer);
	    
	    JButton tutorialButton = new JButton("Tutorial");
	    tutorialButton.setBackground(new Color(0, 0, 0, 0));
		tutorialButton.setOpaque(false);
	    //tutorialButton.setBorderPainted(false);
	    tutorialButton.setBounds(258, 379, 275, 70);
	    tutorialButton.setPreferredSize(new Dimension(170, 50)); // Set the button size in pixels
	    backgroundPanel.add(tutorialButton);
	    
	    JPanel radioPanel= new JPanel();
	    radioPanel.setBounds(650,150,100,100);
	    radioPanel.setOpaque(false);
	    
	    ButtonGroup languageGroup = new ButtonGroup();
	    JRadioButton itaRadioButton = new JRadioButton("ITA");
	    itaRadioButton.setOpaque(false);
	    languageGroup.add(itaRadioButton);
	    radioPanel.add(itaRadioButton);
	    
        JRadioButton engRadioButton = new JRadioButton("ENG");
        languageGroup.add(engRadioButton);
        engRadioButton.setOpaque(false);
        radioPanel.add(engRadioButton);
        
        backgroundPanel.add(radioPanel);
        JLabel lblNewLabel_3 = new JLabel("Don't play alone, it's funnier with friends!");
        lblNewLabel_3.setBounds(258, 193, 275, 19);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Ready to play?");
        lblNewLabel_4.setBounds(258, 315, 275, 19);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Learn how to play with this tutorial");
        lblNewLabel_5.setBounds(258, 447, 275, 19);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_5);
	            
        
	}
}