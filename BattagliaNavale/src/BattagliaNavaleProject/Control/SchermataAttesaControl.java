package BattagliaNavaleProject.Control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.formGui.DoubleGameGridView;

public class SchermataAttesaControl {
String userName;
boolean r=true;
static String indirizzo;



	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	String[] arrayMsg = null;
	
	
	public void attesa(String user) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		 System.out.println("Connecting to th server");
	        ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	  		//  Socket to talk to server
				socket.connect(indirizzo);
				System.out.println("qua non mi sono rotto");
			
		do {
			Thread.sleep(5000);
			String sendMsg = "CODA";
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println(sendMsg);
			
			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg= new String(byteMsg, ZMQ.CHARSET);
			
			if(rispostaMsg.equals("OK POS2")) {
				
				DoubleGameGridView dggv= new DoubleGameGridView();
				r=false;
			}
			
		}while(r==true);
	        
	}
	public String getIndirizzo() {
	return indirizzo;
}


public static void setIndirizzo(String ind) {
	indirizzo = ind;
}

	
}
