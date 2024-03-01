package BattagliaNavaleProject.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.SwingWorker;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.Square;
import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.AggiuntaListener;
import BattagliaNavaleProject.view.DoubleGameGridView;
import BattagliaNavaleProject.view.FinePartitaView;
import BattagliaNavaleProject.view.Observer;

public class TurniControl {
	DoubleGameGridView DGGV;
	final int GRID_DIMENSION = 10;
	private String indirizzo;
	private int stato;
	private int x;
	private int y;
	private boolean r;
	private int lunghezza;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	int[] arraymsg = new int[2];
	int[] arrayRisposta = new int[4];
	private AggiuntaListener al;
	private TornaMenuPrincipale tmp;
	private int miaoconta;
	private Observer obs;

	public TurniControl(String indirizzo, DoubleGameGridView DGGV, AggiuntaListener al, TornaMenuPrincipale tmp,
			Observer obs) {
		// TODO Auto-generated constructor stub
		this.obs = obs;
		this.tmp = tmp;
		this.indirizzo = indirizzo;
		this.DGGV = DGGV;
		this.al = al;

		socket.connect(indirizzo);
	}

	/*
	 * 0 libero bianco 1 occupato giallo 2 colpito 3 affondato diventa 4 acqua
	 * diventa azzurro per gli stati
	 * 
	 * array risposta x y stato lunghezza N E S O
	 */
	public void turno() {
		miaoconta = 0;
		// rendo la griglia cliccabile
		for (int i = 0; i < GRID_DIMENSION; i++) {
			for (int j = 0; j < GRID_DIMENSION; j++) {
				al.removeMouseListener(DGGV, i, j);
				if (DGGV.opponentBoard[i][j].getBackground() == Color.white) {
					al.addListenerOpponentGriglia(DGGV, i, j);
				}

			}
		}
		// aspetto che sia cliccata
	}

	public void colpoClick(MouseEvent e) throws IOException {

		if (e.getSource() instanceof Square) {

			Square clickedSquare = (Square) e.getSource();

			arraymsg[1] = clickedSquare.gety();
			arraymsg[0] = clickedSquare.getx();
			String msgserver = ("" + arraymsg[0] + "," + arraymsg[1] + ",");
			System.out.println("Inviato " + msgserver);

			socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
			ricevi();

		}
	}

	private void ricevi() throws IOException {
		// TODO Auto-generated method stub
		//
		byte[] byteMsg = socket.recv(0);
		System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
		String rispostamsg = new String(byteMsg, ZMQ.CHARSET);

		String[] arrayStringhe = rispostamsg.split(",");

		for (int i = 0; i < arrayStringhe.length; i++)
			arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
		System.out.println("Received msg  " + rispostamsg);

		stato = arrayRisposta[2];
		lunghezza = arrayRisposta[3];
		x = arrayRisposta[0];
		y = arrayRisposta[1];
		controllastato();

	}

	private void controllastato() {
		// TODO Auto-generated method stub
		if (stato == 2) {
			DGGV.opponentBoard[x][y].setColpito();
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					DGGV.opponentBoard[x][y].setColpito();
					String filepath = "./music/ColpitaSound.wav";
					SoundEffect se = new SoundEffect();
					se.playMusic(filepath);

					try {
						cicloattesa();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

				}
			};
			worker.execute();
		}
		if (stato == 3) {

			// DGGV.opponentBoard[x][y].setAffondato();

			verificaLunghezza();

		} else if (stato == 4) {
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					DGGV.opponentBoard[x][y].setAcqua();

					String filepath = "./music/waterSplash.wav";
					SoundEffect se = new SoundEffect();
					se.playMusic(filepath);
					DGGV.turnoPanel.setVisible(false);
					try {
						cicloattesa();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;

				}
			};
			worker.execute();
		}
	}

	private void verificaLunghezza() {
		// TODO Auto-generated method stub
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 0; i < lunghezza; i++) {
					String sendMsg = "AFFONDATO";
					DGGV.createIcon(arrayRisposta[0], arrayRisposta[0]);
					socket.send(sendMsg.getBytes(ZMQ.CHARSET), ZMQ.DONTWAIT);
					System.out.println(sendMsg);

					byte[] byteMsg = socket.recv(0);
					System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
					String rispostamsg = new String(byteMsg, ZMQ.CHARSET);

					String[] arrayStringhe = rispostamsg.split(",");
					System.out.println();

					for (int i1 = 0; i1 < arrayStringhe.length; i1++)
						arrayRisposta[i1] = Integer.parseInt(arrayStringhe[i1].trim());
					System.out.println("Received msg 2 " + rispostamsg);

					lunghezza = arrayRisposta[3];
					stato = arrayRisposta[2];
					if (stato == 5) {
						String filepath = "./music/Background_game_music.wav";
						SoundEffect se = new SoundEffect();
						se.playMusic2(filepath, false);
						String filepath2 = "./music/finalWin.wav";
						SoundEffect se2 = new SoundEffect();
						se2.playMusic(filepath2);
						FinePartitaControl fsv = new FinePartitaControl(DGGV.getUsername(), "HAI VINTO", tmp, obs);

						DGGV.dispose();
					}
					x = arrayRisposta[0];
					y = arrayRisposta[1];
					DGGV.opponentBoard[x][y].setAffondato();

				}

				String filepath = "./music/AffondataSound.wav";
				SoundEffect se = new SoundEffect();
				se.playMusic(filepath);

				try {
					Thread.sleep(100);
					cicloattesa();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;

			}
		};
		worker.execute();
	}

	private void cicloattesa() throws InterruptedException, IOException, SQLException {
		// TODO Auto-generated method stub
		DGGV.turnoPanel.setVisible(false);
		boolean r = true;
		do {
			toglilistener();
			Thread.sleep(500);
			String sendMsg = "MIAO" + miaoconta;
			DGGV.shipsPanel.setBackground(Color.decode("#5C99D6"));

			socket.send(sendMsg.getBytes(ZMQ.CHARSET), 0);
			System.out.println("inviata attesa del turno " + sendMsg);
			miaoconta++;
			byte[] byteMsg = socket.recv(0);
			System.out.println("Received " + new String(byteMsg, ZMQ.CHARSET) + " ");
			String rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

			if (rispostaMsg.equals("GIOCA")) {

				DGGV.turnoPanel.setVisible(true);
				DGGV.shipsPanel.setBackground(Color.decode("#659feb"));
				turno();
				r = false;
			}

			if (rispostaMsg.equals("HAI PERSO")) {
				r = false;

				DGGV.dispose();
				String filepath = "./music/Background_game_music.wav";
				SoundEffect se = new SoundEffect();
				se.playMusic2(filepath, false);
				filepath = "./music/gameover.wav";
				se = new SoundEffect();
				se.playMusic(filepath);
				FinePartitaControl sfp = new FinePartitaControl(DGGV.getUsername(), "HAI PERSO", tmp, obs);

			}

		} while (r == true);

	}

	void toglilistener() {
		// TODO Auto-generated method stub
		System.out.println("tolti");
		for (int i = 0; i < GRID_DIMENSION; i++) {
			for (int j = 0; j < GRID_DIMENSION; j++) {

				al.removeListenerOpponent(DGGV, i, j);
			}
		}
	}
}
