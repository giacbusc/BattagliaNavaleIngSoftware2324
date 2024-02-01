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

import BattagliaNavaleProject.formGui.DoubleGameGridView;
import BattagliaNavaleProject.client.InfoBoat;
import BattagliaNavaleProject.client.Square;

public class DoubleGameGridControl implements MouseListener, MouseMotionListener{
	
	private static final int GRID_DIMENSION = 10;
	public DoubleGameGridView grid;
	private int clickcount=0;
	int x;
	int y;
	//private int[] arrayRisposta= new int[7];
	private int[] arrayRisposta= {5,5,0,0,1,1,0};
	int boatlenght;
	static String indirizzo;
	public static String getIndirizzo() {
	return indirizzo;
}

public static void setIndirizzo(String indirizzo) {
	DoubleGameGridControl.indirizzo = indirizzo;
}

	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arrayMsg = null;
	
	
	String[] arraymsg =new String [3];
	int dim=3;
	
	// System.out.println("Connecting to th server");
     
		//  Socket to talk to server
 
	public DoubleGameGridControl (DoubleGameGridView grid)
	{	
		this.grid = grid;
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		 System.out.println("Connecting to th server");
		 System.out.println("sono tornato sopra");
	     ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	  		//  Socket to talk to server
		socket.connect(indirizzo);
		clickcount++;
		
		// TODO Auto-generated method stub
 
		try {
			if(e.getSource()instanceof Square && clickcount==1 ) {
				System.out.println(clickcount);
				
				Square clickedSquare= (Square) e.getSource();
				
				System.out.println("sono la square"+clickcount +clickedSquare.getx()+ clickedSquare.gety());
				if(clickedSquare.getName().equals("yourBoard")) {
					//clickedSquare.setBackground(Color.gray); //da togliere
					arraymsg[1]=""+clickedSquare.gety();
					arraymsg[0]=""+clickedSquare.getx();
					System.out.println("barca" + arraymsg[2]);
				
					
					clickcount=0;
					
					
				            String msgserver=(""+arraymsg[0]+","+arraymsg[1]+","+arraymsg[2]);
				            System.out.println(msgserver);
				            
				            socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
				            System.out.println("ho inviato");
				            ricevi2msg(socket,x,y);
				}
				
							
			}
			
			
			else if(e.getSource() instanceof JPanel && clickcount==1) {
				
				JPanel clickedPanel= (JPanel) e.getSource();

				if(clickedPanel.getName().equals("0")) 
				{   
					clickedPanel.setName("Aircraft");
					arraymsg[2]=clickedPanel.getName();
					clickedPanel.setVisible(false);
					System.out.println("ciao funziono sono il clickcount "+ clickcount);
					
				}
				if(clickedPanel.getName().equals("1") ) 
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("Destroyer1");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("2"))
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("Destroyer2");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("3") ) 
				{
					clickedPanel.setVisible(false);
					clickedPanel.setName("Cruiser1");
					arraymsg[2]=clickedPanel.getName();
					System.out.println("barca cliccata "+arraymsg[2]);
				}
				if(clickedPanel.getName().equals("4") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Cruiser2");
					System.out.println("barca cliccata "+(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("5") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Cruiser3");
					System.out.println("barca cliccata "+(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("6") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Submarine1");
					System.out.println("barca cliccata "+clickedPanel.getName());
					arraymsg[2]=(clickedPanel.getName());
					
					
			           
			           
				}
				if(clickedPanel.getName().equals("7") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Submarine2");
					System.out.println("barca cliccata "+(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("8") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Submarine3");
					System.out.println("barca cliccata "+(clickedPanel.getName()));
					arraymsg[2]=(clickedPanel.getName());
				}
				if(clickedPanel.getName().equals("9") ) 
				{
					//cosa fare se clicco navi da 2
					clickedPanel.setVisible(false);
					clickedPanel.setName("Submarine4");
					System.out.println("barca cliccata "+(clickedPanel.getName()));
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
				
			
					//clickedSquare.setBackground(Color.gray); //da togliere
					arraymsg[1]=""+clickedSquare.gety();
					arraymsg[0]=""+clickedSquare.getx();
					System.out.println("barca" + arraymsg[2]);
					
					x=clickedSquare.getx();
					y=clickedSquare.gety();
					clickcount=0;
					
					
					String msgserver=(""+arraymsg[0]+","+arraymsg[1]+","+arraymsg[2]);
		            System.out.println(msgserver);
				            
		            socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
		            System.out.println("ho inviato");
		            System.out.println(""+msgserver);
		            ricevimsg(socket);
				      
				}
		
			}
			
			
				
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
			
		
	}
	
	public void ricevi2msg(ZMQ.Socket socket,int x,int y) {
		
		
		
		 byte[] reply = socket.recv(0);// lo 0 blocca l'esecuzione della funzione finche non si riceve qualcosa
       String rispostamsg= new String(reply, ZMQ.CHARSET);
       System.out.println(rispostamsg);
		String[] arrayStringhe = rispostamsg.split(",");
		 System.out.println();
			
			for(int i = 0; i < arrayStringhe.length; i++)
				arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
	        System.out.println(
	             "Received " + rispostamsg );
	        
	      //0   1   2   3   4   5   6   
		  //x   y   St  N   E   S   O
			System.out.println("quello che ho mandato prima : "+x +" quello che ricevo: "+ arrayRisposta[0]);
	        
	        	if(arrayRisposta[6]==0) {
	        		 while(y!=arrayRisposta[1]) {
	        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.orange);
	        			 arrayRisposta[1]--;
	        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);
	        		 }
			        }
			        if(arrayRisposta[5]==0) {
			        	while(x!=arrayRisposta[0]) {
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.orange);
		        			 arrayRisposta[0]++;
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);
		        		 }
				        }
			        
			       if(arrayRisposta[4]==0) {
			    	   while(y!=arrayRisposta[1]) {
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.orange);
		        			 arrayRisposta[1]++;
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);
		        		 }
			       }
		    
