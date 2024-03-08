package BattagliaNavaleProject.BattagliaNavaleServer.start;

import BattagliaNavaleProject.BattagliaNavaleServer.ServerSocket;

public class StartControl 
{
	
	public static void avvioSelection(String indirizzo) throws InterruptedException
	{
		ServerSocket server = ServerSocket.getInstance();
		
		if(indirizzo.equals("LOCALE"))
		{
			String local="tcp://localhost:5545";
			server.startServer(local);
		}
		else
		{
			String tcp= ConfigServer.avviaserver(indirizzo);
			server.startServer(tcp);
		}
	}
	
}
