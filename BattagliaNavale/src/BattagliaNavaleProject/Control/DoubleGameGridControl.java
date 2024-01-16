package BattagliaNavaleProject.Control;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.TransferHandler;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.DoubleGameGridGUI;
import BattagliaNavaleProject.client.Square;

public class DoubleGameGridControl implements MouseListener, MouseMotionListener{
	
	private static final int GRID_DIMENSION = 10;
	public DoubleGameGridGUI grid;
	private JPanel selectedShip;
	private Point previousPoint;
	private Point currentPoint;
	private int clickcount=0;
	
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arraymsg =new String [3];
	int dim=3;
	
	public static void main(String[] args)
    {
		try  {
        System.out.println("Connecting to th server");

  		//  Socket to talk to server
			socket.connect("tcp://localhost:5555");
		

        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
            String request = "Hello";
            System.out.println("Sending Hello " + requestNbr);
            socket.send(request.getBytes(ZMQ.CHARSET), 0);

            byte[] reply = socket.recv(0);
            System.out.println(
                "Received " + new String(reply, ZMQ.CHARSET) + " " +
                requestNbr
            );
        }

		}finally {}
		
    }
	
	public DoubleGameGridControl (DoubleGameGridGUI grid)
	{	
		this.grid = grid;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickcount++;
		
		// TODO Auto-generated method stub
 
		try {
			if(e.getSource()instanceof Square && clickcount==1 ) {
				System.out.println(clickcount);
				/*Square clickedSquare2= (Square) e.getSource();
				System.out.println("sono la square" +clickedSquare2.getx()+ clickedSquare2.gety());
				if(clickedSquare2.getName().equals("yourBoard")) {
					clickedSquare2.setBackground(Color.ORANGE);
					uso le coordinate mandate per colorare le cose nel mezzo
					*/
				
			}
			
			
			if(e.getSource() instanceof JPanel && clickcount==1) {
				
				JPanel clickedPanel= (JPanel) e.getSource();

				if(clickedPanel.getName().equals("0")) 
				{   
					clickedPanel.setName("aircraft");
					arraymsg[2]=clickedPanel.getName();
					clickedPanel.setVisible(false);
					System.out.println("ciao funziono sono il clickcount "+ clickcount);
					
				}
				if(clickedPanel.getName().equals("1") ) 
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("destroyer1");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("2"))
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("destroyer2");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("3") ) 
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("cruiser1");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("4") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("cruiser2");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("5") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("cruiser3");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("6") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("submarine1");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("7") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("submarine2");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("8") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("submarine3");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("9") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("submarine4");
					System.out.println("barca cliccata "+Integer.parseInt(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				
			}
			
			if(!(e.getSource() instanceof Square )&& clickcount==2) {
				System.out.println("Non puoi cliccare 2 barche; posiziona la barca che hai attualmente selezionato");
				clickcount = 1;
			}
			
				if(e.getSource() instanceof Square && clickcount==2){
				Square clickedSquare= (Square) e.getSource();
				System.out.println("sono la square"+clickcount +clickedSquare.getx()+ clickedSquare.gety());
				if(clickedSquare.getName().equals("yourBoard")) {
					clickedSquare.setBackground(Color.gray);
					arraymsg[1]=""+clickedSquare.gety();
					arraymsg[0]=""+clickedSquare.getx();
					System.out.println("barca " + arraymsg[2]);
					
					
					clickcount=0;
					
				            String msgserver=(""+arraymsg[0]+","+arraymsg[1]+","+arraymsg[2]);
				            System.out.println(msgserver);
				            ricevimsg();
							//socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
				           
				           
					
				}
		
			}
			
			
				
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
			
		
	}
	
	public void ricevimsg() {
		/* byte[] reply = socket.recv(0);// lo 0 blocca l'esecuzione della funzione finche non si riceve qualcosa
        String rispostamsg= new String(reply, ZMQ.CHARSET);
		String[] arrayStringhe = rispostamsg.split(",");
		*/
		//invece che 2 devo mettere arrayStringhe.length
		int[] arrayRisposta= new int[3];
		arrayRisposta[0]=2;
		arrayRisposta[1]=2;
		
		/*for(int i = 0; i < arrayStringhe.length; i++)
			arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
        System.out.println(
             "Received " + rispostamsg );
        */
		
        grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.ORANGE);
        
        
         
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedShip = (JPanel) e.getSource();
		
		MouseListener[] listeners = selectedShip.getMouseListeners();
		System.out.println("Number of listeners: " + listeners.length);
		for (MouseListener listener : listeners) {
		    System.out.println("Listener class: " + listener.getClass());
		}

		System.out.println("Selected ship name: " + selectedShip.getName());

		if(selectedShip.getName().equals("0") || selectedShip.getName().equals("1") || selectedShip.getName().equals("2") || selectedShip.getName().equals("3") || selectedShip.getName().equals("4") || selectedShip.getName().equals("5") || selectedShip.getName().equals("6") || selectedShip.getName().equals("7") || selectedShip.getName().equals("8") || selectedShip.getName().equals("9"))
		{
			previousPoint = selectedShip.getLocation();
			System.out.println("Sono entrato in questa fantastica funzione" + previousPoint);
		}
		else
			previousPoint = null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		//0   1   2   3   4   5   6   
		//x   y   St  N   E   S   O
		
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(previousPoint != null && selectedShip != null)
		{
			System.out.println("Mi dovrei spostare");
			currentPoint = e.getPoint();
			selectedShip = (JPanel) e.getSource();
			selectedShip.setLocation((int) currentPoint.getX() - (int) previousPoint.getX(), (int) currentPoint.getY() - (int) previousPoint.getY());
			previousPoint = currentPoint;
		}
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
