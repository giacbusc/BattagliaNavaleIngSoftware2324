package BattagliaNavaleProject.multiplayer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.ArrayList;

public class ServerSocket {
	
    private static final ArrayList<String> connectedClients = new ArrayList<>();
    private static final ArrayList<ZMQ.Socket> clientSockets = new ArrayList<>();
    private int clientIndex=0;
    boolean sveglia = false;
                public void startServer() {
                    ZContext context = new ZContext();
                    ZMQ.Socket socketServer = context.createSocket(SocketType.REP);
                    socketServer.bind("tcp://172.16.128.218:5510");

                    try {
                        int maxClients = 2;

                        for (int clientIndex = 0; clientIndex < maxClients; clientIndex++) {
                            System.out.println("In attesa di ricevere una mail...");
                            String request = socketServer.recvStr(0);
                            System.out.println("Messaggio ricevuto: " + request);
                           


                            String[] authInfo = request.split("\\|");

                            if (authInfo.length != 1) {
                                socketServer.send("ERROR", 0);
                                continue; // Salta il resto del loop e attendi un nuovo messaggio
                            }

                            String username = authInfo[0]; //però cosi poi mi prende attesa come nickname
                            //System.out.println(username);

                            if (clientIndex == 0) {
                                connectedClients.add(username);
                                String responseMessage = "WAIT";
                                socketServer.send(responseMessage.getBytes(), 0);
                                System.out.println("Inviato: " + responseMessage);
                            } else if (request.equals("attesa")) {
                            	 String responseMessage = "MARTIN IN GABBIA";
                                 socketServer.send(responseMessage.getBytes(), 0);
                                 System.out.println("stampa "+responseMessage);
                                 clientIndex--;
                            } else if(isUsernameUnique(username)) 
                            {
                            	connectedClients.add(username);
                                String responseMessage = "OK";
                                socketServer.send(responseMessage.getBytes(), 0);
                                System.out.println("Inviato: " + responseMessage);
                                sveglia = true;
                                // Ignora il messaggio "attesa" e continua a ricevere
                               
                            }else{
                                clientIndex--; // Decrementa l'indice per rimanere nello stesso stato
                                String responseMessage = "DUPL";
                                socketServer.send(responseMessage.getBytes(), 0);
                                System.out.println("Inviato: " + responseMessage);
                            }
                            
                            if(sveglia == true)
                            {
                            	 String requestSveglia = socketServer.recvStr(0);
                                 System.out.println("Messaggio ricevuto: " + requestSveglia);
                                 String responseMessage = "OK";
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
//cambiarr
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

