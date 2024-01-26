package BattagliaNavaleProject.Control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.formGui.DoubleGameGridView;
import BattagliaNavaleProject.formGui.SchermataAttesaView;
import BattagliaNavaleProject.formGui.SchermataInizialeView;
import BattagliaNavaleProject.form.LoginModel;

public class ConnectionControl 
{
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arrayMsg = null;
	private static LoginModel model;
	static private SchermataAttesaView sav;
	private String userName;
	
	/*public ConnectionControl(SchermataAttesaView sav, String userName) throws IOException
	{
		this.sav = sav;
		this.userName = userName;
		
		try  {
	        System.out.println("Connecting to th server");

	  		//  Socket to talk to server
				socket.connect("tcp://172.16.128.218:5545");
			

	        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
	            String request = "Hello";
	            System.out.println("Sending Hello " + requestNbr);
	            socket.send(request.getBytes(ZMQ.CHARSET), 0);

	            byte[] reply = socket.recv(0);
	            System.out.println(
	                "Received " + new String(reply, ZMQ.CHARSET) + " " +
	                requestNbr
	            );
	        }

			}finally {}
			
			String sendMsg = model.getUserName();
			
			byte[] byteMsg = socket.recv(0);
			String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);

			if(rispostaMsg.equals("OK"))
			{
				DoubleGameGridView DGG = new DoubleGameGridView(socket);
			}
			else if(rispostaMsg.equals("ERROR"))
			{
				//Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata di attesa che qualcosa 
				//è andato storto nella connessione
				System.out.println("C'è stato un errore nella connessione.");
			}
			else if(rispostaMsg.equals("DUPL"))
			{
				SchermataInizialeView scv= new SchermataInizialeView();
				sav.close(socket);
			}
	}*/
	
	public ConnectionControl(SchermataAttesaView sav, String userName) throws IOException
	{
		this.sav = sav;
		this.userName = userName;
		
		try  {
	        System.out.println("Connecting to th server");
	        ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	  		//  Socket to talk to server
				socket.connect("tcp://172.16.128.218:5519");
				
	        
	            /*String request = "Hello";
	            System.out.println("Sending Hello ");
	            socket.send(request.getBytes(ZMQ.CHARSET), 0);

	            byte[] reply = socket.recv(0);
	            System.out.println(
	                "Received " + new String(reply, ZMQ.CHARSET) + " ");
	        */
			
			String sendMsg = userName;
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println(sendMsg);
			byte[] byteMsg = socket.recv(0);
				System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
				String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);
				
				
				if(rispostaMsg.equals("OK"))
				{
					sav= new SchermataAttesaView("ATTESA POSIZIONAMENTO", userName);
				}
				else if(rispostaMsg.equals("ERROR"))
				{
					//Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata di attesa che qualcosa 
					//è andato storto nella connessione
					System.out.println("C'è stato un errore nella connessione.");
				}
				else if(rispostaMsg.equals("DUPL"))
				{
					SchermataInizialeView scv= new SchermataInizialeView();
					sav.close(socket);
				}
				
			
				else if (rispostaMsg.equals("WAIT")) {
				
				while(true) {
					 sendMsg = "attesa";
					 System.out.println(sendMsg);
					socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
					byteMsg = socket.recv(0);
					System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
					rispostaMsg= new String(byteMsg, ZMQ.CHARSET);
					
					if(rispostaMsg.equals("OK POS1")) {
						try {
							DoubleGameGridView DGG = new DoubleGameGridView(socket);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//dario
							e.printStackTrace();
						}
						break;
					}else if(rispostaMsg.equals("ERROR"))
					{
						//Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata di attesa che qualcosa 
						//è andato storto nella connessione
						System.out.println("C'è stato un errore nella connessione.");
					}
					else if(rispostaMsg.equals("DUPL"))
					{
						SchermataInizialeView scv= new SchermataInizialeView();
						sav.close(socket);
					}
					
				}
				
					
			}
			
			
		
		
	}finally {}
	}

	
	}	

