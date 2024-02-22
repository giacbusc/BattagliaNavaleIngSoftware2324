package BattagliaNavaleProject.BattagliaNavaleServer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.Server;


public class ConnectionDb 
{
	//DA METTERE IN QUESTO MODO
	//private String jdbcURL = "jdbc:h2:./test";
	private String jdbcURL = "jdbc:h2:./BattagliaNavale;FILE_LOCK=NO";
	private String username = "sa";
	private String password = "";
	/*public ConnectionDb()
	{
		try {
			
			//DA MODIFICARE PER METTERLO EMBEDDED
			Server server = Server.createTcpServer().start();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public Connection getConnection() throws SQLException
	{
		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		return connection;
	}
	
	public Connection closeConnection() throws SQLException
	{
		Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		connection.createStatement().execute("SHUTDOWN");
		connection.close();
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
