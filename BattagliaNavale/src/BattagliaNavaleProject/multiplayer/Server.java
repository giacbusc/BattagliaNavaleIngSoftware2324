package BattagliaNavaleProject.multiplayer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.Square;

import org.zeromq.ZContext;

public class Server
{
	private Square[][] player1;
	private Square[][] player2;
	final static int MAX_LENGTH=10;
	
	public Server(int port, ServerListener listener) throws Exception
	{
		try (ZContext context = new ZContext()) {
		      //  Socket to talk to clients
		      ZMQ.Socket socket = context.createSocket(SocketType.REP);
		      socket.bind("tcp://172.16.129.123:5555");

		      while (!Thread.currentThread().isInterrupted()) 
		      {
		        byte[] reply = socket.recv(0); //funz.rec bloccata
		       String messaggio=new String(reply, ZMQ.CHARSET);
		       //devo dividere il messaggio
		       String[] mexSplit=messaggio.split(" ");
		       String x=mexSplit[0];
		       String y=mexSplit[1];
		       String nome=mexSplit[2];
		       
		       
			       
		            
		      
		      }
		  
	}
		public String controllaCella(int x,int y, int nomeBarca)
		{ 
			String[] spedire={x,y,"1","0","0","0","0"};
			int l=lunghezzaBarca(nomeBarca);
		
			if(l==1)
			{
				if(cellaLibera(x,y)==true)
				{
					spedire={x,y,"1","0","0","0","0"};
				}
			}
			if(l==2)
			{[]
				if(cellaLibera(x,y)==true)
				{	//nord
					if(cellaLibera(x+1,y)==true) 
					//sud
					
					spedire= x +" "+y+" "+1+" "+0+" "+0+" "+0+" "+0;
				}
			}
			for(int i=0;i<MAX_LENGTH;i++) 
		       {
		    	   for(int j=0;j<MAX_LENGTH-1;j++)
		    	   {
		    		    
		    	   }
		       }
		}
	public boolean cellaLibera(int x,int y)
	{
		if(player1[x][y].getStato()==0)
		{
			return true;
		}
	}
}
