package BattagliaNavaleProject.BattagliaNavaleServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;

public class ProvaDb {

    public static void main(String[] args) throws SQLException {
        ConnectionDb conn1 = new ConnectionDb();
        String sql = "SELECT * FROM utente";
        PreparedStatement pstmt = conn1.getConnection().prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        // Itera attraverso le righe del risultato
        while (rs.next()) {
            // Ottieni i valori dalle colonne (supponendo colonne con nomi specifici)
            String nickname = rs.getString("NICKNAME");
            String nome = rs.getString("NOME");
            String cognome = rs.getString("COGNOME");
            String password = rs.getString("PASSWORD");

            // Costruisci una stringa di output con i valori ottenuti
            String resultString = String.format("Nickname: %s, Nome: %s, Cognome: %s, Password: %s",
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

