package BattagliaNavaleProject.database;

import java.sql.SQLException;

public class SettingDatabaseTemp 
{
	public static void main(String[] args) throws SQLException
	{
		ConnectionDb conn = new ConnectionDb();
		conn.getConnection();
	

		
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('buscst', 'fedegerva', 'buscst');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('m', 'p', 'm');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('m', 'p', 'm');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('fedegerva', 'm', 'm');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('giesse', 'lucaciancio', 'giesse');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('fedegerva', 'lucaciancio', 'fedegerva');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('p', 'buscst', 'p');");
		conn.insertQuery("INSERT INTO PARTITA (GIOCATORE1, GIOCATORE2, VINCITORE) VALUES ('lucaciancio', 'p', 'lucaciancio');");
	}
}
