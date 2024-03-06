package BattagliaNavaleProject.BattagliaNavaleServer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.InfoBoat;
import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.Square;
import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;

public class Partita {
	int turno;
	private String[] spedire = new String[6];
	Square[][] player1;
	Square[][] player2;
	int MAX_LENGTH = 10;
	boolean INVIATO = false;
	public static int contaBarcheP1 = 0, contaBarcheP2 = 0;
	private ArrayList<String> username = new ArrayList<>();
	public String mexprecedente = "MIAO-1";
	private boolean finito = false;
	private InterfacciaServerPartita isp;

	public Partita(InterfacciaServerPartita isp) {
		this.isp = isp;
	}

	public void inizioGioco() {

		turno = 1;
		ZMQ.Socket socketServer = isp.getSocketServer();
		player1 = isp.getPlayer1();
		player2 = isp.getPlayer2();
		// stampaGriglia(1);

		while (finito == false) {
			System.out.println("p1: " + contaBarcheP1 + " p2: " + contaBarcheP2);
			byte[] reply = socketServer.recv(0);
			String request = new String(reply, ZMQ.CHARSET);
			System.out.println("Messaggio ricevuto: " + request);
			// BOOLEANO CHE ANDRA SETTATO PER RISPONDERGLI ANZICHE CON ATA CON GIOCA PER
			// RISVEGLIARLO

			String mexATA = request.substring(0, 3);

			if (mexATA.equals("ATA")) {
				if (finito == true)
					break;

				if (INVIATO == true) {
					System.out.println("Stai passando il turno!");
					String responseMessage = "GIOCA";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
					turno = 2;
					System.out.println("turno " + turno);

					INVIATO = false;
					continue;
				}

				if (INVIATO == false) {
					String responseMessage = "ATA";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
					continue;
				}
			} else if (mexATA.equals("MIA")) {
				if (finito == true)
					break;

				String messaggioMiao = request.substring(0, 4);
				String numeroMiao = request.substring(4);
				int numattuale = Integer.parseInt(numeroMiao);

				String numeroMIAOprecedente = mexprecedente.substring(4);
				int numprecedente = Integer.parseInt(numeroMIAOprecedente);

				System.out.println("miao attuale: " + numattuale);
				System.out.println("miao precedente: " + numprecedente);
				if (messaggioMiao.equals("MIAO") && INVIATO == true) {
					if (numattuale > numprecedente) {
						String responseMessage = "GIOCA";
						socketServer.send(responseMessage.getBytes(), 0);
						System.out.println("Inviato: " + responseMessage);
						if (turno == 2) {
							turno = 1;
							System.out.println("turno " + turno);
						} else {
							turno = 2;
							System.out.println("turno " + turno);
						}

						System.out.println("turno " + turno);
						INVIATO = false;
						continue;
					} else {
						String responseMessage = "MIAO";
						socketServer.send(responseMessage.getBytes(), 0);
						System.out.println("Inviato: " + responseMessage);
						INVIATO = true;
						continue;
					}

				}

				if (messaggioMiao.equals("MIAO") && INVIATO == false) {
					mexprecedente = request;
					String responseMessage = "MIAO";
					socketServer.send(responseMessage.getBytes(), 0);
					System.out.println("Inviato: " + responseMessage);
					continue;
				}

			}

			// SPACCHETTAMENTO MIAO ricevuto per ultimo

			if (!request.equals("")) {
				if (finito == true)
					break;
				String[] mexSplit = request.split(",");
				String x = mexSplit[0];
				String y = mexSplit[1];
				int xpos = Integer.valueOf(x).intValue();
				int ypos = Integer.valueOf(y).intValue();
				// STA GIOCANDO IL PLAYER CHE HA POSIZIONATO LE BARCHE PER PRIMO
				if (turno == 1) {
					controlloT1(socketServer, xpos, ypos, x, y);
				}
				// STA GIOCANDO IL PLAYER CHE HA POSIZIONATO LE BARCHE PER SECONDO --> il P2
				else if (turno == 2) {
					controlloT2(socketServer, xpos, ypos, x, y);
				}

			}
		}

		byte[] reply = socketServer.recv(0);
		String request = new String(reply, ZMQ.CHARSET);
		System.out.println("Messaggio ricevuto: " + request);
		String messaggioMiao = request.substring(0, 4);
		if (messaggioMiao.equals("MIAO")) {
			String responseMessage = "HAI PERSO";
			socketServer.send(responseMessage.getBytes(), 0);
			System.out.println("Inviato: " + responseMessage);
		}
		username = isp.getConnectedclients();

		if (contaBarcheP1 == 10) {
			registraVincita(username.get(0), username.get(1));
		} else if (contaBarcheP2 == 10) {
			registraVincita(username.get(1), username.get(0));
		}

	}

