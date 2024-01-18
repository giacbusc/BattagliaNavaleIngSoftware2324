package BattagliaNavaleProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.Server;


public class ConnectionDb 
{
	private String jdbcURL = "jdbc:h2:tcp://172.16.129.25:9092/~/test";
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
	
	public Connection getConnection() throws SQLException
	{
		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		return connection;
	}
	
	public boolean insertQuery(String sql) throws SQLException
	{
		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		Statement statement = connection.createStatement();
		boolean resultSet = statement.execute(sql);
		//statement.close();
		//connection.close();
		return resultSet;
		
	}
}
