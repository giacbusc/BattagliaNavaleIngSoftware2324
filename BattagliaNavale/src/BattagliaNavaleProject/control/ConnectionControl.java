package BattagliaNavaleProject.control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.formModel.LoginModel;
import BattagliaNavaleProject.view.DoubleGameGridView;
import BattagliaNavaleProject.view.Observer;
import BattagliaNavaleProject.view.SchermataAttesaView;
import BattagliaNavaleProject.view.SchermataInizialeView;
import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;

public class ConnectionControl {
	private static String indirizzo;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	Observer obs;
	private DoubleGameGridControl DGGC;


	public ConnectionControl(SchermataAttesaControl sac, String userName, Observer obs, TornaMenuPrincipale tmp)
			throws IOException, InterruptedException {
	
		this.obs = obs;

		try {
			System.out.println("Connecting to th server");
			ZMQ.Socket socket = context.createSocket(SocketType.REQ);
			// Socket to talk to server
			socket.connect(indirizzo);
			
			/*
			 * instauro la connessione con il server; gestisco l'attesa del secondo client e l'avvio del gioco 
			 * inteso come apertura della griglia
			 */

			String sendMsg = userName;
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println(sendMsg);
			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

			if (rispostaMsg.equals("OK")) {
				sac.chiudi();
				sac = new SchermataAttesaControl("ATTESA POSIZIONAMENTO", userName, obs, tmp);
			} else if (rispostaMsg.equals("ERROR")) {
				// Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata
				// di attesa che qualcosa
				// è andato storto nella connessione
				System.out.println("C'è stato un errore nella connessione.");
			} else if (rispostaMsg.equals("DUPL")) {
				obs.update();
				sac.chiudi();
			}

			else if (rispostaMsg.equals("WAIT")) {

				while (true) {//resto qua fino a che non arrivo un messaggio dal server
					sendMsg = "attesa";
					System.out.println(sendMsg);
					socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
					byteMsg = socket.recv(0);
					System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
					rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

					if (rispostaMsg.equals("OK POS1")) {//inizio posizionamento del primo client 
						try {

							String filepath = "./music/Background_game_music.wav";
							SoundEffect se = new SoundEffect();

							se.playMusic2(filepath,false);
							DGGC = new DoubleGameGridControl(userName, tmp, obs);

							sac.chiudi();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							// dario
							e.printStackTrace();
						}
						break;
					} else if (rispostaMsg.equals("ERROR")) {
						// Qui dobbiamo chiamare una funzione che faccia uscire a video nella schermata
						// di attesa che qualcosa
						// è andato storto nella connessione
						System.out.println("C'è stato un errore nella connessione.");
					} else if (rispostaMsg.equals("DUPL")) {
						obs.update();
						sac.chiudi();
					}

				}

			}

		} finally {
		}
	}

	public Observer getObserver() {
		return obs;
	}

	public static void setIndirizzo(String ind) {
		// TODO Auto-generated method stub
		indirizzo = ind;
	}

}
