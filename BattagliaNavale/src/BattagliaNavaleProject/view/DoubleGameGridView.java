package BattagliaNavaleProject.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.Square;
import BattagliaNavaleProject.control.DoubleGameGridControl;
import BattagliaNavaleProject.control.TurniControl;

public class DoubleGameGridView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int GRID_DIMENSION = 10;
	private static final int Square_SIZE = 60;
	private JFrame frame;
	public JPanel yourBoardPanel;
	public JPanel opponentBoardPanel;
	private JPanel centralTopPanel;
	public Square[][] yourBoard;
	public Square[][] opponentBoard;
	private JPanel shipsPanel;
	private JPanel gridPanel;
	public JLabel turno;
	public JPanel waitPanel;
	private GridBagConstraints c;
	private GridBagConstraints c1;
	private GridBagConstraints c2;
	private GridBagConstraints c3;
	private ArrayList<Integer> dim;
	private int selectedShip;
	private final Border topLeftBorder = BorderFactory.createMatteBorder(1, 1, 0, 0, Color.black);
	private final Border topLeftBottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 0, Color.black);
	private final Border topLeftRightBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black);
	private final Border topLeftBottomRightBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
	private JPanel panel[];
	private String username;	
	public JPanel turnoPanel;
	

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{
					DoubleGameGridView frame = new DoubleGameGridView();
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});




	}*/

	/*public DoubleGameGridGUI()
	{
		socket = this.socket;
		this.frame = new JFrame("Battaglia Navale");
		setSize(1400,788);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centralPanel = new JPanel(new BorderLayout());
		c = new GridBagConstraints();
		c1 = new GridBagConstraints();
		c2 = new GridBagConstraints();
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

	/*centralTopPanel.setBackground(Color.BLUE);
		centralTopPanel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(centralTopPanel, BorderLayout.NORTH);

		createGrid();

		getContentPane().add(gridPanel, BorderLayout.CENTER);


		frame.pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}*/

	public DoubleGameGridView(String username) throws IOException 
	{
		this.username = username;
		this.frame = new JFrame("Battaglia Navale");
		setSize(1400,788);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centralPanel = new JPanel(new BorderLayout());
		c = new GridBagConstraints();
		c1 = new GridBagConstraints();
		c2 = new GridBagConstraints();
		c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.BOTH;
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

		JLabel usernameLabel = new JLabel(username);
		usernameLabel.setForeground(Color.white);
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		centralTopPanel.setBackground(Color.decode("#5C99D6"));
		centralTopPanel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(centralTopPanel, BorderLayout.NORTH);
		

		centralTopPanel.add(usernameLabel);
	
		createGrid();

		getContentPane().add(gridPanel, BorderLayout.CENTER);


		frame.pack();
		setLocationRelativeTo(null);
		setVisible(true);
		waitPanel.setVisible(false);
		turnoPanel.setVisible(false);
	}

	private void boatList(ArrayList<Integer> dim)
	{	
		panel= new JPanel[GRID_DIMENSION];
		selectedShip = 0;
		shipsPanel = new JPanel();
		shipsPanel.setLayout(new FlowLayout());
		JLabel selectorLabel;
		int boatLength;
		shipsPanel.setBackground(Color.decode("#5C99D6"));
		shipsPanel.setPreferredSize(new Dimension(100,100));
		//centralPanel.add(shipsPanel);
		c2.gridx = 0 ;
		c2.gridy = 1;
		c2.weightx = 2;
		c2.fill = GridBagConstraints.HORIZONTAL;
		gridPanel.add(shipsPanel,c2);
		for(int i=0;i<dim.size();i++) 
		{
			panel[i]= new JPanel();
			panel[i].setFocusable(true);
			//panel[i].addMouseListener(this);
			panel[i].setName(""+i);
			boatLength = dim.get(i);

			if(boatLength == 4)
			{
				panel[i].setPreferredSize(new Dimension(220,40));
				panel[i].setBackground(Color.decode("#D147D1")); // 4 quadretti = rosso
				shipsPanel.add(panel[i]);

			}else if(boatLength == 3)
			{
				panel[i].setPreferredSize(new Dimension(165,40));
				panel[i].setBackground(Color.decode("#FFC20A"));
				shipsPanel.add(panel[i]);
			}
			else if(boatLength == 2)
			{
				panel[i].setPreferredSize(new Dimension(110,40));
				panel[i].setBackground(Color.decode("#9AFF6B"));
				shipsPanel.add(panel[i]);
			}
			else if(boatLength == 1)
			{
				panel[i].setPreferredSize(new Dimension(55,40));
				panel[i].setBackground(Color.decode("#00E6AC"));
				shipsPanel.add(panel[i]);
			}

		}

		frame.pack();
	}

	private ArrayList<Integer> getDimNavi()
	{
		dim = new ArrayList<Integer>();
		for(int i = 0; i < GRID_DIMENSION; i++)
		{
			if(i == 0)
			{
				dim.add(4);
			}else if(i == 1 || i == 2)
			{
				dim.add(3);
			}
			else if( i > 2 && i < 6)
			{
				dim.add(2);
			}
			else
			{
				dim.add(1);
			}
		}
		return dim;
	}


	public void createGrid()
	{	
		yourBoardPanel = new JPanel();
		opponentBoardPanel = new JPanel();
		yourBoard = new Square[GRID_DIMENSION][GRID_DIMENSION];
		opponentBoard = new Square[GRID_DIMENSION][GRID_DIMENSION];
		yourBoardPanel.setLayout(new GridLayout(GRID_DIMENSION+2, GRID_DIMENSION+2, 0, 0));
		opponentBoardPanel.setLayout(new GridLayout(GRID_DIMENSION+2, GRID_DIMENSION+2, 0, 0));

		grigliaView();
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
		c1.ipadx = 35;
		c1.gridx = 1;
		c1.gridy = 0;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		c1.fill= GridBagConstraints.BOTH;
		gridPanel.add(opponentBoardPanel,c1);
		//getContentPane().add(yourBoardPanel, BorderLayout.WEST);
		dim=getDimNavi();
		boatList(dim);
		waitPanelCreation();
		turnoPanelCreation();
		frame.pack();

	}
	public void grigliaView()
	{

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
					yourBoard[i][j]= new Square(i,j,0);
					//yourBoard[i][j].addMouseListener(this);//
					yourBoard[i][j].setName("yourBoard");//
					yourBoardPanel.add(yourBoard[i][j]);
					opponentBoard[i][j]= new Square(i,j,0);

					opponentBoard[i][j].setName( "opponentBoard" ); //
					opponentBoardPanel.add(opponentBoard[i][j]);

					setBordi(i, j);
				}
			}
		}
	}
	public void setBordi(int i, int j)
	{
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
	public void waitPanelCreation() {
		// TODO Auto-generated method stub
		waitPanel = new JPanel();
		waitPanel.setBackground(Color.white);
		c3.gridx = 1 ;
		c3.gridy = 1;
		c3.weightx = 2;
		gridPanel.add(waitPanel,c3);
		GridBagLayout gbl_waitPanel = new GridBagLayout();
		gbl_waitPanel.columnWidths = new int[]{0};
		gbl_waitPanel.rowHeights = new int[]{0};
		gbl_waitPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_waitPanel.rowWeights = new double[]{Double.MIN_VALUE};
		waitPanel.setLayout(gbl_waitPanel);

		JLabel attesa = new JLabel("Attendi che l'avversario finisca il posizionamento");
		attesa.setForeground(Color.GRAY);
		attesa.setFont(new Font("Tahoma", Font.BOLD, 28));
		attesa.setHorizontalAlignment(SwingConstants.CENTER);
		waitPanel.add(attesa);
		waitPanel.setVisible(true);
	}
	public void turnoPanelCreation() {
		// TODO Auto-generated method stub
		turnoPanel = new JPanel();
		turnoPanel.setBackground(Color.white);
		c3.gridx = 1 ;
		c3.gridy = 1;
		c3.weightx = 2;
		gridPanel.add(turnoPanel,c3);
		GridBagLayout gbl_turnoPanel = new GridBagLayout();
		gbl_turnoPanel.columnWidths = new int[]{0};
		gbl_turnoPanel.rowHeights = new int[]{0};
		gbl_turnoPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_turnoPanel.rowWeights = new double[]{Double.MIN_VALUE};
		turnoPanel.setLayout(gbl_turnoPanel);

		turno= new JLabel("E' il tuo turno! Cerca di colpire l'avversario");
		turno.setForeground(Color.GRAY);
		turno.setFont(new Font("Tahoma", Font.BOLD, 22));
		turno.setHorizontalAlignment(SwingConstants.CENTER);
		turno.setVerticalAlignment(SwingConstants.CENTER);
		turnoPanel.add(turno);
		turnoPanel.setVisible(true);
	}

	/*public void mostra() {
		// TODO Auto-generated method stub
		yourBoardPanel.addMouseListener(DGGC);//

	}
	 */
	public JPanel[] getPanel() {
		return panel;
	}
	public void setPanel(JPanel[] panel) {
		this.panel = panel;
	}
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public void addMouseBarche(MouseListener act)
	{
		ArrayList<Integer> dim = getDimNavi();
		for(int i = 0; i < dim.size();i++)
		{
			panel[i].addMouseListener(act);
		}
		System.out.println("sono entrato");
	}
	
	public void removeMouseBarche(MouseListener act)
	{
		ArrayList<Integer> dim = getDimNavi();
		for(int i = 0; i < dim.size();i++)
		{
			panel[i].removeMouseListener(act);
		}
	}

	public void addMouseGriglia(MouseListener act)
	{
		for(int i = 0; i<GRID_DIMENSION; i++)
		{
			for(int j = 0; j < GRID_DIMENSION; j++)
			{
				if(yourBoard[i][j].getName().equals("yourBoard"));
					yourBoard[i][j].addMouseListener(act);
			}
		}
	}
	
	public void removeMouseMiaGrigilia(MouseListener act)
	{
		for(int i = 0; i<GRID_DIMENSION; i++)
		{
			for(int j = 0; j < GRID_DIMENSION; j++)
			{
				if(yourBoard[i][j].getName().equals("yourBoard"));
					yourBoard[i][j].removeMouseListener(act);
			}
		}
	}
	
	public void addListenerOpponentGriglia(MouseListener act, int i, int j)
	{
		opponentBoard[i][j].addMouseListener(act);
	}
	public void addListenerCasellaVuota(MouseListener act, int i, int j)
	{
		if(yourBoard[i][j].getStato()!=1)
		{
			yourBoard[i][j].addMouseListener(act);
		}
	}
	
	public void removeMouseListener(MouseListener act, int i, int j)
	{
		yourBoard[i][j].removeMouseListener(act);
	}
	
	
	public void removeListenerOpponent(MouseListener act, int i, int j)
	{
		opponentBoard[i][j].removeMouseListener(act);
	}
}
