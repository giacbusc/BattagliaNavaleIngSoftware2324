package BattagliaNavaleProject.Database;

import java.sql.SQLException;

public class SettingDatabaseTemp 
{
	public static void main(String[] args) throws SQLException
	{
		ConnectionDb conn = new ConnectionDb();
		conn.getConnection();
		
		conn.insertQuery("CREATE TABLE UTENTE ("
				+ "    nome VARCHAR (50) NOT NULL,\r\n"
				+ "    cognome VARCHAR (50) NOT NULL,\r\n" 
				+ "	   nickname VARCHAR (50) PRIMARY KEY,\r\n"
				+ "    password VARCHAR (50) NOT NULL"
				+ ");");
	}
}
