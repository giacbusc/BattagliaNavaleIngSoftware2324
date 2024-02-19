package BattagliaNavaleProject.multiplayer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.InfoBoat;
import BattagliaNavaleProject.client.Square;

import java.util.ArrayList;

public class ServerSocket {
	final static int MAX_LENGTH = 10;
	private Square[][] player1 = new Square[MAX_LENGTH][MAX_LENGTH];
	private Square[][] player2 = new Square[MAX_LENGTH][MAX_LENGTH];
	private String[] spedire = new String[7];
	static String indirizzo;
	private String[] mexprec = new String[3];
	private boolean sveglia = false;
	private static final ArrayList<String> connectedClients = new ArrayList<>();
	private ZContext context = new ZContext();
	private ZMQ.Socket socketServer;
	private int turno = 0;
	private static ServerSocket istance=null;

	public static ServerSocket getIstance()
	{
		if(istance==null)
			istance= new ServerSocket();
		
		return istance;
	}
	
	private ServerSocket()
	{
		socketServer=context.createSocket(SocketType.REP);
	}
	
	
	public void startServer(String indirizzo) {

		socketServer.bind(indirizzo);
		
		/*
		"ABCSDSA"
		""
		"HelloWorld"
		*/
		
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
					System.out.println(clientIndex);
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
			byte[] reply = socketServer.recv(0);
			String messaggio = new String(reply, ZMQ.CHARSET);
			System.out.println("ricevuto: " + messaggio);

			if (messaggio.equals("CODA")) {
				String responseMessage = "OK POS2";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				piazzamentoBarca(turno);
			}
			
			
			
		}

		// mandare mex per dire che pui iniziare il 2
		/*
		 * String responseMessage = "OK POS2";
		 * socketServer.send(responseMessage.getBytes(), 0);
		 * System.out.println("Inviato: " + responseMessage); turno = 2;
		 * piazzamentoBarca(turno);
		 */

		
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public static void setIndirizzo(String ind) {
		indirizzo = ind;
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
		System.out.println("il martin dice shhhhh");
		int countB = 0; // contatore barche
		mexprec[2] = "firstPosition";
		System.out.println("inizio piazzamento ");
		while (countB < 10) {
			byte[] reply = socketServer.recv(0);
			String messaggio = new String(reply, ZMQ.CHARSET);
			System.out.println("ricevuto: "+messaggio);

			if (messaggio.equals("CODA")) {
				String responseMessage = "CODA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}
			
			if (messaggio.equals("ATA")) {
				String responseMessage = "ATA";
				socketServer.send(responseMessage.getBytes(), 0);
				System.out.println("Inviato: " + responseMessage);
				continue;
			}
			stampaGriglia(turno);

			String[] mexSplit = messaggio.split(",");
			String x = mexSplit[0];
			String y = mexSplit[1];
			String nomeBarca = mexSplit[2];
			System.out.println(x + " " + y + " " + nomeBarca);
			InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarca);
			int l = boat.getLunghezza();
			System.out.println("Lunghezza barca: " + l);
			System.out.println("Nome barca: "+nomeBarca);
			String[] dividimex = new String[8]; 
			if (mexprec[2].equals(nomeBarca)) 
			{ 	// secondo click della barca
				// RIEMPI CELLE
				//setto spedire a -1
				 for (int k = 0; k < spedire.length; k++) {
			           spedire[k] = "-1";
			       }
				dividimex[2] = "1";
				String fiocco2=riempiCelle(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l,
						Integer.valueOf(mexprec[0]).intValue(), Integer.valueOf(mexprec[1]).intValue(), turno);
				System.out.println("CONTAAAA DEL 2 CLICK: " + countB);
				
				if(countB==9)
				{
					fiocco2=fiocco2 + "1";//Per indicare che il posizionamento è terminato al client
					
					System.out.println("hihihihihihihi "+fiocco2);
				}
				else
				{
					fiocco2=fiocco2+"0";
					System.out.println("hihihihihihihihi "+fiocco2);
				}
				
				socketServer.send(fiocco2.getBytes(), 0);
				System.out.println("Inviato fiocco2: " + fiocco2);
				countB++;
				
					
			} else { // primo click
				String fiocco = controllaCella(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), l, turno);
				dividimex = fiocco.split(",");
				System.out.println("CONTAAAA: " + countB);
				
				if(l==1 && countB==9)
				{
					fiocco=fiocco + "1";//Per indicare che il posizionamento è terminato al client
					System.out.println("STRINGA FINALE DA SPEDIRE: " + fiocco);
				}
				else 
				{
					fiocco=fiocco+"0";
				}
				socketServer.send(fiocco.getBytes(), 0);
				System.out.println("Inviato fiocco: " + fiocco);
					
				
				if(l>1 && dividimex[6].equals("-1") && dividimex[3].equals("-1") && dividimex[4].equals("-1") && dividimex[5].equals("-1")){ 
					
					System.out.println("ricambio lo stato della cella "+Integer.valueOf(x).intValue()+" "+Integer.valueOf(y).intValue());
					if(turno==1)
					{
						aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(),turno,0);
						
					}
					else
					{
						aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(),turno,0);
					}
				}else{
					aggiornaGriglia(Integer.valueOf(x).intValue(), Integer.valueOf(y).intValue(), turno,1);
					if (l == 1) {
						countB++;
					}
				}
			
