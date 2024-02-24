package BattagliaNavaleProject.BattagliaNavaleServer;

public class AvvioServer 
{
	
	public static void main(String[] args) throws InterruptedException
	{
		ServerSocket server = ServerSocket.getInstance();
		String tcp= AvviaServerH2.avviaserver();
		String local="tcp://localhost:5545";
		server.startServer(tcp);

	}
	
}
