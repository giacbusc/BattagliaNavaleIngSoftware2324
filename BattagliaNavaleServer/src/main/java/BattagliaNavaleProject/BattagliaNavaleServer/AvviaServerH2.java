package BattagliaNavaleProject.BattagliaNavaleServer;
import org.h2.tools.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AvviaServerH2 {
	public static String serverAddress;
    public static String avviaserver() {
        try {
            // Avvia il server H2 sulla porta specificata (es. 9092) e il percorso del database
            String indirizzoIP = "192.168.1.226"; // Modifica con l'indirizzo IP appropriato
            int porta = 5545; // Modifica con la porta appropriata

            Server server = Server.createTcpServer("-tcpPort", Integer.toString(porta), "-tcpAllowOthers", "-ifNotExists");
            server.start();

            // Genera l'indirizzo del server per i client
             serverAddress = "tcp://" + indirizzoIP + ":" + porta;
           
            System.out.println("Server H2 avviato. Puoi connetterti al database tramite: " + serverAddress);


        } catch (Exception e) {
            System.err.println("Errore durante l'avvio del server H2: " + e.getMessage());
        }
		return serverAddress;
     }
    
	public static String getServerAddress() {
		return serverAddress;
	}
	
    }


