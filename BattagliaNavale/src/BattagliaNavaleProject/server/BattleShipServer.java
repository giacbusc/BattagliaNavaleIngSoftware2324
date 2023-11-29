package BattagliaNavaleProject.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BattleShipServer
{
	private int numPlayers;
	private ServerSocket ss;
	private ServerSideConnection player1;
	private ServerSideConnection player2;
	
	public BattleShipServer() 
	{
		System.out.println("----Game Server----");
		numPlayers = 0;
		try {
			ss = new ServerSocket(51734) ;
		} catch (IOException ex) {
		System.out.println("I0Exception from GameServer Constructor");
		}
	}
	
	public void acceptConnections () 
	{
		try {
			System.out.println("Waiting for connections...");
			while (numPlayers < 2) {
				Socket s = ss.accept();
				numPlayers++;
				System.out.println("Player #" + numPlayers + " has connected.");
				ServerSideConnection ssc = new ServerSideConnection(s, numPlayers); //numPlayers ha la stessa funzione del ID
				if(numPlayers == 1) {
					player1 = ssc;
				} else {
					player2 = ssc;
				}
				Thread t = new Thread(ssc);
				t.start();
			} 
			
			System.out.println("We now have 2 players. No longer accepting connections.");
		}catch (IOException ex) {
			System.out.println("I0Exception from acceptConnections()");
			
		}
	}
	
	/*
	 * BattleShipServer serv = new BattleShipServer();
	 * server.acceptConnections();
	 */
		
}



	