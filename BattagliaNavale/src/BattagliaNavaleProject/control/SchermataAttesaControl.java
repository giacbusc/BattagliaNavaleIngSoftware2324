package BattagliaNavaleProject.control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.DoubleGameGridView;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SchermataAttesaView;

public class SchermataAttesaControl {
	
	boolean r=true;
	static String indirizzo;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arrayMsg = null;
	SchermataAttesaView sv;
	Observer obs;
	TornaMenuPrincipale tmp;
	
	public SchermataAttesaControl(String msg, String username, Observer observer, TornaMenuPrincipale tmp) throws IOException, InterruptedException
	{
		this.tmp = tmp;
		this.obs=observer;
		sv = new SchermataAttesaView(msg, username,obs);
		sv.setVisible(true);
		
		if(msg.equals("ATTESA POSIZIONAMENTO")) {
    		attesa(username);
    	}
	}
	public void attesa(String user) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Connecting to th server");
		ZMQ.Socket socket = context.createSocket(SocketType.REQ);
		//  Socket to talk to server
		socket.connect(indirizzo);

		do {
			Thread.sleep(200);
			String sendMsg = "CODA";
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Ricevuto" + sendMsg);

			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);

			if(rispostaMsg.equals("OK POS2")) {

				String filepath = "./music/Background_game_music.wav";
				SoundEffect se = new SoundEffect();
				se.playMusic(filepath,true);
				DoubleGameGridControl dggc= new DoubleGameGridControl(user, tmp, obs);
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
}
