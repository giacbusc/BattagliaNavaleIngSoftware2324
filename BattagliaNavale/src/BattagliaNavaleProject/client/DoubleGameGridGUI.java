package BattagliaNavaleProject.client;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


public class DoubleGameGridGUI extends JFrame{
	private static final int GRID_DIMENSION = 10;
	private JLabel[][] grid1 = new JLabel[GRID_DIMENSION][GRID_DIMENSION];
	private JLabel[][] grid2 = new JLabel[GRID_DIMENSION][GRID_DIMENSION];
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					DoubleGameGridGUI frame = new DoubleGameGridGUI();
				    frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	public DoubleGameGridGUI() throws IOException 
	{
		setTitle("Battaglia Navale");
<<<<<<< Updated upstream
		setSize(596,408);
=======
		setSize(1090,581);
>>>>>>> Stashed changes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 2));
		
		final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoGriglia.jpg");
		Image image = sfondo.getImage();
        final Image scaledImage = image.getScaledInstance(1450, 816, Image.SCALE_SMOOTH);
        
		JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(backgroundPanel);
		createGrid(grid1, backgroundPanel);
		createGrid(grid2, backgroundPanel);
		
		setLocationRelativeTo(null);
		backgroundPanel.setVisible(true);
		setVisible(true);
	}
	
	private void createGrid(final JLabel[][] grid, JPanel backgroundPanel)
	{		        
		JPanel panel = new JPanel(new GridLayout(GRID_DIMENSION + 1, GRID_DIMENSION + 1));
		panel.setOpaque(false);
		
		for(int i = 0; i < GRID_DIMENSION + 1; i++)
		{
			for(int j = 0; j < GRID_DIMENSION + 1; j++)
			{
				if(i == 0 && j > 0)
				{
<<<<<<< Updated upstream
					panel.add( new JLabel(String.valueOf((char) ('A' + j - 1)), SwingConstants.CENTER));
				
=======
					 JLabel label = new JLabel(String.valueOf((char) ('A' + j - 1)), SwingConstants.CENTER);
					 label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));  // Bordo inferiore per le lettere
			         panel.add(label);
>>>>>>> Stashed changes
				} else if(j == 0 && i > 0)
				{
					 JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
			         label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));  // Bordo destro per i numeri
			         panel.add(label);
				} else if(i > 0 && j > 0)
				{
					final JLabel label = new JLabel();
					label.setPreferredSize(new Dimension(42, 42));
					label.setOpaque(false);
					label.setBorder(new LineBorder(Color.BLACK, 2));
					label.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							label.setOpaque(true);
							label.setBackground(Color.RED);
							Point coordinate = getCoordinate(label, grid);
							int riga = coordinate.y;
							int colonna = coordinate.x;
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
					grid[i-1][j-1]= label;
					panel.add(label);
				} else 
				{
					panel.add(new JLabel(""));
				}
			}
		}
		backgroundPanel.add(panel);
	}
	private Point getCoordinate(JLabel label, JLabel[][]grid)
	{
		for(int i = 0; i < GRID_DIMENSION; i++)
		{
			for(int j = 0; j < GRID_DIMENSION; j++)
			{
				if(grid[i][j] == label)
					return new Point(j, i);
			}
		}
		return null;
	}
	
}
