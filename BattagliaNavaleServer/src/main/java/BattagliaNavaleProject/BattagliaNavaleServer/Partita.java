package BattagliaNavaleProject.BattagliaNavaleServer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.database.ConnectionDb;

public class Partita {
	ServerSocket s;
	int turno;
	private String[] spedire = new String[4];
	Square[][] player1;
	Square[][] player2;
	int MAX_LENGTH = 10;
	boolean INVIATO = false;
	public static int contaBarcheP1 = 0, contaBarcheP2 = 0;
	private ArrayList<String> username = new ArrayList<>();
	public String mexprecedente = "MIAO-1";
	private boolean finito=false;
	
	public void inizioGioco() {
		
		turno = 1;
		ServerSocket s = ServerSocket.getInstance();
		ZMQ.Socket socketServer = s.getSocketServer();
		player1 = s.getPlayer1();
		player2 = s.getPlayer2();

		// stampaGriglia(1);

		while (finito==false) {
			System.out.println("p1: " + contaBarcheP1 + " p2: " + contaBarcheP2);
			byte[] reply = socketServer.recv(0);
			String request = new String(reply, ZMQ.CHARSET);
			System.out.println("Messaggio ricevuto: " + request);
			// BOOLEANO CHE ANDRA SETTATO PER RISPONDERGLI ANZICHE CON ATA CON GIOCA PER
			// RISVEGLIARLO
			
			

			String mexATA = request.substring(0, 3);
			

			if (mexATA.equals("ATA")) 
			{
				if(finito==true)
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
			} else if (mexATA.equals("MIA")) 
			{
				if(finito==true)
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

			if (!request.equals("")) 
			{
				if(finito==true)
					break;
				String[] mexSplit = request.split(",");
				String x = mexSplit[0];
				String y = mexSplit[1];
				int xpos = Integer.valueOf(x).intValue();
				int ypos = Integer.valueOf(y).intValue();
				// STA GIOCANDO IL PLAYER CHE HA POSIZIONATO LE BARCHE PER PRIMO
				if (turno == 1) {
					System.out.println("turno nell'if" + turno);
					System.out.println("conta barche p1: " + contaBarcheP1);
					for (int k = 0; k < spedire.length; k++) {
						spedire[k] = "-1";
					}
					if (player2[xpos][ypos].getStato() == 0) {
						spedire[0] = x;
						spedire[1] = y;
						spedire[2] = "4"; // ha colpito l'acqua
						player2[xpos][ypos].setStato(4);
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
							INVIATO = true;
							spedireMex(spedire);
						} else { // è affondata
							contaBarcheP1++;
							player2[xpos][ypos].setStato(3);
							String nomeBarcaColpita = player2[xpos][ypos].getNome();
							InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarcaColpita);
							int l = boat.getLunghezza();

							if (contaBarcheP1 == 10)
								l = l + 1;

							int contaAffondati = 0;
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
										spedire[0] = String.valueOf(i);
										spedire[1] = String.valueOf(j);
										spedire[2] = "3";
										spedire[3] = String.valueOf(l);
										contaAffondati++;
										spedireMex(spedire);
										// contaAffondati++;

										// RICEZIONE DELL'AFFONDATO
										if (contaAffondati < l) {
											byte[] replyAffondato = socketServer.recv(0);
											String requestAffondato = new String(replyAffondato, ZMQ.CHARSET);
											System.out.println("Messaggio ricevuto: " + requestAffondato);
										}
										System.out
												.println("affondati: " + contaAffondati + " barche: " + contaBarcheP1);
										if (contaAffondati == (l - 1) && contaBarcheP1 == 10) {
											System.out.println("ha vinto p1 ");
											spedire[0] = "-1";
											spedire[1] = "-1";
											spedire[2] = "5"; // lo stato a 5 indica che il player ha vinto!
											spedire[3] = String.valueOf(l);
											spedireMex(spedire);
											finito=true;
											break;
										}
									}

								}
							}

							INVIATO = true;
						}

					}
					// STA GIOCANDO IL PLAYER CHE HA POSIZIONATO LE BARCHE PER SECONDO --> il P2
				} else if (turno == 2) {
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
							INVIATO = true;
							spedireMex(spedire);
						} else { // è affondata
							contaBarcheP2++;
							player1[xpos][ypos].setStato(3);
							String nomeBarcaColpita = player1[xpos][ypos].getNome();
							InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarcaColpita);
							int l = boat.getLunghezza();

							if (contaBarcheP2 == 10)
								l = l + 1;

							int contaAffondati = 0;

							for (int i = 0; i < MAX_LENGTH; i++) {
								for (int j = 0; j < MAX_LENGTH; j++) {
									if (player1[i][j].getNome().equals(nomeBarcaColpita)) {
										player1[i][j].setStato(3);
										spedire[0] = String.valueOf(i);
										spedire[1] = String.valueOf(j);
										spedire[2] = "3";
										spedire[3] = String.valueOf(l);
										contaAffondati++;
										spedireMex(spedire);

										// RICEZIONE DELL'AFFONDATO
										if (contaAffondati < l) {
											byte[] replyAffondato = socketServer.recv(0);
											String requestAffondato = new String(replyAffondato, ZMQ.CHARSET);
											System.out.println("Messaggio ricevuto: " + requestAffondato);

										}
										System.out.println("affondati: " + contaAffondati + " barche: " + contaBarcheP2);
										if (contaAffondati == (l - 1) && contaBarcheP2 == 10) {
											System.out.println("ha vinto p2 ");
											spedire[0] = "-1";
											spedire[1] = "-1";
											spedire[2] = "5"; // lo stato a 5 indica che il player2 ha vinto!
											spedire[3] = String.valueOf(l);
											spedireMex(spedire);
											finito=true;
											break;
										}

									}
								}
							}
							INVIATO = true;
						}

					}
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
		username = s.getConnectedclients();

		if (contaBarcheP1 == 10) {
			registraVincita(username.get(0), username.get(1));
		} else if (contaBarcheP2 == 10) {
			registraVincita(username.get(1), username.get(0));
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

	public void registraVincita(String usernameVincitore, String usernameSconfitto) {
		String sql = "INSERT INTO PARTITA VALUES (?,?,?)";

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
