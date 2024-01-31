package BattagliaNavaleProject.formGui;
import javax.imageio.ImageIO;
import javax.swing.*;

import BattagliaNavaleProject.Control.ConnectionControl;
import BattagliaNavaleProject.Control.MenuPrincipaleControl;
import BattagliaNavaleProject.Control.SchermataInizialeControl;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MenuPrincipaleView extends JFrame {
	private JLabel infoPlayerLabel_1;
	private static String userName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					MenuPrincipaleView frame = new MenuPrincipaleView(userName);
				    frame.setVisible(true);
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}

	
	public MenuPrincipaleView(String username) throws IOException, SQLException 
	{
		this.userName = username;
		
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
		
		final JLabel lblNewLabel_3 = new JLabel("Don't play alone, it's funnier with friends!");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_3.setBounds(515, 233, 425, 40);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_3);
        
        final JLabel lblNewLabel_4 = new JLabel("Ready to play?");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_4.setBounds(515, 400, 425, 40);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_4);
        
        final JLabel lblNewLabel_5 = new JLabel("Learn how to play with this tutorial");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_5.setBounds(515, 560, 425, 40);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_5);
		
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        final JLabel multiplayerTextLabel= new JLabel("Multiplayer");
        multiplayerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        multiplayerTextLabel.setBounds(590,160,282,70);
        backgroundPanel.add(multiplayerTextLabel);
       
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setPreferredSize(new Dimension(200,200));
        logoLabel.setBounds(0, 0, 200, 200);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(logoLabel);
        
        
       //ImageIcon icon2 = new ImageIcon("2.png");
        JLabel infoPlayerLabel = null;
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        JLabel lblNewLabel_2 = new JLabel("BATTAGLIA NAVALE ");
        lblNewLabel_2.setForeground(new Color(0, 128, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 60));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(400, 0, 650, 85);
        backgroundPanel.add(lblNewLabel_2);
       
        final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
	    Image buttonImage = hoverIcon.getImage();
        Image scaledButtonImage = buttonImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	    final JLabel imageLabel =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel3 =new JLabel(new ImageIcon(scaledButtonImage));
	    imageLabel.setBounds(545,154,90,90);
	    imageLabel2.setBounds(545,315,90,90);
	    imageLabel3.setBounds(545,480,90,90);
	    imageLabel.setVisible(false);
	    imageLabel2.setVisible(false);
	    imageLabel3.setVisible(false);
	    backgroundPanel.add(imageLabel);
	    backgroundPanel.add(imageLabel2);
	    backgroundPanel.add(imageLabel3);
	    

	    final JButton multiplayerButton = new JButton("");
	    multiplayerButton.setPreferredSize(new Dimension(370, 150));
	    multiplayerButton.setBackground(new Color(0, 0, 0, 0));
		multiplayerButton.setOpaque(false);
	    multiplayerButton.setBounds(550, 154, 350, 90);//275 70
	    backgroundPanel.add(multiplayerButton);
	    
	    multiplayerButton.addActionListener(new MenuPrincipaleControl(this));
		 
	    
	    multiplayerButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel.setVisible(true);
	    		multiplayerTextLabel.setForeground(new Color(0, 128, 255));
	    		multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		multiplayerButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel.setVisible(false);
	    	multiplayerTextLabel.setForeground(new Color(0, 0, 0));
	    	multiplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
	    	lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
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
        singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        singleplayerTextLabel.setBounds(600,320,282,70);
        backgroundPanel.add(singleplayerTextLabel);
        
	    final JButton singleplayer = new JButton("");
	    singleplayer.setBackground(new Color(0, 0, 255));
	    singleplayer.setBackground(new Color(0, 0, 0, 0));
		singleplayer.setOpaque(false);
	    singleplayer.setPreferredSize(new Dimension(170, 50));
	    singleplayer.setBounds(550, 317, 350, 90);
	    backgroundPanel.add(singleplayer);
	    
	    
	   // singleplayer.addActionListener(new MenuPrincipaleControl(this));
	    
	    singleplayer.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel2.setVisible(true);
	    		singleplayerTextLabel.setForeground(new Color(0, 128, 255));
	    		singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		singleplayer.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel2.setVisible(false);
	    	singleplayerTextLabel.setForeground(new Color(0, 0, 0));
	    	singleplayerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
	    	lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
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
        tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        tutorialTextLabel.setBounds(630,495,200,50);
        backgroundPanel.add(tutorialTextLabel);
	    
	    final JButton tutorialButton = new JButton("");
	    tutorialButton.setBackground(new Color(0, 0, 0, 0));
		tutorialButton.setOpaque(false);
	    //tutorialButton.setBorderPainted(false);
	    tutorialButton.setBounds(550, 480, 350, 90);
	    tutorialButton.setPreferredSize(new Dimension(170, 50)); // Set the button size in pixels
	    backgroundPanel.add(tutorialButton);
	    
	    tutorialButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel3.setVisible(true);
	    		tutorialTextLabel.setForeground(new Color(0, 128, 255));
	    		tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		tutorialButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel3.setVisible(false);
	    	tutorialTextLabel.setForeground(new Color(0, 0, 0));
	    	tutorialTextLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
	    	lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
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
	    
	    /*JPanel radioPanel= new JPanel();
	    radioPanel.setBounds(800,435,100,100);
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
        
        backgroundPanel.add(radioPanel);*/
        
        JPanel panel = new JPanel();
        panel.setBounds(629, 26, 109, 30);
        backgroundPanel.add(panel);
        LoginView gui= new LoginView();
        infoPlayerLabel_1 = 
        		new JLabel(gui.getUser());
        panel.add(infoPlayerLabel_1);
        infoPlayerLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	                    
	}


	public void open() throws IOException {
		// TODO Auto-generated method stub
	 try {
		SceltaGiocoView s= new SceltaGiocoView(userName);
		s.setVisible(true);
		dispose();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}
}