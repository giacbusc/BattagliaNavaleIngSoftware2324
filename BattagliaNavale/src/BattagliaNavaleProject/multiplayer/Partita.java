package BattagliaNavaleProject.multiplayer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Partita {
	ServerSocket s;

	public void inizioGioco()
	{
		ServerSocket s = ServerSocket.getIstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		

	
	}
	
	
}
