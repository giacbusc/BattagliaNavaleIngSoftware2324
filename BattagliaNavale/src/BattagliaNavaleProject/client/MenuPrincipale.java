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
	    
	    
        panel.setBounds(10, 21, 757, 85);
        setContentPane(panel);
        
        
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.jpeg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        // scaled icon
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel lblNewLabel = new JLabel(scaledIcon);
        
        lblNewLabel.setPreferredSize(new Dimension(60,60));
        lblNewLabel.setBounds(0, 0, 119, 85);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);
        
        ImageIcon icon2 = new ImageIcon("2.png");
        JLabel lblNewLabel_1 = new JLabel("foto profilo");
        lblNewLabel_1.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
        lblNewLabel_1.setBounds(638, 0, 119, 85);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BATTAGLIA NAVALE ");
        lblNewLabel_2.setForeground(new Color(0, 0, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 42));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(129, 0, 499, 85);
        panel.add(lblNewLabel_2);
        
	    JPanel buttonPanel = new JPanel();
	    

	    JButton multiplayerbutton = new JButton("Multiplayer");
	    multiplayerbutton.setBackground(new Color(0, 0, 255));
	   // multiplayerbutton.setForeground(new Color(0, 0, 255)); senza questa linea leggiamo la scritta sul bottone
	    multiplayerbutton.setPreferredSize(new Dimension(170, 50));
	    multiplayerbutton.setBounds(258, 139, 275, 70);
	    buttonPanel.add(multiplayerbutton);
	    
	    JButton singleplayer = new JButton("Singleplayer");
	    singleplayer.setBackground(new Color(0, 0, 255));
	    //singleplayer.setForeground(new Color(0, 0, 255));
	    singleplayer.setPreferredSize(new Dimension(170, 50));
	    singleplayer.setBounds(258, 237, 275, 70);
	    buttonPanel.add(singleplayer);
	    
	    JButton tutorialbutton = new JButton("Tutorial");
	    //tutorialbutton.setForeground(new Color(0, 0, 255));
	    tutorialbutton.setBackground(new Color(0, 0, 255));
	    tutorialbutton.setBounds(258, 340, 275, 70);
	    tutorialbutton.setPreferredSize(new Dimension(170, 50)); // Set the button size in pixels
	    buttonPanel.add(tutorialbutton);
	    
	    panel.add(buttonPanel, BorderLayout.CENTER);
	    
	    
	    ButtonGroup languageGroup = new ButtonGroup();
	    JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    radioButtonPanel.setBounds(0, 461, 757, 31);
	    buttonPanel.add(radioButtonPanel);
	    JRadioButton itaRadioButton = new JRadioButton("ITA");
	    languageGroup.add(itaRadioButton);
	    radioButtonPanel.add(itaRadioButton);
	    
        JRadioButton engRadioButton = new JRadioButton("ENG");
        languageGroup.add(engRadioButton);
        radioButtonPanel.add(engRadioButton);
	            
        
	    
	}
}