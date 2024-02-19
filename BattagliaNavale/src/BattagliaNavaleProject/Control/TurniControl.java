package BattagliaNavaleProject.Control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

static ZContext context = new ZContext();
static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
DoubleGameGridControl DGGC;
String[] arraymsg =new String [2];
public TurniControl(String indirizzo,DoubleGameGridView DGGV) {
	// TODO Auto-generated constructor stub
	this.indirizzo=indirizzo;
	this.DGGV=DGGV;
}

/*
 * */
 public void turno() {
	 //rendo la griglia cliccabile 
		for(int i = -1; i < GRID_DIMENSION + 1; i++)
		{
			for(int j = -1; j < GRID_DIMENSION + 1; j++)
			{
				DGGV.yourBoard[i][j].removeMouseListener(DGGV);
				DGGV.opponentBoard[i][j].addMouseListener(DGGV);
			}
		}
	//aspetto che sia cliccata
 }
 
 public void colpoClick(MouseEvent e) {
	 
	 if(e.getSource()instanceof Square ) {

			Square clickedSquare= (Square) e.getSource();
			
			arraymsg[1]=""+clickedSquare.gety();
			arraymsg[0]=""+clickedSquare.getx();
			String msgserver=(""+arraymsg[0]+","+arraymsg[1]);
			System.out.println("Inviato " + msgserver);

			socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
			
			
 }
 }
}
