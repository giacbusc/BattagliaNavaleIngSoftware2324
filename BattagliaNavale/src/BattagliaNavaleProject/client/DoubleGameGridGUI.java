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
	private final JFrame frame;
	private JPanel yourBoardPanel;
	private JPanel opponentBoardPanel;
	private JPanel centralTopPanel;
	private Square[][] yourBoard;
	private Square[][] opponentBoard;
	private JPanel shipsPanel;
	private JPanel gridPanel;
	private GridBagConstraints c;
	//private int dim; -> se vogliamo far sì che il giocatore all'inizio scelga la dimensione
	private int selectedShip;
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
		setSize(1400,788);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centralPanel = new JPanel(new BorderLayout());
		c = new GridBagConstraints();
		gridPanel =  new JPanel();
		centralTopPanel = new JPanel();
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

		centralTopPanel.setBackground(Color.BLUE);
		centralTopPanel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(centralTopPanel, BorderLayout.NORTH);
		
		createGrid();
		
		getContentPane().add(gridPanel, BorderLayout.CENTER);
		
		
		frame.pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void boatList()
	{
		selectedShip = 0;
		shipsPanel = new JPanel();
		shipsPanel.setLayout(new GridLayout(7,6,0,3));
		JLabel selectorLabel;
		int boatLength;
		shipsPanel.setBackground(Color.blue);
		shipsPanel.setPreferredSize(new Dimension(100,100));
		//centralPanel.add(shipsPanel);
		c.gridx = 0 ;
		c.gridy = 1;
		c.weightx = 2;
		gridPanel.add(shipsPanel,c);
		frame.pack();
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

					else if(j == GRID_DIMENSION -1)
					{
						yourBoard[i][j].setBorder(topLeftRightBorder);
						opponentBoard[i][j].setBorder(topLeftRightBorder);
					}

					else if(j == GRID_DIMENSION -1)
					{
						yourBoard[i][j].setBorder(topLeftRightBorder);
						opponentBoard[i][j].setBorder(topLeftRightBorder);
					}

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
		
		gridPanel.setLayout(new GridBagLayout());
		
		yourBoardPanel.setPreferredSize(new Dimension(600,600));
		c.ipadx = 35;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill= GridBagConstraints.BOTH;
		gridPanel.add(yourBoardPanel, c);
		
		opponentBoardPanel.setPreferredSize(new Dimension(600,600));
		c.ipadx = 35;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill= GridBagConstraints.BOTH;
		gridPanel.add(opponentBoardPanel,c);
		//getContentPane().add(yourBoardPanel, BorderLayout.WEST);
		boatList();
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
