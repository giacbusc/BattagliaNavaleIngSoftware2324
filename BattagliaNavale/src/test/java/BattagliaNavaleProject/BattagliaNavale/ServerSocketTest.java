package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.*;

import org.junit.Test;

import BattagliaNavaleProject.multiplayer.ServerSocket;

public class ServerSocketTest {

	@Test
	public void testControllaCellaCase1() {
		// caso per una cella libera
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		String result = serverSocket.controllaCella(0, 0, 1, 1); // x,y,l,turno
		assertEquals("0,0,1,-1,-1,-1,-1,", result);
	}

	@Test
	public void testControllaCellaCase2() {
		// testcase per una cella occupata
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		serverSocket.getPlayer1()[0][0].setStato(1); // la cella è gia occupata
		String result = serverSocket.controllaCella(0, 1, 2, 1);
		assertEquals("0,1,1,-1,0,0,-1,", result);
	}

	@Test
	public void testControllaCellaCase3() {
		// check piazzamento barca
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();// la square è tutta libera
		String result = serverSocket.controllaCella(4, 4, 3, 1);
		assertEquals("4,4,1,0,0,0,0,", result);
	}

	@Test
	public void testControllaCellaCase4() {
		// caso di piazzamento
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();// la square è tutta libera
		String result = serverSocket.controllaCella(9, 9, 4, 1);
		assertEquals("9,9,1,0,-1,-1,0,", result);
	}

	@Test
	public void testcellaLiberaOccupata() {
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		serverSocket.getPlayer1()[0][0].setStato(1);
		boolean result = serverSocket.cellaLibera(0, 0, 1);
		assertFalse(result);

	}

	@Test
	public void testcellaLibera() {
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		boolean result = serverSocket.cellaLibera(2, 2, 1);
		assertTrue(result);
	}

	@Test
	public void testRiempiCelleCase1_casoSud() {
		
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		//spedire è inizializzato a -1
		String result = serverSocket.riempiCelle(2, 2, 3, 0, 2, 1, "Destroyer1");
		assertEquals("2,2,1,0,-1,-1,-1,", result);
	}

	@Test
	public void testRiempiCelleCase2_casoNord() {
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		//spedire è inizializzato a -1
		String result = serverSocket.riempiCelle(3, 3, 2, 5, 3, 1, "Cruiser2");
		assertEquals("4,3,1,-1,-1,0,-1,", result);
	}

	@Test
	public void testRiempiCelleCase3_casoOvest() {
		// Test case for vertical boat placement with the last cell
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		//spedire è inizializzato a -1
		String result = serverSocket.riempiCelle(3, 3, 4, 3, 6, 1, "Aircraft1");
		assertEquals("3,3,1,-1,0,-1,-1,", result);
	}

	@Test
	public void testRiempiCelleCase4_casoEst() {
		// Test case for horizontal boat placement with the last cell
		ServerSocket serverSocket = ServerSocket.getInstance();
		serverSocket.inizializzaSquare();
		//spedire è inizializzato a -1
		String result = serverSocket.riempiCelle(3, 5, 2, 3, 1, 1, "Cruiser");
		assertEquals("3,2,1,-1,-1,-1,0,", result);
	}

}
