package BattagliaNavaleProject.Control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.formGui.DoubleGameGridView;
import BattagliaNavaleProject.formGui.SchermataAttesaView;
import BattagliaNavaleProject.formGui.SchermataInizialeView;
import BattagliaNavaleProject.client.Observer;
import BattagliaNavaleProject.form.LoginModel;

public class ConnectionControl 
{
	static String indirizzo;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arrayMsg = null;
	private static LoginModel model;
	static private SchermataAttesaControl sac;
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
	
	public ConnectionControl(SchermataAttesaControl sac, String userName, Observer obs) throws IOException, InterruptedException
	{
		this.sac = sac;
		this.userName = userName;
		
		try  {
	        System.out.println("Connecting to th server");
	        ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	  		//  Socket to talk to server
				socket.connect(indirizzo);
				
	        
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
					sac.chiudi();
					sac= new SchermataAttesaControl("ATTESA POSIZIONAMENTO", userName);
				}
				else if(rispostaMsg.equals("ERROR"))
				{
					//Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata di attesa che qualcosa 
					//è andato storto nella connessione
					System.out.println("C'è stato un errore nella connessione.");
				}
				else if(rispostaMsg.equals("DUPL"))
				{
					obs.update();
					sac.chiudi();
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
							DoubleGameGridView DGGV = new DoubleGameGridView(userName);
							sac.chiudi();
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
						obs.update();
						sac.chiudi();
					}
					
				}
				
					
			}
			
			
		
		
	}finally {}
	}

	public static void setIndirizzo(String ind) {
		// TODO Auto-generated method stub
		indirizzo = ind;
	}

	
	}	
