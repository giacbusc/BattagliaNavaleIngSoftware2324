package BattagliaNavaleProject.multiplayer;
import java.util.ArrayList;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ServerSocket {

    private static final ArrayList<String> connectedClients = new ArrayList<>();
    private static final ArrayList<ZMQ.Socket> clientSockets = new ArrayList<>();

    public void startServer() {
        ZContext context = new ZContext();
        ZMQ.Socket socketServer = context.createSocket(SocketType.REP);
<<<<<<< HEAD
        socketServer.bind("tcp://172.16.128.218:5525");

        try {
            int maxClients = 2;

            for (int clientIndex = 0; clientIndex < maxClients; clientIndex++) {
                System.out.println("In attesa di ricevere una mail...");
=======
        socketServer.bind("tcp://172.16.128.218:55");
        System.out.println("Sono qui");

        try {
            while (clientCount < 2) {
                clientCount++;
                
                
>>>>>>> 191b084bd5caf96e63690f2c0ffefb6a2615e85b
                String request = socketServer.recvStr(0);
                System.out.println("Messaggio ricevuto: " + request);
                String[] authInfo = request.split("\\|");

                if (authInfo.length != 1) {
                    socketServer.send("ERROR", 0);
                    continue; // Salta il resto del loop e attendi un nuovo messaggio
                }

                String username = authInfo[0];
                System.out.println(username);

                if (clientIndex == 0) {
                    connectedClients.add(username);
                    clientSockets.add(socketServer);
                    String responseMessage = "WAIT";
                    socketServer.send(responseMessage.getBytes(), 0);
                    System.out.println("Inviato: " + responseMessage);
                    
                } else if (isUsernameUnique(username)) {
                    connectedClients.add(username);
                    String responseMessage = "OK";

                    // Invia il messaggio "OK" a tutti i client
                    for (ZMQ.Socket socket : clientSockets) {
                        socket.send(responseMessage.getBytes(), 0);
                    }

                    System.out.println("Inviato: " + responseMessage);
                } else {
                    clientIndex--; // Decrementa l'indice per rimanere nello stesso stato
                    String responseMessage = "DUPL";
                    socketServer.send(responseMessage.getBytes(), 0);
                    System.out.println("Inviato: " + responseMessage);
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

    private static boolean isUsernameUnique(String username) {
        for (String str : connectedClients) {
            if (str.equals(username)) {
                // Il nome utente non è univoco, poiché esiste già nella lista
                return false;
            }
        }
        return true;
    }
}

