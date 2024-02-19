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

	public void inizioGioco() {

		turno = 1;
		ServerSocket s = ServerSocket.getIstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		player1 = s.getPlayer1();
		player2 = s.getPlayer2();
		
		stampaGriglia(1);
		
		while (true) {
			String request = socketServer.recvStr(0);
			System.out.println("Messaggio ricevuto: " + request);

			// BOOLEANO CHE ANDRA SETTATO PER RISPONDERGLI ANZICHE CON ATA CON GIOCA PER
			// RISVEGLIARLO
			if(request.equals("ATA") && INVIATO==true)
			{
				String responseMessage = "GIOCA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				turno=2;
				INVIATO=false;
				continue;
			}
			if (request.equals("ATA")) {
				String responseMessage = "ATA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			} else {
				String[] mexSplit = request.split(",");
				String x = mexSplit[0];
				String y = mexSplit[1];
				if (turno == 1) 
				{
					for (int k = 0; k < spedire.length; k++) {
						spedire[k] = "-1";
					}
					if (player2[Integer.valueOf(x).intValue()][Integer.valueOf(x).intValue()].getStato() == 0) {
						spedire[0] = x;
						spedire[1] = y;
						spedire[2] = "4"; // ha colpito l'acqua
						
						/*
						if(request.equals("ATA"))
						{
							String responseMessage = "GIOCA";
							socketServer.send(responseMessage.getBytes(), 0);
							System.out.println("Inviato: " + responseMessage);
							continue;
						}*/
						
						spedireMex(spedire);

					} else {// colpito o affondato
						spedireMex(spedire);
					}

				}
				else if(turno == 2)
				{
					for (int k = 0; k < spedire.length; k++) {
						spedire[k] = "-1";
					}
					if (player2[Integer.valueOf(x).intValue()][Integer.valueOf(x).intValue()].getStato() == 0) {
						spedire[0] = x;
						spedire[1] = y;
						spedire[2] = "4"; // ha colpito l'acqua
						
						/*
						if(request.equals("ATA"))
						{
							String responseMessage = "GIOCA";
							socketServer.send(responseMessage.getBytes(), 0);
							System.out.println("Inviato: " + responseMessage);
							continue;
						}*/
						
						spedireMex(spedire);

					} else {// colpito o affondato
						spedireMex(spedire);
					}
				}

			}

		}

	}

	public void spedireMex(String[] spedire) {
		s = ServerSocket.getIstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		StringBuilder composta = new StringBuilder();
		for (String e : spedire) {
			// Aggiungiamo l'elemento alla StringBuilder con uno spazio
			composta.append(e).append(",");
		}

		String compostaFinale = composta.toString().trim();
		String fiocco = compostaFinale;
		socketServer.send(fiocco.getBytes(), 0);
		System.out.println("Inviato fiocco: " + fiocco);
		INVIATO = true;
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
