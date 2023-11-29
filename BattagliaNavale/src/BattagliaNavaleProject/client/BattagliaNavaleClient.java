package BattagliaNavaleProject.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BattagliaNavaleClient 
{
	private BattagliaNavaleClient csc;
	private Socket socket;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;

	
	public BattagliaNavaleClient()
	{
		System.out.println("CLIENTTTTT");
		try {
			socket = new Socket("localhost", 51734);
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
			playerID = dataIn.readInt();
			System.out.println("Connected to server as Player #" + playerId + ".");
		}catch(IOException ex){
			System.out.println("IO Exception from CSC constructor");
		}
	}
	
	public void connectToServer()
	{
		csc = new BattagliaNavaleClient();
	}
	
	
	
	//Classe di avvio del client
	/*
	 * VIENE ESEGUITO DOPO AVER CLICCATO MULTIPLAYER
	 * 
	 * player.connectToServer();
	 * player.avvioGioco();
	 */
}
