package BattagliaNavaleProject.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

public class FinePartitaView extends JFrame implements ActionListener {
	private static String userName;
	private static String msg;
	private JButton menuButton;
	private JButton exitButton;
	MenuPrincipaleView menu;
	
	//Classe che gestisce la visualizzazione del menu di fine partita
	public FinePartitaView(String userName, String messaggio) throws IOException, SQLException 
	{
		this.userName = userName;
		this.msg=messaggio;
		
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
		
		
		final JLabel userLabel = new JLabel(userName);
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(530, 30, 425, 40);
		backgroundPanel.add(userLabel);
		
		ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		JLabel logoLabel = new JLabel(scaledIcon);
		logoLabel.setPreferredSize(new Dimension(200,200));
		logoLabel.setBounds(0, 0, 200, 200);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(logoLabel);
		
		final JLabel lblNewLabel_3 = new JLabel("Vuoi giocare ancora?");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_3.setBounds(530, 404, 425, 40);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_3);
        
        final JLabel lblNewLabel_4 = new JLabel("Ne sei sicuro?");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_4.setBounds(530, 568, 425, 40);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblNewLabel_4);
        
        final JLabel pcSoloLabel= new JLabel("TORNA AL MENU");
        pcSoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        pcSoloLabel.setBounds(545,286,367,80);
        backgroundPanel.add(pcSoloLabel);
       
        JLabel lblNewLabel_2 = new JLabel(msg);
        lblNewLabel_2.setForeground(new Color(0, 128, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 70));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(365, 115, 750, 85);
        backgroundPanel.add(lblNewLabel_2);
       
        final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
	    Image buttonImage = hoverIcon.getImage();
        Image scaledButtonImage = buttonImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	    final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel3 =new JLabel(new ImageIcon(scaledButtonImage));
	    imageLabel2.setBounds(496,250,71,69);
	    imageLabel3.setBounds(530,469,79,69);
	    imageLabel2.setVisible(false);
	    imageLabel3.setVisible(false);
	    backgroundPanel.add(imageLabel2);
	    backgroundPanel.add(imageLabel3);
	    

	    menuButton = new JButton();
	    menuButton.setName("menu");
	    
	    menuButton.setPreferredSize(new Dimension(370, 150));
	    menuButton.setBackground(new Color(0, 0, 0, 0));
		menuButton.setOpaque(false);
	    menuButton.setBounds(496, 250, 468, 144);//275 70
	    backgroundPanel.add(menuButton);
	    
	    menuButton.addActionListener(this);
		 
	    
	    menuButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel2.setVisible(true);
	    		pcSoloLabel.setForeground(new Color(0, 128, 255));
	    		pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		menuButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel2.setVisible(false);
	    	pcSoloLabel.setForeground(new Color(0, 0, 0));
	    	pcSoloLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
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
	    
	    final JLabel pcMutliLabel= new JLabel("EXIT");
        pcMutliLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        pcMutliLabel.setBounds(588,482,306,76);
        backgroundPanel.add(pcMutliLabel);
        
	    exitButton = new JButton();
	    exitButton.setName("esci");
	    exitButton.setBackground(new Color(0, 0, 255));
	    exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setOpaque(false);
	    exitButton.setPreferredSize(new Dimension(170, 50));
	    exitButton.setBounds(533, 469, 407, 106);
	    backgroundPanel.add(exitButton);
	    
	    exitButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel3.setVisible(true);
	    		pcMutliLabel.setForeground(new Color(0, 128, 255));
	    		pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		exitButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel3.setVisible(false);
	    	pcMutliLabel.setForeground(new Color(0, 0, 0));
	    	pcMutliLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
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
	//Metodo utilizzato per aggiungere un action listener al pulsante menu
	public void aggiungiListenerMenu(ActionListener act)
	{
		menuButton.addActionListener(act);
	}
	//Metodo utilizzato per aggiungere un action listener al pulsante exit
	public void aggiungiListenerExit(ActionListener act)
	{
		exitButton.addActionListener(act);
	}

	                    
}

	





