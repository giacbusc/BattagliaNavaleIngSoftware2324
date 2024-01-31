package BattagliaNavaleProject.formGui;
import javax.imageio.ImageIO;
import javax.swing.*;

import BattagliaNavaleProject.Control.ConnectionControl;
import BattagliaNavaleProject.Control.MenuPrincipaleControl;
import BattagliaNavaleProject.Control.SceltaGiocoControl;
import BattagliaNavaleProject.Control.SchermataInizialeControl;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SceltaGiocoView extends JFrame {
	private JLabel infoPlayerLabel_1;
	private String userName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					SceltaGiocoView s= new SceltaGiocoView("ciao");
				    s.setVisible(true);
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}

	
	public SceltaGiocoView(String username) throws IOException, SQLException 
	{
		this.userName = username;
		
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
		
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        final JLabel pcsolo= new JLabel("Voglio giocare su un solo pc");
       pcsolo.setHorizontalAlignment(SwingConstants.CENTER);
        pcsolo.setFont(new Font("Tahoma", Font.BOLD, 20));
        pcsolo.setBounds(237,174,313,78);
        backgroundPanel.add(pcsolo);
       
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setPreferredSize(new Dimension(80,80));
        logoLabel.setBounds(0, 0, 119, 85);
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
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 42));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(129, 29, 499, 85);
        backgroundPanel.add(lblNewLabel_2);
       
        final ImageIcon hoverIcon = new ImageIcon("../docs/resources/EffettoBottone.png");
	    Image buttonImage = hoverIcon.getImage();
        Image scaledButtonImage = buttonImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    final JLabel imageLabel =new JLabel(new ImageIcon(scaledButtonImage));
	    final JLabel imageLabel2 =new JLabel(new ImageIcon(scaledButtonImage));
	    imageLabel.setBounds(224,146,72,47);
	    imageLabel2.setBounds(224,348,72,47);
	    imageLabel.setVisible(false);
	    imageLabel2.setVisible(false);
	    backgroundPanel.add(imageLabel);
	    backgroundPanel.add(imageLabel2);
	    

	    final JButton pcsolobutton = new JButton("");
	    pcsolobutton.setPreferredSize(new Dimension(170, 50));
	    pcsolobutton.setBackground(new Color(0, 0, 0, 0));
	    pcsolobutton.setOpaque(false);
	    pcsolobutton.setBounds(216, 135, 356, 158);
	    backgroundPanel.add(pcsolobutton);
	    
	   // pcsolobutton.addActionListener(new SceltaGiocoControl(this));
		 
	    
	    pcsolobutton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel.setVisible(true);
	    		pcsolo.setForeground(new Color(0, 128, 255));
	    		pcsolo.setFont(new Font("Tahoma", Font.BOLD, 22));
	    		
	    		pcsolobutton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel.setVisible(false);
	    	pcsolo.setForeground(new Color(0, 0, 0));
	    	pcsolo.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	
            pcsolobutton.repaint();
             
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
	    
	    final JLabel pcmultipli= new JLabel("Voglio giocare con pc diversi");
        pcmultipli.setHorizontalAlignment(SwingConstants.CENTER);
       pcmultipli.setFont(new Font("Tahoma", Font.BOLD, 20));
        pcmultipli.setBounds(237,379,313,60);
        backgroundPanel.add(pcmultipli);
        
	    final JButton pcbutton = new JButton("");
	    pcbutton.setBackground(new Color(0, 0, 255));
	    pcbutton.setBackground(new Color(0, 0, 0, 0));
	    pcbutton.setOpaque(false);
	    pcbutton.setPreferredSize(new Dimension(170, 50));
	    pcbutton.setBounds(216, 333, 356, 158);
	    backgroundPanel.add(pcbutton);
	    
	    
	   // singleplayer.addActionListener(new MenuPrincipaleControl(this));
	    
	    pcbutton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel2.setVisible(true);
	    		pcmultipli.setFont(new Font("Tahoma", Font.BOLD, 22));
	    		
	    		pcbutton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel2.setVisible(false);
	    	pcmultipli.setForeground(new Color(0, 0, 0));
	    	pcmultipli.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	
	    	pcbutton.repaint();
             
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
	   
}