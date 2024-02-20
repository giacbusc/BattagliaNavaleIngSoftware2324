package BattagliaNavaleProject.formGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import BattagliaNavaleProject.Control.ConnectionControl;
import BattagliaNavaleProject.Control.LoginControl;
import BattagliaNavaleProject.Control.RegistrationControl;
import BattagliaNavaleProject.Control.SchermataInizialeControl;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

//pagina per loggarsi/registrarsi
public class SchermataInizialeView extends JFrame {
	
	JButton loginButton = new JButton("");
	SchermataInizialeView frame;
	
	
	JButton registerButton = new JButton("");

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchermataInizialeView frame = new SchermataInizialeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SchermataInizialeView() 
	{		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			 Dimension dimensioniSchermo = Toolkit.getDefaultToolkit().getScreenSize();

		        // Calcola le coordinate x e y per centrare la finestra
		        int x = (dimensioniSchermo.width - getWidth()) / 2;
		        int y = (dimensioniSchermo.height - getHeight()) / 2;
		        
		        setBounds(x-(450/2),y-(150),450,300);
			
			final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");
			
			JPanel backgroundPanel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        backgroundPanel.setAlignmentX(BOTTOM_ALIGNMENT);
	        backgroundPanel.setAlignmentY(BOTTOM_ALIGNMENT);
	        
	        getContentPane().add(backgroundPanel);
	        backgroundPanel.setLayout(null);
		    
		    final JLabel infoLabel = new JLabel("New player? Start here!");
			infoLabel.setBounds(128, 144, 166, 16);
			infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			infoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			backgroundPanel.add(infoLabel);
			
			final JLabel titleLabel = new JLabel("Welcome in BattleShip game");
			titleLabel.setBounds(55, 10, 354, 40);
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
			titleLabel.setForeground(new Color(0, 128, 255));
			backgroundPanel.add(titleLabel);
		    
			loginButton.setBounds(128, 60, 166, 62);
			loginButton.setName("Login");
			
			loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			
			
			final JLabel loginTextLabel= new JLabel("Login");
			loginTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
			loginTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			loginTextLabel.setBounds(138,77,142,30);
			backgroundPanel.add(loginTextLabel);
			loginButton.setBackground(new Color(0, 0, 0, 0));
			loginButton.setOpaque(false);
			backgroundPanel.add(loginButton);
	        
			setVisible(true);
			
			loginButton.addMouseListener(new MouseListener()
		    {
		    	public void mouseEntered(MouseEvent e)
		    	{
		    		loginTextLabel.setForeground(new Color(0, 128, 255));
		    		loginTextLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		    		registerButton.repaint();
		    	}
		    
		    
		    public void mouseExited(MouseEvent e)
		    {
		    	loginTextLabel.setForeground(new Color(0, 0, 0));
		    	loginTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	            registerButton.repaint();
	             
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
		

			final JLabel registerTextLabel= new JLabel("Register");
			registerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
			registerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			registerTextLabel.setBounds(138,177,142,30);
	        backgroundPanel.add(registerTextLabel);
	        
	        
			registerButton.setBounds(128, 163, 166, 62);
			registerButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			registerButton.setBackground(new Color(0, 0, 0, 0));
			registerButton.setOpaque(false);
			registerButton.setName("Register");
			backgroundPanel.add(registerButton);
			
			 registerButton.addMouseListener(new MouseListener()
			    {
			    	public void mouseEntered(MouseEvent e)
			    	{
			    		registerTextLabel.setForeground(new Color(0, 128, 255));
			    		registerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
			    		infoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			    		registerButton.repaint();
			    	}
			    
			    
			    public void mouseExited(MouseEvent e)
			    {
			    	registerTextLabel.setForeground(new Color(0, 0, 0));
			    	registerTextLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			    	infoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		            registerButton.repaint();
		             
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

		public void addActionReg(ActionListener act)
		{
			registerButton.addActionListener(act);
		}
		
		public void addActionLog(ActionListener act)
		{
			loginButton.addActionListener(act);
		}

	public void close() {
		// TODO Auto-generated method stub
   	  dispose();
	}
	

}

	
	
	