package BattagliaNavaleProject.client;

import javax.swing.*;
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
		setSize(1450,816);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));
		
		createGrid(grid1);
		createGrid(grid2);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createGrid(final JLabel[][] grid)
	{	
		 final ImageIcon sfondo = new ImageIcon("../docs/resources/Griglia.png");
			
			JPanel panel = new JPanel(new GridLayout(GRID_DIMENSION + 1, GRID_DIMENSION + 1)) {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        
		for(int i = 0; i < GRID_DIMENSION + 1; i++)
		{
			for(int j = 0; j < GRID_DIMENSION + 1; j++)
			{
				if(i == 0 && j > 0)
				{
					panel.add( new JLabel(String.valueOf((char) ('A' + j - 1)), SwingConstants.CENTER));
				} else if(j == 0 && i > 0)
				{
					panel.add( new JLabel(String.valueOf(i), SwingConstants.CENTER));
				} else if(i > 0 && j > 0)
				{
					final JLabel label = new JLabel();
					label.setPreferredSize(new Dimension(40, 40));
					label.setOpaque(false);
					label.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							label.setOpaque(true);
							label.setBackground(Color.BLUE);
							Point coordinate = getCoordinate(label, grid);
							int riga = coordinate.y;
							int  colonna = coordinate.x;
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
		getContentPane().add(panel);
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
