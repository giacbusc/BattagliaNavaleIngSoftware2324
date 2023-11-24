package BattagliaNavaleProject.client;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;


public class DoubleGameGridGUI extends JFrame implements MouseListener, MouseMotionListener{
	private static final int GRID_DIMENSION = 10;
	public final JFrame frame;
	private JPanel yourBoardPanel;
	private JPanel opponentBoardPanel;
	private Square[][] yourBoard;
	private Square[][] opponentBoard;
	//private int dim; -> se vogliamo far sì che il giocatore all'inizio scelga la dimensione
	private final Border topLeftBorder = BorderFactory.createMatteBorder(1, 1, 0, 0, Color.black);
	private final Border topLeftBottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 0, Color.black);
	private final Border topLeftRightBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black);
	private final Border topLeftBottomRightBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
	
	
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
		this.frame = new JFrame("Battaglia Navale");
		setSize(1024,490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 2));
		
		JPanel backgroundPanel = new JPanel();
		/*final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoGriglia.jpg");
		Image image = sfondo.getImage();
        final Image scaledImage = image.getScaledInstance(1450, 816, Image.SCALE_SMOOTH);
        
		JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);
            }
        };*/
        //getContentPane().add(backgroundPanel);
		createGrid();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createGrid()
	{	
		yourBoardPanel = new JPanel();
		opponentBoardPanel = new JPanel();
		yourBoard = new Square[GRID_DIMENSION][GRID_DIMENSION];
		opponentBoard = new Square[GRID_DIMENSION][GRID_DIMENSION];
		yourBoardPanel.setLayout(new GridLayout(GRID_DIMENSION+2, GRID_DIMENSION+2, 0, 0));
		opponentBoardPanel.setLayout(new GridLayout(GRID_DIMENSION+2, GRID_DIMENSION+2, 0, 0));
		
		for(int i = -1; i < GRID_DIMENSION + 1; i++)
		{
			for(int j = -1; j < GRID_DIMENSION + 1; j++)
			{
				
				if((i == -1 && j == -1) || (i == -1 && j == GRID_DIMENSION) || (i == GRID_DIMENSION && j == -1) || (i == GRID_DIMENSION && j == GRID_DIMENSION))
				{
					 yourBoardPanel.add(new JLabel(" "));
	                 opponentBoardPanel.add(new JLabel(" "));
				}
				
				else if(i==-1)
				{
					yourBoardPanel.add(new JLabel(""+(char)(j+'A'), JLabel.CENTER));
                    opponentBoardPanel.add(new JLabel(""+(char)(j+'A'), JLabel.CENTER));
				}
				else if(j==-1)
				{
					  yourBoardPanel.add(new JLabel((i+1)+"", JLabel.CENTER));
	                  opponentBoardPanel.add(new JLabel((i+1)+"", JLabel.CENTER));
				}
				else if (i == GRID_DIMENSION)
				{
					yourBoardPanel.add(new JLabel(" "));
                    opponentBoardPanel.add(new JLabel(" "));
				}
				else if(j == GRID_DIMENSION)
				{
					yourBoardPanel.add(new JLabel(" "));
                    opponentBoardPanel.add(new JLabel(" "));
				}
				else
				{
					yourBoard[i][j]= new Square(i,j);
					yourBoard[i][j].addMouseListener(this);
					yourBoard[i][j].addMouseMotionListener(this);
					yourBoardPanel.add(yourBoard[i][j]);
					opponentBoard[i][j]= new Square(i,j);
					opponentBoard[i][j].addMouseListener(this);
					opponentBoard[i][j].addMouseMotionListener(this);
					opponentBoardPanel.add(opponentBoard[i][j]);
					
					if(i == GRID_DIMENSION-1 && j == GRID_DIMENSION -1 )
					{
						yourBoard[i][j].setBorder(topLeftBottomRightBorder);
						opponentBoard[i][j].setBorder(topLeftBottomRightBorder);
					}
					/*else if(j == GRID_DIMENSION -1)
					{
						yourBoard[i][j].setBorder(topLeftRightBorder);
						opponentBoard[i][j].setBorder(topLeftRightBorder);
					}*/
					else if(i == GRID_DIMENSION-1)
					{
						yourBoard[i][j].setBorder(topLeftBottomBorder);
						opponentBoard[i][j].setBorder(topLeftBottomBorder);
					}
					else
					{
						yourBoard[i][j].setBorder(topLeftBorder);
						opponentBoard[i][j].setBorder(topLeftBorder);
					}
				}
			}
		}
		
		yourBoardPanel.setPreferredSize(new Dimension(432,432));
		opponentBoardPanel.setPreferredSize(new Dimension(432,432));
		getContentPane().add(yourBoardPanel, BorderLayout.WEST);
		getContentPane().add(opponentBoardPanel, BorderLayout.EAST);
		frame.pack();
		
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
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