	private void controlloT1(Socket socketServer, int xpos, int ypos, String x, String y) {
		// System.out.println("turno nell'if" + turno);
		// System.out.println("conta barche p1: " + contaBarcheP1);
		for (int k = 0; k < spedire.length; k++) {
			spedire[k] = "-1";
		}
		if (player2[xpos][ypos].getStato() == 0) {
			spedire[0] = x;
			spedire[1] = y;
			spedire[2] = "4"; // ha colpito l'acqua
			player2[xpos][ypos].setStato(4);
			spedire[4] = Integer.toString(contaBarcheP1);
			spedire[5] = Integer.toString(contaBarcheP2);
			INVIATO = true;
			spedireMex(spedire);

		} else {

			if (player2[xpos][ypos].getStato() == 1 && controllaAffondata(player2, xpos, ypos) == false) { // è
																											// stata
																											// solo
																											// colpita
				spedire[0] = x;
				spedire[1] = y;
				spedire[2] = "2";
				player2[xpos][ypos].setStato(2);
				spedire[4] = Integer.toString(contaBarcheP1);
				spedire[5] = Integer.toString(contaBarcheP2);
				INVIATO = true;
				spedireMex(spedire);
			} else { // è affondata
				contaBarcheP1++;
				String nomeBarcaColpita = player2[xpos][ypos].getNome();
				InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarcaColpita);
				int l = boat.getLunghezza();
				if (contaBarcheP1 == 10)
					l = l + 1;

				spedire[0] = String.valueOf(xpos);
				spedire[1] = String.valueOf(ypos);
				spedire[2] = "3";
				spedire[3] = String.valueOf(l);
				player2[xpos][ypos].setStato(3);
				spedire[4] = Integer.toString(contaBarcheP1);
				spedire[5] = Integer.toString(contaBarcheP2);
				spedireMex(spedire);

				ArrayList<String[]> celleAffondate = new ArrayList<String[]>();
				// PER INDICARE LA VINCITA PRIMA SI METTONO TUTTE LE BARCHE SETTATE NELLA
				// GRIGLIA DOPODICHE
				// SI VA A:
				// 1) aumentare la lunghezza di 1 della barca cosicchè si possa mandare un
				// ulteriore messaggio
				// 2) si invia un nuovo stato speciale che risulta come 5 che indica la vittoria
				// del player

				for (int i = 0; i < MAX_LENGTH; i++) {
					for (int j = 0; j < MAX_LENGTH; j++) {
						if (player2[i][j].getNome().equals(nomeBarcaColpita)) {

								player2[i][j].setStato(3);
								String[] martin = new String[4];
								martin[0] = String.valueOf(i);
								martin[1] = String.valueOf(j);
								martin[2] = "3";
								martin[3] = String.valueOf(l);

								celleAffondate.add(martin);
							
						}
					}
				}
				
				 for (String[] array : celleAffondate) {
			            System.out.print("[ ");
			            for (String str : array) {
			                System.out.print(str + " ");
			            }
			            System.out.println("]");
			        }
				 
				int contaAffondati = -1;
				
				System.out.println("grandezza arraylist: " + celleAffondate.size());
				String martin[] = celleAffondate.get(0);
				
				System.out.println("celle affondate arraylist in posizione 0" + martin.toString());
				while (true) {
					byte[] replyAffondato = socketServer.recv(0);
					String requestAffondato = new String(replyAffondato, ZMQ.CHARSET);
					System.out.println("Messaggio ricevuto durante l'affondato: " + requestAffondato);
					String checkCorrettezzaSerie = requestAffondato.substring(0, 4);
					if (checkCorrettezzaSerie.equals("AFFO")) 
					{
						contaAffondati++;

						if (contaAffondati == (l - 1) && contaBarcheP1 == 10) {
							System.out.println("ha vinto p2 ");
							spedire[0] = "-1";
							spedire[1] = "-1";
							spedire[2] = "5"; // lo stato a 5 indica che il player2 ha vinto!
							spedire[3] = String.valueOf(l);
							spedireMex(spedire);
							finito = true;
							break;
						}
						spedireMex(celleAffondate.get(contaAffondati));

						if (contaAffondati == celleAffondate.size()-1 && contaBarcheP1!=10) {
							INVIATO = true;
							break;
						}
					} else if (checkCorrettezzaSerie.equals("MIAO")) {
						String responseMessage = "MIAO";
						socketServer.send(responseMessage.getBytes(), ZMQ.DONTWAIT);
						System.out.println("Inviato IL MIAO DEL MARTIN: " + responseMessage);
					}

				}

				System.out.println("affondati: " + contaAffondati + " barche: " + contaBarcheP1);

			}

		}
	}

	private void controlloT2(Socket socketServer, int xpos, int ypos, String x, String y) {
		System.out.println("turno nell'if " + turno);
		System.out.println("conta barche p2: " + contaBarcheP2);
		for (int k = 0; k < spedire.length; k++) {
			spedire[k] = "-1";
		}
		if (player1[xpos][ypos].getStato() == 0) {
			spedire[0] = x;
			spedire[1] = y;
			spedire[2] = "4"; // ha colpito l'acqua
			player1[xpos][ypos].setStato(4);
			spedire[4] = Integer.toString(contaBarcheP2);
			spedire[5] = Integer.toString(contaBarcheP1);
			INVIATO = true;
			spedireMex(spedire);

		} else {// colpito o affondato

			if (player1[xpos][ypos].getStato() == 1 && controllaAffondata(player1, xpos, ypos) == false) { // è
																											// stata
																											// solo
																											// colpita
				spedire[0] = x;
				spedire[1] = y;
				spedire[2] = "2";
				player1[xpos][ypos].setStato(2);
				spedire[4] = Integer.toString(contaBarcheP2);
				spedire[5] = Integer.toString(contaBarcheP1);
				INVIATO = true;
				spedireMex(spedire);
			} else { // è affondata
				contaBarcheP2++;
				String nomeBarcaColpita = player1[xpos][ypos].getNome();
				InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarcaColpita);
				int l = boat.getLunghezza();
				if (contaBarcheP2 == 10)
					l = l + 1;

				spedire[0] = String.valueOf(xpos);
				spedire[1] = String.valueOf(ypos);
				spedire[2] = "3";
				spedire[3] = String.valueOf(l);
				player1[xpos][ypos].setStato(3);
				spedire[4] = Integer.toString(contaBarcheP2);
				spedire[5] = Integer.toString(contaBarcheP1);
				spedireMex(spedire);

				ArrayList<String[]> celleAffondate = new ArrayList<String[]>();
				for (int i = 0; i < MAX_LENGTH; i++) {
					for (int j = 0; j < MAX_LENGTH; j++) {
						if (player1[i][j].getNome().equals(nomeBarcaColpita)) {

								player1[i][j].setStato(3);
								String[] martin = new String[4];
								martin[0] = String.valueOf(i);
								martin[1] = String.valueOf(j);
								martin[2] = "3";
								martin[3] = String.valueOf(l);

								celleAffondate.add(martin);
							
						}
					}
				}
				
				 for (String[] array : celleAffondate) {
			            System.out.print("[ ");
			            for (String str : array) {
			                System.out.print(str + " ");
			            }
			            System.out.println("]");
			        }
				
				int contaAffondati = -1;
				System.out.println("grandezza arraylist: " + celleAffondate.size());
				String martin[] = celleAffondate.get(0);
				System.out.println("celle affondate arraylist in posizione 0" + martin.toString());

				while (true) {
					byte[] replyAffondato = socketServer.recv(0);
					String requestAffondato = new String(replyAffondato, ZMQ.CHARSET);
					System.out.println("Messaggio ricevuto durante l'affondato: " + requestAffondato);
					String checkCorrettezzaSerie = requestAffondato.substring(0, 4);
					
					if (checkCorrettezzaSerie.equals("AFFO")) 
					{
						contaAffondati++;

						if (contaAffondati == (l - 1) && contaBarcheP2 == 10) {
							System.out.println("ha vinto p2 ");
							spedire[0] = "-1";
							spedire[1] = "-1";
							spedire[2] = "5"; // lo stato a 5 indica che il player2 ha vinto!
							spedire[3] = String.valueOf(l);
							spedireMex(spedire);
							finito = true;
							break;
						}
						spedireMex(celleAffondate.get(contaAffondati));

						if (contaAffondati == celleAffondate.size()-1 && contaBarcheP2!=10) {
							INVIATO = true;
							break;
						}
					} else if (checkCorrettezzaSerie.equals("MIAO")) {
						String responseMessage = "MIAO";
						socketServer.send(responseMessage.getBytes(),  ZMQ.DONTWAIT);
						System.out.println("Inviato IL MIAO DEL MARTIN: " + responseMessage);
					}

				}


				// RICEZIONE DELL'AFFONDATO

				System.out.println("affondati: " + contaAffondati + " barche: " + contaBarcheP2);


			}

		}
	}

	public boolean controllaAffondata(Square[][] player, int x, int y) { // nome della barca da cercare
		String nomeBarcaColpita = player[x][y].getNome();
		int conta = 0;
		InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarcaColpita);
		int l = boat.getLunghezza();
		player[x][y].setStato(2);
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				if (player[i][j].getNome().equals(nomeBarcaColpita)) {
					if (player[i][j].getStato() == 2)
						conta++;
				}
			}
		}

		if (conta == l) // sono state colpite tutte le celle
		{
			return true;
		}
		return false;
	}

	public void spedireMex(String[] spedire) {
		ZMQ.Socket socketServer = isp.getSocketServer();
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
			System.out.println(); // Vai a capo dopo ogni riga2
		}
	}

	public void registraVincita(String usernameVincitore, String usernameSconfitto) {
		String sql = "INSERT INTO PARTITA (GIOCATORE1,GIOCATORE2,VINCITORE) VALUES (?,?,?)";

		ConnectionDb conn = new ConnectionDb();
		PreparedStatement pstmt;
		try {
			pstmt = conn.getConnectionServer().prepareStatement(sql);
			pstmt.setString(1, usernameVincitore);
			pstmt.setString(2, usernameSconfitto);
			pstmt.setString(3, usernameVincitore);
			boolean resultSet = pstmt.execute();
			System.out.println("VINCITA AGGIUNTA CON SUCCESSO");

			conn.closeConnectionServer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
