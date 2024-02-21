package BattagliaNavaleProject.Control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.swing.SwingWorker;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.Square;
import BattagliaNavaleProject.formGui.DoubleGameGridView;

public class TurniControl  {
DoubleGameGridView DGGV;
final int GRID_DIMENSION=10;
private String indirizzo;
private int stato;
private int x;
private int y;
private boolean r;
private int lunghezza;
static ZContext context = new ZContext();
static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
int[] arraymsg =new int[2];
int[] arrayRisposta= new int[4];
public TurniControl(String indirizzo,DoubleGameGridView DGGV) {
	// TODO Auto-generated constructor stub
	this.indirizzo=indirizzo;
	this.DGGV=DGGV;
	socket.connect(indirizzo);	
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
				if(DGGV.opponentBoard[i][j].getBackground()==Color.white) {
					DGGV.opponentBoard[i][j].addMouseListener(DGGV);
				}
				
			}
		}
	//aspetto che sia cliccata
 }
 
 public void colpoClick(MouseEvent e) throws IOException {
	 
	 if(e.getSource()instanceof Square ) {

			Square clickedSquare= (Square) e.getSource();
			
			arraymsg[1]=clickedSquare.gety();
			arraymsg[0]=clickedSquare.getx();
			String msgserver=(""+arraymsg[0]+","+arraymsg[1]+",");
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
	
	for(int i = 0; i < arrayStringhe.length; i++) 
		arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
	System.out.println(
			"Received msg  " + rispostamsg );
	
	stato= arrayRisposta[2];
	lunghezza= arrayRisposta[3];
	x= arrayRisposta[0];
	y= arrayRisposta[1];
	controllastato();
	
	
	

}

private void controllastato() {
	// TODO Auto-generated method stub
	if(stato==2) {
		DGGV.opponentBoard[x][y].setColpito();
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				DGGV.opponentBoard[x][y].setColpito();
		
				try {
					cicloattesa();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
		}
				return null;
	
		}
		};
		worker.execute();
	}
	if(stato==3) {
		DGGV.opponentBoard[x][y].setAffondato();
		verificaLunghezza();
		
	}
	else if(stato==4) {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				DGGV.opponentBoard[x][y].setAcqua();
		
				try {
					cicloattesa();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
		}
				return null;
	
		}
		};
		worker.execute();
	}
}

private void verificaLunghezza() {
	// TODO Auto-generated method stub
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		@Override
		protected Void doInBackground() throws Exception {
			for(int i=1;i<lunghezza;i++) {
		String sendMsg = "AFFONDATO";
		socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
		System.out.println(sendMsg);
		
		byte[] byteMsg = socket.recv(0);
		System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
		String rispostamsg = new String(byteMsg, ZMQ.CHARSET);

		String[] arrayStringhe = rispostamsg.split(",");
		System.out.println();


		for(int i1 = 0; i1 < arrayStringhe.length; i1++) 
			arrayRisposta[i1] = Integer.parseInt(arrayStringhe[i1].trim());
		System.out.println(
				"Received msg 2 " + rispostamsg );
		
		lunghezza= arrayRisposta[3]; 
		stato=arrayRisposta[2];
		x= arrayRisposta[0];
		y= arrayRisposta[1];
		DGGV.opponentBoard[x][y].setAffondato();
		
	}
			try {
				cicloattesa();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
	}
			return null;

	}
	};
	worker.execute();
}


private void cicloattesa() throws InterruptedException {
	// TODO Auto-generated method stub
	boolean r=true;
	do {
		DGGV.turnoPanel.setVisible(false);
		toglilistener();
		Thread.sleep(1300);
		String sendMsg = "ATA2";
		DGGV.turnoPanel.setVisible(true);
		socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
		System.out.println("inviata attesa del turno " + sendMsg);

		byte[] byteMsg = socket.recv(0);
		System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
		String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);
		
		if(rispostaMsg.equals("GIOCA")) {
			DGGV.turnoPanelCreation();
			DGGV.turnoPanel.setVisible(true);
			
			turno();
			r=false;
		}

	}while(r==true);
	
}

void toglilistener() {
	// TODO Auto-generated method stub
	System.out.println("tolti");
	for(int i = 0; i < GRID_DIMENSION ; i++)
	{
		for(int j = 0; j < GRID_DIMENSION ; j++)
		{
			
			DGGV.opponentBoard[i][j].removeMouseListener(DGGV);
		}
	}
}
}