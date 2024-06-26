package BattagliaNavaleProject.BattagliaNavaleServer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.InfoBoat;
import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.Square;

import java.util.ArrayList;

public class ServerSocket implements InterfacciaServerPartita {
	final static int MAX_LENGTH = 10;
	private Square[][] player1 = new Square[MAX_LENGTH][MAX_LENGTH];
	private Square[][] player2 = new Square[MAX_LENGTH][MAX_LENGTH];
	private String[] spedire = new String[7];
	private String ataprecedente;
	static String indirizzo;
	private String[] mexprec = new String[3];
	private boolean sveglia = false;
	private static final ArrayList<String> connectedClients = new ArrayList<>();
	private ZContext context = new ZContext();
	private ZMQ.Socket socketServer;
	private int turno = 0;
	private static ServerSocket instance = null;

	private ServerSocket() {
		socketServer = context.createSocket(SocketType.REP);
	}

	public void startServer(String indirizzo) {

		socketServer.bind(indirizzo);

		/*
		 * "ABCSDSA" "" "HelloWorld"
		 */

		try {
			inizializzaSquare();
			int maxClients = 2;

			for (int clientIndex = 0; clientIndex < maxClients; clientIndex++) {
				System.out.println("In attesa di un giocatore..");
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
					Thread.sleep(500);
					String responseMessage = "in attesa";
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
					System.out.println(clientIndex);
					System.out.println("STAMPA NOMI");
					stampaGriglia2(turno);
					turno = 2;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			socketServer.close();
			context.close();
		} /*
			 * finally { // Chiusura del socket e del contesto alla fine del programma
			 * System.out.println("il martin fa etciù");
			 * 
			 * }
			 */

		if (turno == 2) {
			boolean r = true;
			while (r == true) {
				byte[] reply = socketServer.recv(0);
				String messaggio = new String(reply, ZMQ.CHARSET);
				System.out.println("ricevuto: " + messaggio);

				if (messaggio.equals("CODA")) {
					String responseMessage = "OK POS2";
					socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
					System.out.println("Inviato: " + responseMessage);
					piazzamentoBarca(turno);
					System.out.println("inizio giocooooo");

					while (true) {
						reply = socketServer.recv(0);
						messaggio = new String(reply, ZMQ.CHARSET);
						System.out.println("ricevuto: " + messaggio);
						String messaggioATA2 = messaggio.substring(0, 3);
						String numeroATA = messaggio.substring(3);
						int numattuale = Integer.parseInt(numeroATA);
						System.out.println("num attuale: " + numattuale);
						String numeroATAprecedente = ataprecedente.substring(3);
						int numprecedente = Integer.parseInt(numeroATAprecedente);
						System.out.println("num precedente: " + numprecedente);

						if (messaggioATA2.equals("ATA") && numattuale > numprecedente) {
							r = false;
							responseMessage = "GIOCA";
							socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
							System.out.println("Inviato AL PLAYER 1: " + responseMessage);
							Partita a = new Partita(this);
							a.inizioGioco();
							break;
						} else {
							responseMessage = "ATA";
							socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
							System.out.println("Inviato AL PLAYER 2: " + responseMessage);

						}
					}

				}
			}

		}

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

	private void piazzamentoBarca(int turno) {
		int countB = 0; // contatore barche
		mexprec[2] = "firstPosition";
		System.out.println("inizio piazzamento ");
		while (countB < 10) {
			byte[] reply = socketServer.recv(0);
			String messaggio = new String(reply, ZMQ.CHARSET);
			System.out.println("ricevuto: " + messaggio);

			if (messaggio.equals("CODA")) {
				String responseMessage = "CODA";
				socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}
			String messaggioATA = messaggio.substring(0, 3);
			if (messaggioATA.equals("") || messaggioATA.equals("ATA")) {
				ataprecedente = messaggio;
				String responseMessage = "ATA";
				socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}
			stampaGriglia(turno);

			String[] mexSplit = messaggio.split(",");
			String x = mexSplit[0];
			String y = mexSplit[1];
			String nomeBarca = mexSplit[2];
			// System.out.println(x + " " + y + " " + nomeBarca);
			InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarca);
			int l = boat.getLunghezza();
			System.out.println("Lunghezza barca: " + l);
			System.out.println("Nome barca: " + nomeBarca);
			String[] dividimex = new String[8];
			if (mexprec[2].equals(nomeBarca)) { // secondo click della barca
												// RIEMPI CELLE
												// setto spedire a -1
				dividimex[2] = "1";
				String fiocco2 = riempiCelle(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l,
						Integer.valueOf(mexprec[0]).intValue(), Integer.valueOf(mexprec[1]).intValue(), turno,
						nomeBarca);
				System.out.println("check nome2 " + nomeBarca);
				if (countB == 9) {
					fiocco2 = fiocco2 + "1";// Per indicare che il posizionamento è terminato al client

					System.out.println("inviato " + fiocco2);
				} else {
					fiocco2 = fiocco2 + "0";
					System.out.println("inviato " + fiocco2);
				}

				socketServer.send(fiocco2.getBytes(), 0);
				System.out.println("Inviato fiocco2: " + fiocco2);
				countB++;

			} else { // primo click
				String fiocco = controllaCella(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l, turno);
				dividimex = fiocco.split(",");

				if (l == 1 && countB == 9) {
					fiocco = fiocco + "1";// Per indicare che il posizionamento è terminato al client
					System.out.println("STRINGA FINALE DA SPEDIRE: " + fiocco);
				} else {
					fiocco = fiocco + "0";
				}
				socketServer.send(fiocco.getBytes(), 0);
				System.out.println("Inviato: " + fiocco);

				if (l > 1 && dividimex[6].equals("-1") && dividimex[3].equals("-1") && dividimex[4].equals("-1")
						&& dividimex[5].equals("-1")) {

					System.out.println("ricambio lo stato della cella " + Integer.valueOf(x).intValue() + " "
							+ Integer.valueOf(y).intValue());
					if (turno == 1) {
						aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), turno, 0,
								nomeBarca);

					} else {
						aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), turno, 0,
								nomeBarca);

					}
				} else {
					aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), turno, 1, nomeBarca);

					if (l == 1) {
						countB++;
					}

				}

				stampaGriglia(turno);

			}

			// LOGICA DI SPEDIZIONE DEL MEX
			// nel mex prec salviamo x,y,nomebarca
			if (!(dividimex[2].equals("-1"))) {
				mexprec[0] = x;
				mexprec[1] = y;
				mexprec[2] = nomeBarca;

				for (int i = 0; i < mexprec.length; i++) {
					System.out.println("Elemento " + i + ": " + mexprec[i]);
				}
			} else {
				mexprec[2] = "errorposition"; // serve per non contare la barca nel mexprec[2].equals(NomeBarca)
				// countB--;
			}

		}

	}

	private void aggiornaGriglia(int x, int y, int turno, int s, String n) {
		if (turno == 1) {

			player1[x][y].setStato(s);
			if (s == 1) {
				player1[x][y].setNome(n);
				// System.out.println(player1[x][y].getNome());
			}
		} else {
			player2[x][y].setStato(s);
			if (s == 1) {
				player2[x][y].setNome(n);
				// System.out.println(player2[x][y].getNome());
			}
		}

	}

	public String controllaCella(int x, int y, int l, int turno) {

		// Riempimento dell'array con il valore -1
		for (int k = 0; k < spedire.length; k++) {
			spedire[k] = "-1";
		}

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
			Case2ControllaCella(x, y, l, turno);
			break;
		}

		case 3: {
			Case3ControllaCella(x, y, l, turno);
			break;
		}

		case 4: {
			Case4ControllaCella(x, y, l, turno);
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

	private void Case4ControllaCella(int x, int y, int l, int turno) {
		// TODO Auto-generated method stub
		int contaCelleVere = 0;

		// **SUD**
		if (checkFuoriGriglia(x, y, l, 2, turno)) {
			for (int i = x; i < x + 4; i++) {
				if (cellaLibera(i, y, turno) == true) {
					contaCelleVere++;
				}
			}
			if (contaCelleVere == 4) {
				spedire[5] = "0";
				spedire[2] = "1";
			}
			contaCelleVere = 0; // azzero cosi posso riutilizzarlo per gli altri casi
		}
		// **NORD**
		if (checkFuoriGriglia(x, y, l, 0, turno)) {
			for (int i = x; i > x - 4; i--) {
				if (cellaLibera(i, y, turno) == true) {
					contaCelleVere++;
				}
			}
			if (contaCelleVere == 4) {
				spedire[3] = "0";
				spedire[2] = "1";

			}
			contaCelleVere = 0;
		}
		// **OVEST**
		if (checkFuoriGriglia(x, y, l, 3, turno)) {
			for (int i = y; i > y - 4; i--) {
				if (cellaLibera(x, i, turno) == true) {
					contaCelleVere++;
				}
			}
			if (contaCelleVere == 4) {
				spedire[6] = "0";
				spedire[2] = "1";

			}
			contaCelleVere = 0;
		}
		// **EST**
		if (checkFuoriGriglia(x, y, l, 1, turno)) {
			for (int i = y; i < y + 4; i++) {
				if (cellaLibera(x, i, turno) == true) {
					contaCelleVere++;
				}
			}
			if (contaCelleVere == 4) {
				spedire[4] = "0";
				spedire[2] = "1";

			}
			contaCelleVere = 0; // azzero cosi posso riutilizzarlo per gli altri casi
		}

	}

	private void Case3ControllaCella(int x, int y, int l, int turno) {
		// TODO Auto-generated method stub
		int contaCelleVere = 0;

		// **SUD**
		if (checkFuoriGriglia(x, y, l, 2, turno)) {
			for (int i = x; i < x + 3; i++) {
				if (cellaLibera(i, y, turno) == true) {
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
			for (int i = x; i > x - 3; i--) {
				if (cellaLibera(i, y, turno) == true) {
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
			for (int i = y; i > y - 3; i--) {
				if (cellaLibera(x, i, turno) == true) {
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
			for (int i = y; i < y + 3; i++) {
				if (cellaLibera(x, i, turno) == true) {
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

	private void Case2ControllaCella(int x, int y, int l, int turno) {
		// **OVEST** DEVE AVERE d=3
		if (checkFuoriGriglia(x, y, l, 3, turno)) {
			if (cellaLibera(x, y - 1, turno) == true) {

				spedire[6] = "0";
				spedire[2] = "1";
			}
		}

		// **SUD** DEVE AVERE d=2
		if (checkFuoriGriglia(x, y, l, 2, turno)) {
			if (cellaLibera(x + 1, y, turno) == true) { // est
				spedire[5] = "0";
				spedire[2] = "1";
			}
		}

		// **EST** DEVE AVERE d=1
		if (checkFuoriGriglia(x, y, l, 1, turno)) {
			if (cellaLibera(x, y + 1, turno) == true) {
				spedire[4] = "0";
				spedire[2] = "1";
			}
		}

		// **NORD** DEVE AVERE d=0
		if (checkFuoriGriglia(x, y, l, 0, turno)) {
			if (cellaLibera(x - 1, y, turno) == true) { // ovest
				spedire[3] = "0";
				spedire[2] = "1";
			}
		}

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
			if (x - l + 1 < 0)
				return false;
		}
		if (d == 3) // ovest
		{
			if (y - l + 1 < 0)
				return false;
		}

		if (d == 2) // sud
		{
			if (x + (l - 1) > MAX_LENGTH - 1)
				return false;
		}

		if (d == 1) // est
		{
			if (y + (l - 1) > MAX_LENGTH - 1)
				return false;
		}
		return true;
	}

//riempire le celle dopo il secondo click
	public String riempiCelle(int x, int y, int l, int xp, int yp, int turno, String nomeBarca) // x e y posizioni //
																								// secondo click,mentre
																								// xp e yp
	// posizioni primo click
	{
		for (int k = 0; k < spedire.length; k++) {
			spedire[k] = "-1";
		}
		if (x != xp) // CASO NORD o SUD
		{
			if (xp < x) // caso sud
			{

				for (int i = xp + 1; i < xp + l; i++) {
					if (turno == 1) {
						player1[i][yp].setStato(1);
						player1[i][yp].setNome(nomeBarca);
					} else {
						player2[i][yp].setStato(1);
						player2[i][yp].setNome(nomeBarca);
					}
				}
				// devo mandare le coordinate dell'ultima cella e la direzione in cui
				// colorare(da ultima cella verso la prima)
				// mi serve anche la lunghezza della barca.Basta fare prima cella + lunghezza
				// -1?
				spedire[0] = Integer.toString(xp + l - 1);
				spedire[1] = Integer.toString(y);
				spedire[2] = "1";
				spedire[3] = "0";
			}

			if (xp > x) // caso nord
			{
				for (int i = xp - 1; i > xp - l; i--) {
					if (turno == 1) {
						player1[i][yp].setStato(1);
						player1[i][yp].setNome(nomeBarca);
					}

					else {
						player2[i][yp].setStato(1);
						player2[i][yp].setNome(nomeBarca);
					}

				}
				spedire[0] = Integer.toString(xp - l + 1);
				spedire[1] = Integer.toString(y);
				spedire[2] = "1";
				spedire[5] = "0";
			}

		}

		if (y != yp) // EST O OVEST
		{
			if (yp > y) // ovest
			{

				for (int i = yp - 1; i > yp - l; i--) {
					if (turno == 1) {
						player1[xp][i].setStato(1);
						player1[xp][i].setNome(nomeBarca);
						System.out.println(player1[xp][i].getNome());
					}

					else {
						player2[xp][i].setStato(1);
						player2[xp][i].setNome(nomeBarca);
					}

				}

				spedire[0] = Integer.toString(xp);
				spedire[1] = Integer.toString(yp - l + 1);
				spedire[2] = "1";
				spedire[4] = "0";
			}
			if (yp < y) // est
			{
				for (int i = yp + 1; i < yp + l; i++) {
					if (turno == 1) {
						player1[xp][i].setStato(1);
						player1[xp][i].setNome(nomeBarca);
						System.out.println(player1[xp][i].getNome());
					}

					else {
						player2[xp][i].setStato(1);
						player2[xp][i].setNome(nomeBarca);

					}

				}

				spedire[0] = Integer.toString(xp);
				spedire[1] = Integer.toString(yp + l - 1);
				spedire[2] = "1";
				spedire[6] = "0";
			}
		}

		StringBuilder composta = new StringBuilder();
		for (String e : spedire) {
			// Aggiungiamo l'elemento alla StringBuilder con uno spazio
			composta.append(e).append(",");
		}

		String compostaFinale = composta.toString().trim();
		return compostaFinale;
	}

	public void inizializzaSquare() {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				player1[i][j] = new Square(i, j, 0, "0000000000");
				player2[i][j] = new Square(i, j, 0, "0000000000");

			}
		}
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

	public void stampaGriglia2(int turno) {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				if (turno == 1)
					System.out.print(player1[i][j].getNome() + "\t");
				else
					System.out.print(player2[i][j].getNome() + "\t");
			}
			System.out.println(); // Vai a capo dopo ogni riga
		}

	}

	public ZMQ.Socket getSocketServer() {
		return socketServer;
	}

	public void setSocketServer(ZMQ.Socket socketServer) {
		this.socketServer = socketServer;
	}

	public Square[][] getPlayer1() {
		return player1;
	}

	public void setPlayer1(Square[][] player1) {
		this.player1 = player1;
	}

	public Square[][] getPlayer2() {
		return player2;
	}

	public void setPlayer2(Square[][] player2) {
		this.player2 = player2;
	}

	public ArrayList<String> getConnectedclients() {
		return connectedClients;
	}

	public static ServerSocket getInstance() {
		if (instance == null)
			instance = new ServerSocket();

		return instance;
	}

}
