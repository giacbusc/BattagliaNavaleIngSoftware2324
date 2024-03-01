package BattagliaNavaleProject.BattagliaNavaleServer;
import org.h2.tools.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigServer {
	public static String serverAddress;
	
    public static String avviaserver(String indirizzoIP) {
        try {
            // Avvia il server H2 sulla porta specificata (es. 9092) 
        	System.out.println("indirizzoIP: " + indirizzoIP);
            String[] parti = indirizzoIP.split(":");
           
            Server server = Server.createTcpServer("-tcpPort", parti[1], "-tcpAllowOthers", "-ifNotExists");
            server.start();

            // Genera l'indirizzo del server per i client
             serverAddress = "tcp://" + indirizzoIP;
           
            System.out.println("Server H2 avviato. I client possono connettersi all'indirizzo: " + serverAddress);


        } catch (Exception e) {
            System.err.println("Errore durante l'avvio del server H2: " + e.getMessage());
        }
		return serverAddress;
     }
    
	public static String getServerAddress() {
		return serverAddress;
	}
	
    }


