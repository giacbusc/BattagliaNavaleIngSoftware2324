package BattagliaNavaleProject.BattagliaNavaleServer;

public class AvvioServer 
{
	
	public static void main(String[] args) throws InterruptedException
	{
		ServerSocket server = ServerSocket.getInstance();
		String tcp= "tcp://192.168.1.4:5554";
		String local="tcp://localhost:5545";
		server.startServer(tcp);

	}
	
}
