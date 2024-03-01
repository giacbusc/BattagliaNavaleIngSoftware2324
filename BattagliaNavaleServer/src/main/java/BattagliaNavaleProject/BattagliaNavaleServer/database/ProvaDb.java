package BattagliaNavaleProject.BattagliaNavaleServer.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvaDb {

    public static void main(String[] args) throws SQLException {
        ConnectionDb conn1 = new ConnectionDb();
        String sql = "SELECT * FROM PARTITA";
        PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        // Itera attraverso le righe del risultato
        while (rs.next()) {
            // Ottieni i valori dalle colonne (supponendo colonne con nomi specifici)
            String nickname = rs.getString("GIOCATORE1");
            String nome = rs.getString("GIOCATORE2");
            String cognome = rs.getString("VINCITORE");
            String password = rs.getString("ID");

            // Costruisci una stringa di output con i valori ottenuti
            String resultString = String.format("g1: %s, g2: %s, vincitore: %s, id: %s",
                    nickname, nome, cognome, password);

            // Stampa o elabora la stringa di output come preferisci
            System.out.println(resultString);
        }
        
        // Chiudi il ResultSet e la connessione quando hai finito
        rs.close();
        pstmt.close();
        conn1.closeConnection();
    }
}

