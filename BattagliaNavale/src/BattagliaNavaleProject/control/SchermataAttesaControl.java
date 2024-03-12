package BattagliaNavaleProject.control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SchermataAttesaView;

public class SchermataAttesaControl {
	
	private boolean r=true;
	static String indirizzo;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	private SchermataAttesaView sv;
	private DoubleGameGridControl DGGC;
	Observer obs;
	TornaMenuPrincipale tmp;
	
	public SchermataAttesaControl(String msg, String username, Observer observer, TornaMenuPrincipale tmp) throws IOException, InterruptedException
	{
		this.tmp = tmp;
		this.obs=observer;
		sv = new SchermataAttesaView(msg, username,obs);
		sv.setVisible(true);
		
		if(msg.equals("ATTESA POSIZIONAMENTO")) {
			//Se il messaggio che viene passato nel momento della chiamata al costruttore di questa classe
			//è uguale a ATTESA POSIZIONAMENTO, allora viene chiamato il metodo attesa
    		attesa(username);
    	}
	}
	public void attesa(String user) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Connecting to th server");
		ZMQ.Socket socket = context.createSocket(SocketType.REQ);
		//  Socket to talk to server
		socket.connect(indirizzo);

		//Viene effettuato un ciclo in cui è presente un attesa fino a quando non viene ricevuto dal server
		//il messaggio OK POS2
		do {
			Thread.sleep(200);
			String sendMsg = "CODA";
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Ricevuto" + sendMsg);

			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);

			if(rispostaMsg.equals("OK POS2")) {
				//Viene aperta la schermata della griglia di gioco
				String filepath = "./music/Background_game_music.wav";
				SoundEffect se = new SoundEffect();
				se.playMusic2(filepath,true);
				DGGC = new DoubleGameGridControl(user, tmp, obs);
				sv.dispose();
				r=false;
			}

		}while(r==true);

	}
	public String getIndirizzo() {
		return indirizzo;
	}


	public static void setIndirizzo(String ind) {
		indirizzo = ind;
	}

	public void chiudi()
	{
		sv.dispose();
	}
	public boolean isR() {
		return r;
	}
	
}
