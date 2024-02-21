package BattagliaNavaleProject.formGui;
import javax.swing.*;

import BattagliaNavaleProject.Control.ConnectionControl;
import BattagliaNavaleProject.Control.FinePartitaControl;
import BattagliaNavaleProject.Control.MenuPrincipaleControl;
import BattagliaNavaleProject.client.Observer;

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
	static DoubleGameGridView DGGV;
	 MenuPrincipaleView menu;
	

	
	public FinePartitaView(String userName, String messaggio, DoubleGameGridView dGGV) throws IOException, SQLException 
	{	
		DGGV=dGGV;
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
        
        ImageIcon icon = new ImageIcon("../docs/resources/Logo.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        final JLabel menuLabel= new JLabel("TORNA AL MENU");
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        menuLabel.setBounds(545,286,367,80);
        backgroundPanel.add(menuLabel);
        
        
       //ImageIcon icon2 = new ImageIcon("2.png");
        JLabel infoPlayerLabel = null;
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
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
	    

	    menuButton = new JButton("");
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
	    		menuLabel.setForeground(new Color(0, 128, 255));
	    		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		menuButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel2.setVisible(false);
	    	menuLabel.setForeground(new Color(0, 0, 0));
	    	menuLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
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
	    
	    final JLabel esciLabel= new JLabel("EXIT");
        esciLabel.setHorizontalAlignment(SwingConstants.CENTER);
        esciLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        esciLabel.setBounds(588,482,306,76);
        backgroundPanel.add(esciLabel);
        
	    exitButton = new JButton(".");
	    exitButton.setName("esci");
	    exitButton.setBackground(new Color(0, 0, 255));
	    exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setOpaque(false);
	    exitButton.setPreferredSize(new Dimension(170, 50));
	    exitButton.setBounds(533, 469, 407, 106);
	    backgroundPanel.add(exitButton);
	    
	    
	   exitButton.addActionListener(this);
	    
	    exitButton.addMouseListener(new MouseListener()
	    {
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		imageLabel3.setVisible(true);
	    		esciLabel.setForeground(new Color(0, 128, 255));
	    		esciLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
	    		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
	    		exitButton.repaint();
	    	}
	    
	    
	    public void mouseExited(MouseEvent e)
	    {
	    	imageLabel3.setVisible(false);
	    	esciLabel.setForeground(new Color(0, 0, 0));
	    	esciLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
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
		FinePartitaControl fpc= new FinePartitaControl(userName,DGGV.getObserver());
		fpc.gestisciClick(e);
	}
	    
	

	
	                    
	}

	
	
	

