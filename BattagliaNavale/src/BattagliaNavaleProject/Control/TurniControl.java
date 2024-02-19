package BattagliaNavaleProject.Control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Array;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.Square;
import BattagliaNavaleProject.formGui.DoubleGameGridView;

public class TurniControl  {
DoubleGameGridView DGGV;
final int GRID_DIMENSION=10;
static String indirizzo;
private int stato;
private int x;
private int y;
private boolean r;
private int lunghezza;
static ZContext context = new ZContext();
static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
int[] arraymsg =new int[2];
int[] arrayRisposta;
public TurniControl(String indirizzo,DoubleGameGridView DGGV) {
	// TODO Auto-generated constructor stub
	this.indirizzo=indirizzo;
	this.DGGV=DGGV;
}

/*0 libero bianco
 * 1 occupato giallo
 * 2 colpito 
 * 3 affondato diventa 
 * 4 acqua diventa azzurro   
 * per gli stati
 * 
 * array risposta x y stato lunghezza  N E S O 
 * */
 public void turno() {
	 //rendo la griglia cliccabile 
		for(int i = 0; i < GRID_DIMENSION ; i++)
		{
			for(int j = 0; j < GRID_DIMENSION ; j++)
			{
				DGGV.yourBoard[i][j].removeMouseListener(DGGV);
				DGGV.opponentBoard[i][j].addMouseListener(DGGV);
			}
		}
	//aspetto che sia cliccata
 }
 
 public void colpoClick(MouseEvent e) throws IOException {
	 
	 if(e.getSource()instanceof Square ) {

			Square clickedSquare= (Square) e.getSource();
			
			arraymsg[1]=clickedSquare.gety();
			arraymsg[0]=clickedSquare.getx();
			String msgserver=(""+arraymsg[0]+","+arraymsg[1]);
			System.out.println("Inviato " + msgserver);

			socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
			ricevi();
			
 }
 }

private void ricevi() throws IOException {
	// TODO Auto-generated method stub
	//
	byte[] byteMsg = socket.recv(0);
	System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
	String rispostamsg = new String(byteMsg, ZMQ.CHARSET);

	String[] arrayStringhe = rispostamsg.split(",");
	System.out.println();


	for(int i = 0; i < arrayStringhe.length; i++) 
		arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
	System.out.println(
			"Received msg 2 " + rispostamsg );
	
	lunghezza= arrayRisposta[4]; //mi serve per il ciclo di invio di messaggi 
	stato=arrayRisposta[2];
	x= arrayRisposta[0];
	y= arrayRisposta[1];
	
	controllastato();
	

}

private void controllastato() {
	// TODO Auto-generated method stub
	if(stato==2) {
		
	}
	else if(stato==3) {
		
	}
	else if(stato==4) {
		//DGGV.grid[x][y]
	}
}
}
