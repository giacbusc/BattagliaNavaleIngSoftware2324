package test.java.BattagliaNavaleProject.BattagliaNavale;

import BattagliaNavaleProject.multiplayer.ServerSocket;

/**
 * Unit test for simple App.
 */
public class AppTestServer 
{
    /**
     * Rigorous Test :-)
     * @throws InterruptedException 
     */
	public static void main(String[] args) throws InterruptedException
	{
		ServerSocket server = new ServerSocket();
		server.startServer();

	}
	
}
