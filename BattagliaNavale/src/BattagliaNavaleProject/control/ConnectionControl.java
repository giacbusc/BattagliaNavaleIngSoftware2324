package BattagliaNavaleProject.control;

import java.io.IOException;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.view.Observer;
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

			String sendMsg = userName;
			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println(sendMsg);
			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

			if (rispostaMsg.equals("OK")) {
				//Se si riceve dal server OK viene aperta una schermata di attesa posizionamento
				sac.chiudi();
				sac = new SchermataAttesaControl("ATTESA POSIZIONAMENTO", userName, obs, tmp);
			} else if (rispostaMsg.equals("ERROR")) {
				System.out.println("C'è stato un errore nella connessione.");
			} else if (rispostaMsg.equals("DUPL")) {
				//Se si riceve dal server DUPL viene aperta una nuova schermata iniziale
				//DUPL viene inviato dal server nel momento in cui due utenti effettuano il login 
				//con lo stesso account
				obs.update();
				sac.chiudi();
			}

			else if (rispostaMsg.equals("WAIT")) {
				//Se si riceve WAIT l'utente aspetterà che un altro utente si connetta al server per giocare
				while (true) {
					sendMsg = "attesa";
					System.out.println(sendMsg);
					socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
					byteMsg = socket.recv(0);
					System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
					rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

					if (rispostaMsg.equals("OK POS1")) {
						try {
							//Nel momento in cui si riceve OK POS1 significa che il secondo utente si è connesso
							//perciò al primo utente si aprirà la griglia per posizionare le navi
							String filepath = "./music/Background_game_music.wav";
							SoundEffect se = new SoundEffect();

							se.playMusic2(filepath,false);
							DGGC = new DoubleGameGridControl(userName, tmp, obs);

							sac.chiudi();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					} else if (rispostaMsg.equals("ERROR")) {
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
