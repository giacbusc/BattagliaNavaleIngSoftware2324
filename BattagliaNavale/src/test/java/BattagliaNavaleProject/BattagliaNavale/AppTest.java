package test.java.BattagliaNavaleProject.BattagliaNavale;

import BattagliaNavaleProject.Control.ConnectionControl;
import BattagliaNavaleProject.multiplayer.ServerSocket;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	public static void main(String[] args)
	{
		ServerSocket server = new ServerSocket();
		server.startServer();
		ConnectionControl c = new ConnectionControl("ciancio");
	}
	
}
