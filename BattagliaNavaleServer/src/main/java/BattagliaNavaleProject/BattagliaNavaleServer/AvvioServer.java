package BattagliaNavaleProject.BattagliaNavaleServer;

public class AvvioServer 
{
	
	public static void main(String[] args) throws InterruptedException
	{
		ServerSocket server = ServerSocket.getInstance();
		String tcp= "tcp://172.16.128.203:5555";
		String local="tcp://localhost:5545";
		server.startServer(tcp);

	}
	
}