			       if(arrayRisposta[3]==0) {
			    	   while(x!=arrayRisposta[0]) {
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.orange);
		        			 arrayRisposta[0]--;
		        			 grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);
		        		 }
			       }
	        }
	        
	        
		
			
			
		 
	
	
	public void ricevimsg(ZMQ.Socket socket) {
		
       // ZMQ.Socket socket = context.createSocket(SocketType.REQ);
  		//  Socket to talk to server
			
		 byte[] reply = socket.recv(0);// lo 0 blocca l'esecuzione della funzione finche non si riceve qualcosa
        String rispostamsg= new String(reply, ZMQ.CHARSET);
        System.out.println(rispostamsg);
		String[] arrayStringhe = rispostamsg.split(",");
		 System.out.println();
		
		
		for(int i = 0; i < arrayStringhe.length; i++)
			arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
        System.out.println(
             "Received " + rispostamsg );
        
		
		
		
		if(arrayRisposta[2]!=-1) {
        grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.ORANGE);
		}
		
		//else fai qualcosa per l'errore 
       
        
         
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		//0   1   2   3   4   5   6   
		//x   y   St  N   E   S   O
		String nome= arraymsg[2];
		
		
		for(InfoBoat boat: InfoBoat.values()) {
			if(boat.name().equalsIgnoreCase(nome))
				boatlenght=boat.getLunghezza();
		}
		 if(e.getSource() instanceof Square)
		 {
			 Square square = (Square) e.getSource();
			 int x = square.getx();
			 int y = square.gety();
			
			 if(x==arrayRisposta[0] && y == arrayRisposta[1])
			 {
				 if(arrayRisposta[6]==0) {
					 for(int i=1;i<boatlenght;i++)
			        	grid.yourBoard[arrayRisposta[0]-i][arrayRisposta[1]].setBackground(Color.gray);
			        }
			        if(arrayRisposta[5]==0) {
			        	for(int i=1;i<boatlenght;i++)
				        	grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]+i].setBackground(Color.gray);
				        }
			        
			       if(arrayRisposta[4]==0) {
			    	   for(int i=1;i<boatlenght;i++)
			    		   grid.yourBoard[arrayRisposta[0]+i][arrayRisposta[1]].setBackground(Color.gray);
			       }
		    
			       if(arrayRisposta[3]==0) {
			    	   for(int i=1;i<boatlenght;i++)
			    		   grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]-i].setBackground(Color.gray);
			       }
			 }
			 }

		
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