				stampaGriglia(turno);
				

			}
			
			
			
			// LOGICA DI SPEDIZIONE DEL MEX
			// nel mex prec salviamo x,y,nomebarca
			if(!(dividimex[2].equals("-1")))
			{
				mexprec[0] = x;
				mexprec[1] = y;
				mexprec[2] = nomeBarca;
				
				for (int i = 0; i < mexprec.length; i++) {
				    System.out.println("Elemento " + i + ": " + mexprec[i]);
				}
			}
			else
			{
				mexprec[2]="errorposition"; //serve per non contare la barca nel mexprec[2].equals(NomeBarca)
				//countB--;
			}
				
			

		}

	}

	private void aggiornaGriglia(int x, int y, int turno,int s) {
		if (turno == 1) {

			player1[x][y].setStato(s);
		} 
		else {
			player2[x][y].setStato(s);
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

			// **OVEST** DEVE AVERE d=3
			if (checkFuoriGriglia(x, y, l, 3, turno)) {
				if (cellaLibera(x, y - 1, turno) == true) {
					
					spedire[6] = "0";
					spedire[2] = "1";
				}
			}

			//  **SUD** DEVE AVERE d=2
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

			break;
		}

		case 3: {
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

			break;

		case 4: {

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
			if (x + (l - 1) > MAX_LENGTH-1)
				return false;
		}

		if (d == 1) // est
		{
			if (y + (l - 1) > MAX_LENGTH-1)
				return false;
		}
		return true;
	}

//riempire le celle dopo il secondo click
	public String riempiCelle(int x, int y, int l, int xp, int yp, int turno) // x e y posizioni secondo click,mentre xp e yp
																			
	// posizioni primo click
	{
		if (x != xp) // CASO NORD o SUD
		{
			if (xp < x) // caso sud
			{
				
				for (int i = xp + 1; i < xp + l; i++) 
				{
					if(turno==1)
						player1[i][yp].setStato(1);
					else
						player2[i][yp].setStato(1);
				}
				//devo mandare le coordinate dell'ultima cella e la direzione in cui colorare(da ultima cella verso la prima)
				//mi serve anche la lunghezza della barca.Basta fare prima cella + lunghezza -1?
				spedire[0]=Integer.toString(xp+l-1);
				spedire[1]=Integer.toString(y);
				spedire[2]="1";
				spedire[3]="0";
			}
				
			

			if (xp > x) // caso nord
			{
				for (int i = xp - 1; i > xp - l; i--)
				{
					if(turno==1)
						player1[i][yp].setStato(1);
					else
						player2[i][yp].setStato(1);

				}
				spedire[0]=Integer.toString(xp-l+1);
				spedire[1]=Integer.toString(y);
				spedire[2]="1";
				spedire[5]="0";
			}
				
			
		}

		if (y != yp) // EST O OVEST
		{
			if (yp > y) // ovest
			{
				
				for (int i = yp - 1; i > yp - l; i--) 
				{
					if(turno==1)
						player1[xp][i].setStato(1);
					else
						player2[xp][i].setStato(1);

				}
			
				spedire[0]=Integer.toString(xp);
				spedire[1]=Integer.toString(yp-l+1);
				spedire[2]="1";
				spedire[4]="0";
			}
			if (yp < y) // est
			{
				for (int i = yp + 1; i < yp + l; i++) 
				{
					if(turno==1)
						player1[xp][i].setStato(1);
					else
						player2[xp][i].setStato(1);

				}
				
				spedire[0]=Integer.toString(xp);
				spedire[1]=Integer.toString(yp+l-1);
				spedire[2]="1";
				spedire[6]="0";
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
				player1[i][j] = new Square(i, j, 0);
				player2[i][j] = new Square(i, j, 0);
			}
		}
	}


	public void stampaGriglia(int turno) {
		for (int i = 0; i < MAX_LENGTH; i++) 
		{
			for (int j = 0; j < MAX_LENGTH; j++) 
			{
				if (turno == 1)
					System.out.print(player1[i][j].getStato() + "\t");
				else
					System.out.print(player2[i][j].getStato() + "\t");
			}
			System.out.println(); // Vai a capo dopo ogni riga
		}

	}
}
