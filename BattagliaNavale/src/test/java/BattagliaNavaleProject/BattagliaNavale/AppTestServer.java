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
		ServerSocket server = ServerSocket.getInstance();
		String tcp= "tcp://192.168.60.206:5536";
		String local="tcp://localhost:5545";
		server.startServer(tcp);

	}
	
}
