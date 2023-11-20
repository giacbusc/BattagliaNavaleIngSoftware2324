package BattagliaNavaleProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb 
{
	String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
	String username = "sa";
	String password = "1234";
	

	public ConnectionDb()
	{
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to H2 database");
			String sql = "INSERT INTO UTENTE VALUES('Martin','Espinosa','Martin90','lemiebimbevittime')";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
