package BattagliaNavaleProject.server;

import java.sql.SQLException;

import org.h2.tools.Server;

public class StartDatabase 
{
	public static void main(String[] args) throws SQLException
	{
		Server server = Server.createTcpServer(args).start();
	}
}
