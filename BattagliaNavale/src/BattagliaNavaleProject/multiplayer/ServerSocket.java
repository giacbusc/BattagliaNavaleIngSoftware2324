package BattagliaNavaleProject.multiplayer;
import java.util.ArrayList;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerSocket {

    private int clientCount = 0;
    private static final ArrayList<String> connectedClients = new ArrayList<>();
    
    public ServerSocket()
    {
        //ServerSocket server = new ServerSocket();
    }

    public void startServer() {
        ZContext context = new ZContext();
        ZMQ.Socket socketServer = context.createSocket(SocketType.REP);

        socketServer.bind("tcp://172.16.128.218:5549");
        System.out.println("Sono qui");

        try {
            while (clientCount < 2) {
                clientCount++;
                System.out.println(clientCount);
                
                String request = socketServer.recvStr(0);
                String[] authInfo = request.split("\\|");
                if (authInfo.length != 1) 
                {
               		 socketServer.send("ERROR", 0);
                }    
                String username = authInfo[0];
                
                
                if (clientCount == 1) {
              		connectedClients.add(username);
                    String responseMessage = "WAIT";
                    socketServer.send(responseMessage.getBytes(), 0);
                    System.out.println("Inviato: " + responseMessage);
                    
                } else {
                    if (isUsernameUnique(username)) {
                		connectedClients.add(username);
                		String responseMessage = "OK";
                    	socketServer.send(responseMessage.getBytes(), 0);
                    	System.out.println("Inviato: " + responseMessage);
                	}
                	else
                	{
                        String responseMessage = "DUPL";
                        clientCount--;
                    	socketServer.send(responseMessage.getBytes(), 0);
                    	System.out.println("Inviato: " + responseMessage);
                    
                    }

                
            }
         } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiusura del socket e del contesto alla fine del programma
            socketServer.close();
            context.close();
        }
    }
    
    private static boolean isUsernameUnique(String username) 
    {
        
        for (String str : connectedClients) {
            if (str.equals(username)) {
                // The string is not unique, as it already exists in the list
                return false;
            }
        }
        
     	return true;
    }
}
