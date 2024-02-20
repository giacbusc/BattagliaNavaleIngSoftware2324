package BattagliaNavaleProject.multiplayer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import BattagliaNavaleProject.client.Square;

public class Partita {
	ServerSocket s;
	int turno;
	private String[] spedire = new String[4];
	Square[][] player1;
	Square[][] player2;
	int MAX_LENGTH = 10;
	boolean INVIATO = false;
	boolean ATA2SET = false;

	public void inizioGioco() {

		turno = 1;
		ServerSocket s = ServerSocket.getInstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		player1 = s.getPlayer1();
		player2 = s.getPlayer2();

		//stampaGriglia(1);

		while (true) {
			byte[] reply = socketServer.recv(0);
			String request = new String(reply, ZMQ.CHARSET);
			System.out.println("Messaggio ricevuto: " + request);

			// BOOLEANO CHE ANDRA SETTATO PER RISPONDERGLI ANZICHE CON ATA CON GIOCA PER
			// RISVEGLIARLO
			if (request.equals("ATA2") && INVIATO == true) {
				String responseMessage = "GIOCA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				if(turno==2) {turno = 1;}
				else {turno=2;}
				
				System.out.println("turno "+turno);
				INVIATO = false;
				continue;
			}

			if (request.equals("ATA2") && INVIATO == false) {
				String responseMessage = "ATA2";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}

			if (request.equals("ATA") && INVIATO == false) {
				String responseMessage = "ATA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}

			if (request.equals("ATA") && INVIATO == true) {
				System.out.println("Stai passando il turno!");
				String responseMessage = "GIOCA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				turno = 2;
				System.out.println("turno "+turno);
				INVIATO = false;
				continue;
			}

			if (!request.equals(""))
				if (!(request.equals("ATA") && !(request.equals("ATA2")))) 
				{
					String[] mexSplit = request.split(",");
					String x = mexSplit[0];
					String y = mexSplit[1];
					if (turno == 1) { 
						System.out.println("turno nell'if "+turno);
						for (int k = 0; k < spedire.length; k++) {
							spedire[k] = "-1";
						}
						if (player2[Integer.valueOf(x).intValue()][Integer.valueOf(y).intValue()].getStato() == 0) {
							spedire[0] = x;
							spedire[1] = y;
							spedire[2] = "4"; // ha colpito l'acqua
							INVIATO = true;
							spedireMex(spedire);

						} else {// colpito o affondato
							System.out.println("HAI CANNATO, NON ANCORA PROGRAMMATO");
						}

					} else if (turno == 2) { 
						System.out.println("turno nell'if "+turno);
						for (int k = 0; k < spedire.length; k++) {
							spedire[k] = "-1";
						}
						if (player1[Integer.valueOf(x).intValue()][Integer.valueOf(y).intValue()].getStato() == 0) {
							spedire[0] = x;
							spedire[1] = y;
							spedire[2] = "4"; // ha colpito l'acqua
							INVIATO = true;
							spedireMex(spedire);

						} else {// colpito o affondato
							System.out.println("HAI CANNATO, NON ANCORA PROGRAMMATO");
							//spedireMex(spedire);
						}
					}
				}
		}

	}

	public void spedireMex(String[] spedire) {
		s = ServerSocket.getInstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		StringBuilder composta = new StringBuilder();
		for (String e : spedire) {
			// Aggiungiamo l'elemento alla StringBuilder con uno spazio
			composta.append(e).append(",");
		}

		String compostaFinale = composta.toString().trim();
		String fiocco = compostaFinale.substring(0, compostaFinale.length() - 1);
		socketServer.send(fiocco.getBytes(), ZMQ.DONTWAIT);
		System.out.println("Inviato fiocco: " + fiocco);

	}

	public void stampaGriglia(int turno) {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				if (turno == 1)
					System.out.print(player1[i][j].getStato() + "\t");
				else
					System.out.print(player2[i][j].getStato() + "\t");
			}
			System.out.println(); // Vai a capo dopo ogni riga
		}
	}

}
