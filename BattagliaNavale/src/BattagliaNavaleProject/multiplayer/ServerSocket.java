package BattagliaNavaleProject.multiplayer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.InfoBoat;
import BattagliaNavaleProject.client.Square;

import java.util.ArrayList;

public class ServerSocket {
	final static int MAX_LENGTH = 9;
	private Square[][] player1 = new Square[MAX_LENGTH][MAX_LENGTH];
	private Square[][] player2 = new Square[MAX_LENGTH][MAX_LENGTH];
	private String[] spedire = new String[7];

	private String[] mexprec = new String[3];
	private boolean sveglia = false;
	private static final ArrayList<String> connectedClients = new ArrayList<>();
	private ZContext context = new ZContext();
	private ZMQ.Socket socketServer = context.createSocket(SocketType.REP);
	private int turno = 0;

	public void startServer() {

		socketServer.bind("tcp://172.16.128.94:5513");

		try {
			inizializzaSquare();
			int maxClients = 2;

			for (int clientIndex = 0; clientIndex < maxClients; clientIndex++) {
				System.out.println("In attesa di ricevere una mail...");
				String request = socketServer.recvStr(0);
				System.out.println("Messaggio ricevuto: " + request);
				String[] authInfo = request.split("\\|");

				if (authInfo.length != 1) {
					socketServer.send("ERROR", 0);
					continue; // Salta il resto del loop e attendi un nuovo messaggio
				}

				String username = authInfo[0]; // però cosi poi mi prende attesa come nickname

				if (clientIndex == 0) {
					connectedClients.add(username);
					String responseMessage = "WAIT";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
				} else if (request.equals("attesa")) {
					String responseMessage = "MARTIN IN GABBIA";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("stampa " + responseMessage);
					clientIndex--;
				} else if (isUsernameUnique(username)) {
					connectedClients.add(username);
					String responseMessage = "OK";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
					sveglia = true;
					// Ignora il messaggio "attesa" e continua a ricevere

				} else {
					clientIndex--; // Decrementa l'indice per rimanere nello stesso stato
					String responseMessage = "DUPL";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
				}

				if (sveglia == true) {
					String requestSveglia = socketServer.recvStr(0);
					System.out.println("Messaggio ricevuto: " + requestSveglia);
					String responseMessage = "OK POS1";

					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
					turno = 1;

					piazzamentoBarca(turno);
					clientIndex++;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Chiusura del socket e del contesto alla fine del programma
			socketServer.close();
			context.close();
		}
		turno = 2;
		piazzamentoBarca(turno);
	}

	private static boolean isUsernameUnique(String username) {
		for (String str : connectedClients) {
			if (str.equals(username)) {
				// Il nome utente non è univoco, poiché esiste già nella lista
				return false;
			}
		}
		return true;
	}

	private void piazzTest(int turno) {
		System.out.println("Inizio piazzamento " + turno);
	}

	private void piazzamentoBarca(int turno) {
		int countB = 0;
		mexprec[2] = "firstPosition";
		System.out.println("inizio piazzamento ");
		while (countB < 10) {
			byte[] reply = socketServer.recv(0);
			String messaggio = new String(reply, ZMQ.CHARSET);
			System.out.println(messaggio);

			if (messaggio.equals("CODA")) {
				String responseMessage = "CODA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}
			String[] mexSplit = messaggio.split(",");
			String x = mexSplit[0];
			String y = mexSplit[1];
			String nomeBarca = mexSplit[2];
			System.out.println(x + " " + y + " " + nomeBarca);
			InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarca);
			int l = boat.getLunghezza();
			System.out.println("Lunghezza barca: " + l);

			if (mexprec[2].equals(nomeBarca)) { // secondo click della barca
				// RIEMPI CELLE
				riempiCelle(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l,
						Integer.valueOf(mexprec[0]).intValue(), Integer.valueOf(mexprec[1]).intValue(), turno);
			} else { // primo click
				String fiocco = controllaCella(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l, turno);
				socketServer.send(fiocco.getBytes(), 0);
				System.out.println("Inviato fiocco: " + fiocco);
				
				aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), turno);

				countB++;

			}

			// LOGICA DI SPEDIZIONE DEL MEX
			// nel mex prec salviamo x,y,nomebarca
			mexprec[0] = x;
			mexprec[1] = y;
			mexprec[2] = nomeBarca;

		}

	}

	private void aggiornaGriglia(int x, int y, int turno) {
		if (turno == 1) {

			player1[x][y].setStato(1);
		} 
		else {
			player2[x][y].setStato(1);
		}
	}

