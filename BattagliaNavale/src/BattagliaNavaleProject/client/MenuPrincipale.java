package BattagliaNavaleProject.client;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MenuPrincipale extends JFrame {
	
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
		
		final JLabel lblNewLabel_3 = new JLabel("Don't play alone, it's funnier with friends!");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_3.setBounds(258, 193, 275, 19);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_3);
        
        final JLabel lblNewLabel_4 = new JLabel("Ready to play?");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_4.setBounds(258, 315, 275, 19);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_4);
        
        final JLabel lblNewLabel_5 = new JLabel("Learn how to play with this tutorial");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_5.setBounds(258, 447, 275, 19);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_5);
		
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.jpeg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        // scaled icon
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        final JLabel multiplayerTextLabel= new JLabel("Multiplayer");
        multiplayerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        multiplayerTextLabel.setBounds(324,142,142,30);
        backgroundPanel.add(multiplayerTextLabel);
       
        JLabel logoLabel = new JLabel(scaledIcon);
        
        
        
        logoLabel.setPreferredSize(new Dimension(60,60));
        logoLabel.setBounds(0, 0, 119, 85);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(logoLabel);
        
        
        ImageIcon icon2 = new ImageIcon("2.png");
        JLabel infoPlayerLabel = new JLabel("");
        infoPlayerLabel.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
        infoPlayerLabel.setBounds(638, 0, 119, 85);
        backgroundPanel.add(infoPlayerLabel);
        
        JLabel lblNewLabel_2 = new JLabel("BATTAGLIA NAVALE ");
        lblNewLabel_2.setForeground(new Color(0, 128, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 42));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(129, 0, 499, 85);
        backgroundPanel.add(lblNewLabel_2);
       
        final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
	    Image buttonImage = hoverIcon.getImage();
        Image scaledButtonImage = buttonImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    final JLabel imageLabel =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel3 =new JLabel(new ImageIcon(scaledButtonImage));
	    imageLabel.setBounds(258,124,72,70);
	    imageLabel2.setBounds(258,247,72,70);
	    imageLabel3.setBounds(258,379,72,70);
	    imageLabel.setVisible(false);
	    imageLabel2.setVisible(false);
	    imageLabel3.setVisible(false);
	    backgroundPanel.add(imageLabel);
	    backgroundPanel.add(imageLabel2);
	    backgroundPanel.add(imageLabel3);
	    

	    final JButton multiplayerButton = new JButton("");
	    multiplayerButton.setPreferredSize(new Dimension(170, 50));
	    multiplayerButton.setBackground(new Color(0, 0, 0, 0));
		multiplayerButton.setOpaque(false);
	    multiplayerButton.setBounds(258, 124, 275, 70);
	    backgroundPanel.add(multiplayerButton);
	    
	    
	    multiplayerButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel.setVisible(true);
	    		multiplayerTextLabel.setForeground(new Color(0, 128, 255));
	    		multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
	    		multiplayerButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel.setVisible(false);
	    	multiplayerTextLabel.setForeground(new Color(0, 0, 0));
	    	multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
            multiplayerButton.repaint();
             
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
	    
	    final JLabel singleplayerTextLabel= new JLabel("Singleplayer");
        singleplayerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        singleplayerTextLabel.setBounds(324,266,155,30);
        backgroundPanel.add(singleplayerTextLabel);
        
	    final JButton singleplayer = new JButton("");
	    singleplayer.setBackground(new Color(0, 0, 255));
	    singleplayer.setBackground(new Color(0, 0, 0, 0));
		singleplayer.setOpaque(false);
	    singleplayer.setPreferredSize(new Dimension(170, 50));
	    singleplayer.setBounds(258, 247, 275, 70);
	    backgroundPanel.add(singleplayer);
	    
	    
	    singleplayer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    
	    singleplayer.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel2.setVisible(true);
	    		singleplayerTextLabel.setForeground(new Color(0, 128, 255));
	    		singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
	    		singleplayer.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel2.setVisible(false);
	    	singleplayerTextLabel.setForeground(new Color(0, 0, 0));
	    	singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
            singleplayer.repaint();
             
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
        tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        tutorialTextLabel.setBounds(324,395,155,30);
        backgroundPanel.add(tutorialTextLabel);
	    
	    final JButton tutorialButton = new JButton("");
	    tutorialButton.setBackground(new Color(0, 0, 0, 0));
		tutorialButton.setOpaque(false);
	    //tutorialButton.setBorderPainted(false);
	    tutorialButton.setBounds(258, 379, 275, 70);
	    tutorialButton.setPreferredSize(new Dimension(170, 50)); // Set the button size in pixels
	    backgroundPanel.add(tutorialButton);
	    
	    tutorialButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel3.setVisible(true);
	    		tutorialTextLabel.setForeground(new Color(0, 128, 255));
	    		tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
	    		tutorialButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel3.setVisible(false);
	    	tutorialTextLabel.setForeground(new Color(0, 0, 0));
	    	tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
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
	    
	    JPanel radioPanel= new JPanel();
	    radioPanel.setBounds(638,435,100,100);
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
	                    
	}
}