package BattagliaNavaleProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class ConnectionDb 
{
	private String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
	private String username = "sa";
	private String password = "1234";
	public ConnectionDb()
	{
		
		try {
			Server server = Server.createTcpServer().start();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet insertQuery(String sql) throws SQLException
	{
		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		statement.close();
		connection.close();
		return resultSet;
		
	}
}
