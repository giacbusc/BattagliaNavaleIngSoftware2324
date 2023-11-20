package BattagliaNavaleProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb 
{
	

	public static void main(String[] args)
	{
		String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
		String username = "sa";
		String password = "1234";
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to H2 database");
			String sql = "INSERT INTO UTENTE VALUES('Filippo','Ciancio','Pippo90','lemiebimbevittime')";
			String sql2="INSERT INTO PARTITE (GIOCATORE1,GIOCATORE2,VINCITORE) VALUES ('Martin90','Pippo90','Martin90')";
			
			Statement statement = connection.createStatement();
			boolean resultSet = statement.execute(sql2);
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