	public String controllaCella(int x, int y, int l, int turno) {

		String a = Integer.toString(x);
		String b = Integer.toString(y);
		spedire[0] = a;
		spedire[1] = b;

		switch (l) {
		case 1: {
			if (cellaLibera(x, y, turno) == true) {
				spedire[2] = "1";
			}
			break;
		}
		case 2: {

			// **NORD**
			if (checkFuoriGriglia(x, y, l, 0, turno)) {
				if (cellaLibera(x, y - 1, turno) == true) {
					spedire[3] = "0";
					spedire[2] = "1";
				}
			}

			// **EST**
			if (checkFuoriGriglia(x, y, l, 1, turno)) {
				if (cellaLibera(x + 1, y, turno) == true) { // est
					spedire[4] = "0";
					spedire[2] = "1";
				}
			}

			// **SUD**
			if (checkFuoriGriglia(x, y, l, 2, turno)) {
				if (cellaLibera(x, y + 1, turno) == true) {
					spedire[5] = "0";
					spedire[2] = "1";
				}
			}

			// **OVEST**
			if (checkFuoriGriglia(x, y, l, 3, turno)) {
				if (cellaLibera(x - 1, y, turno) == true) { // ovest
					spedire[6] = "0";
					spedire[2] = "1";
				}
			}

			break;
		}

		case 3: {
			int contaCelleVere = 0;

			// **SUD**
			if (checkFuoriGriglia(x, y, l, 2, turno)) {
				for (int i = y; i <= y + 3; i++) {
					if (cellaLibera(x, i, turno) == true) {
						contaCelleVere++;
					}
				}
				if (contaCelleVere == 3) {
					spedire[5] = "0";
					spedire[2] = "1";
				}
				contaCelleVere = 0; // azzero cosi posso riutilizzarlo per gli altri casi
			}
			// **NORD**
			if (checkFuoriGriglia(x, y, l, 0, turno)) {
				for (int i = y; i >= y - 3; i--) {
					if (cellaLibera(x, i, turno) == true) {
						contaCelleVere++;
					}
				}
				if (contaCelleVere == 3) {
					spedire[3] = "0";
					spedire[2] = "1";

				}
				contaCelleVere = 0;
			}
			// **OVEST**
			if (checkFuoriGriglia(x, y, l, 3, turno)) {
				for (int i = x; i >= x - 3; i--) {
					if (cellaLibera(i, y, turno) == true) {
						contaCelleVere++;
					}
				}
				if (contaCelleVere == 3) {
					spedire[6] = "0";
					spedire[2] = "1";

				}
				contaCelleVere = 0;
			}
			// **EST**
			if (checkFuoriGriglia(x, y, l, 1, turno)) {
				for (int i = x; i <= x + 3; i++) {
					if (cellaLibera(i, y, turno) == true) {
						contaCelleVere++;
					}
				}
				if (contaCelleVere == 3) {
					spedire[4] = "0";
					spedire[2] = "1";

				}
				contaCelleVere = 0; // azzero cosi posso riutilizzarlo per gli altri casi
			}
		}

			break;

		case 4: {
			break;
		}

		}

		// Iteriamo attraverso gli elementi dell'array

		StringBuilder composta = new StringBuilder();
		for (String e : spedire) {
			// Aggiungiamo l'elemento alla StringBuilder con uno spazio
			composta.append(e).append(",");
		}

		String compostaFinale = composta.toString().trim();
		return compostaFinale;
	}

	public boolean cellaLibera(int x, int y, int turno) {
		if (turno == 1) {

			if (player1[x][y].getStato() == 0) {
				return true;
			}

		} else {
			if (player2[x][y].getStato() == 0) {
				return true;
			}
		}

		return false;
	}

	public boolean checkFuoriGriglia(int x, int y, int l, int d, int turno) {
		if (d == 0) // nord
		{
			if (x - l - 1 < 0)
				return false;
		}
		if (d == 1) // est
		{
			if (y + (l - 1) > MAX_LENGTH)
				return false;
		}

		if (d == 2) // sud
		{
			if (x + (l - 1) > MAX_LENGTH)
				return false;
		}

		if (d == 3) // ovest
		{
			if (y - l - 1 < 0)
				return false;
		}
		return true;
	}

//riempire le celle dopo il secondo click
	public void riempiCelle(int x, int y, int l, int xp, int yp, int turno) // x e y posizioni secondo click,mentre xp e
																			// yp
	// posizioni primo click
	{
		if (x != xp) // CASO NORD o SUD
		{
			if (xp < x) // caso nord
			{
				for (int i = xp - 1; i > xp - l; i--) {
					// mando le coordinate delle celle da colorare
					// esempio mex: spedire(i,yp,.....) ?una volta che mando mex al client funziona
					// come un return e non va avanti il for?

				}
			}

			if (xp > x) // caso sud
			{
				for (int i = xp + 1; i < xp + l; i++) {
					// mando le coordinate delle celle da colorare
					// esempio mex: spedire(i,yp,.....) ?una volta che mando mex al client funziona
					// come un return e non va avanti il for?
				}
			}
		}

		if (y != yp) // EST O OVEST
		{
			if (yp < y) // EST
			{
				for (int i = yp - 1; i > yp - l; i--) {
					// mando le coordinate delle celle da colorare
					// esempio mex: spedire(xp,i,.....) ?una volta che mando mex al client funziona
					// come un return e non va avanti il for?
				}
			}
			if (yp > y) // OVEST
			{
				for (int i = yp + 1; i < yp + l; i++) {
					// mando le coordinate delle celle da colorare
					// esempio mex: spedire(xp,i,.....) ?una volta che mando mex al client funziona
					// come un return e non va avanti il for?
				}
			}
		}
	}

	public void inizializzaSquare() {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH - 1; j++) {
				player1[i][j] = new Square(i, j, 0);
				player2[i][j] = new Square(i, j, 0);
			}
		}
	}
}
