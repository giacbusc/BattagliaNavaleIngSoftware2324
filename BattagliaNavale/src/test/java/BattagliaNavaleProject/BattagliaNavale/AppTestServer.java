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
		String tcp= "tcp://172.16.128.120:5530";
		String local="tcp://localhost:5547";
		server.startServer(local);

	}
	
}
