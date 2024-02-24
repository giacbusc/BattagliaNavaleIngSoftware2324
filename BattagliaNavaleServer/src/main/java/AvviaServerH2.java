import org.h2.tools.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AvviaServerH2 {
    public static void main(String[] args) {
        try {
            // Avvia il server H2 sulla porta specificata (es. 9092) e il percorso del database
            String indirizzoIP = "192.168.1.4"; // Modifica con l'indirizzo IP appropriato
            int porta = 5545; // Modifica con la porta appropriata

            Server server = Server.createTcpServer("-tcpPort", Integer.toString(porta), "-tcpAllowOthers", "-ifNotExists");
            server.start();

            // Genera l'indirizzo del server per i client
            String serverAddress = "tcp://" + indirizzoIP + ":" + porta;

            System.out.println("Server H2 avviato. Puoi connetterti al database tramite: " + serverAddress);

            // Scrivi l'indirizzo del server in un file di configurazione
            salvaIndirizzoServer(serverAddress);

            // Puoi inserire ulteriore logica qui, o aspettare l'interruzione del programma per chiudere il server
            // ad esempio: Thread.sleep(Long.MAX_VALUE);

        } catch (Exception e) {
            System.err.println("Errore durante l'avvio del server H2: " + e.getMessage());
        }
    }

    private static void salvaIndirizzoServer(String serverAddress) {
        Properties properties = new Properties();
        properties.setProperty("server.address", serverAddress);

        try (FileOutputStream output = new FileOutputStream("config.properties")) {
            properties.store(output, "Configurazione del server H2");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dell'indirizzo del server: " + e.getMessage());
        }
    }
}


