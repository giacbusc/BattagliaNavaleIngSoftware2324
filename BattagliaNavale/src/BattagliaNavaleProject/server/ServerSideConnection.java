package BattagliaNavaleProject.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSideConnection implements Runnable
{	
	private Socket socket;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private int playerID; //potrebbe essere il nickname
	
	public ServerSideConnection(Socket s, int id)
	{
		socket = s;
		playerID = id;
		
		try {
			dataIn = new DataInputStream(socket.getInputStream());
			dataOut = new DataOutputStream(socket.getOutputStream());
		} catch(IOException ex) {
			System.out.println("IOException from run() SSC");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			dataOut.writeInt(playerID);
			dataOut.flush();
			
			while(true) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dataOut.writeChars(null);
	}
	
}
