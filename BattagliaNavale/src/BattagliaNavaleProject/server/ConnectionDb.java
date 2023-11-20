package BattagliaNavaleProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class ConnectionDb 
{
	public ConnectionDb()
	{
		String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
		String username = "sa";
		String password = "1234";
		try {
			Server server = Server.createTcpServer().start();
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			Statement statement = connection.createStatement();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
