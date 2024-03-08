package BattagliaNavaleProject.control;

import javax.swing.*;

import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;
import BattagliaNavaleProject.view.Observer;

import java.awt.*;
import java.sql.*;

public class ClassificaControl extends JFrame {
    private Observer obs;

    public ClassificaControl(String username, Observer obs) {
        this.obs = obs;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Codice per ottenere i dati della classifica dal database
        StringBuilder classificaText = new StringBuilder();
        try {
            ConnectionDb conn = new ConnectionDb();
            Statement stmt = conn.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NICKNAME, COUNT(*) AS NumeroVittorie FROM PARTITA WHERE GIOCATOREVINCITORE IS NOT NULL GROUP BY GIOCATOREVINCITORE ORDER BY COUNT(*) DESC");
            stmt.close();
            classificaText.append("Classifica:\n");
            int posizione = 1;
            boolean utenteTrovato = false;

            while (rs.next()) {
                String giocatore = rs.getString("NICKNAME");
                int numeroVittorie = rs.getInt("NumeroVittorie");

                classificaText.append(posizione).append(". ").append(giocatore).append(" - Vittorie: ").append(numeroVittorie).append("\n");

                // Se l'utente corrente è presente nella classifica, evidenzialo
                if (giocatore.equals(username)) {
                    utenteTrovato = true;
                    classificaText.append("    * Utente corrente *\n");
                }

                posizione++;
            }
            rs.close();
            // Se l'utente corrente non è presente nella classifica, aggiungilo alla fine
            if (!utenteTrovato) {
                classificaText.append(posizione).append(". ").append(username).append(" - Vittorie: 0").append("\n");
            }

            conn.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Visualizza i dati della classifica nel JTextArea
        JTextArea textArea = new JTextArea(classificaText.toString());
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        getContentPane().add(panel);
    }
}

