package BattagliaNavaleProject.control;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.TransferHandler;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.InfoBoat;
import BattagliaNavaleProject.BattagliaNavaleServer.Square;
import BattagliaNavaleProject.doubleGameGridModel.SoundEffect;
import BattagliaNavaleProject.view.AggiuntaListener;
import BattagliaNavaleProject.view.DoubleGameGridView;
import BattagliaNavaleProject.view.Observer;

public class DoubleGameGridControl implements MouseListener, AggiuntaListener{

	private static final int GRID_DIMENSION = 10;
	public TurniControl turni;
	boolean salta = false;
	public DoubleGameGridView grid;
	JPanel clickedPanel;
	boolean vai = true;
	JPanel[] arrayPanel = new JPanel[GRID_DIMENSION];
	private int clickcount = 0;
	boolean entra = false;
	int x;
	int y;
	private int ataconta = 3;
	public int[] arrayRisposta = new int[8];
	int primo = 0;
	int boatlenght;
	static String indirizzo;
	static ZContext context = new ZContext();
	static ZMQ.Socket socket = context.createSocket(SocketType.REQ);
	public String rispostamsg;
	private String username;
	String[] arraymsg = new String[3];
	int dim = 3;
	private TornaMenuPrincipale tmp;
	private Observer obs;
	// System.out.println("Connecting to th server");

	// Socket to talk to server

	public DoubleGameGridControl(String username, TornaMenuPrincipale tmp, Observer bs) throws IOException {
		
		this.obs = obs;
		this.tmp = tmp;
		this.username = username;
		grid = new DoubleGameGridView(username);
		grid.addMouseGriglia(this);
		grid.addMouseBarche(this);
		socket.connect(indirizzo);
	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()instanceof Square) {
			Square clickedSquare= (Square) e.getSource();
			if(clickedSquare.getName().equals("yourBoard")) {
				gestioneClick(e);
			}
			else if(clickedSquare.getName().equals("opponentBoard")) {
				try {
					System.out.println("Ho cliccato la opponent board ");
					turni.colpoClick(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource()instanceof JPanel) {
			gestioneClick(e);
		}
	}

	public void gestioneClick(MouseEvent e) {

		assegnaPanel();
		System.out.println("click");

		try {

			if (!(e.getSource() instanceof Square) || clickcount != 0 || entra == true) {
				clickcount++;
				entra = true;

				if (e.getSource() instanceof Square && clickcount == 1 && salta == false) {

					System.out.println(clickcount);

					Square clickedSquare = (Square) e.getSource();

					System.out.println("sono la square" + clickcount + clickedSquare.getx() + clickedSquare.gety());
					if (clickedSquare.getName().equals("yourBoard")) {

						arraymsg[1] = "" + clickedSquare.gety();
						arraymsg[0] = "" + clickedSquare.getx();
						System.out.println("barca" + arraymsg[2]);

						clickcount = 0;

						String msgserver = ("" + arraymsg[0] + "," + arraymsg[1] + "," + arraymsg[2]);
						System.out.println(msgserver);

						socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);
						System.out.println("ho inviato secondo msg");
						ricevi2msg(socket, x, y);

					}

				}

				if (e.getSource() instanceof JPanel && clickcount == 1) {

					clickedPanel = (JPanel) e.getSource();

					assegnabarca(clickedPanel);

					if (primo == 1) {
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								grid.addListenerCasellaVuota(this,i,j);

							}
						}

					}
				}

				if (!(e.getSource() instanceof Square) && clickcount == 2 && salta == false) {

					clickcount = 1;
				}

				if (e.getSource() instanceof Square && clickcount == 2) {
					Square clickedSquare = (Square) e.getSource();
					System.out.println("sono la square: " + clickcount + clickedSquare.getx() + clickedSquare.gety());
					if (clickedSquare.getName().equals("yourBoard")) {

						// clickedSquare.setBackground(Color.gray); //da togliere
						arraymsg[1] = "" + clickedSquare.gety();
						arraymsg[0] = "" + clickedSquare.getx();
						System.out.println("barca" + arraymsg[2]);

						x = clickedSquare.getx();
						y = clickedSquare.gety();
						clickcount = 0;

						String msgserver = ("" + arraymsg[0] + "," + arraymsg[1] + "," + arraymsg[2]);

						System.out.println("ho inviato primo msg: " + msgserver);

						socket.send(msgserver.getBytes(ZMQ.CHARSET), 0);

						ricevimsg(socket);

					}

				}

			}

		} catch (Exception e3) {
			e3.printStackTrace();
		}

	}

	public void assegnabarca(JPanel clickedPanel) {
		if (clickedPanel.getName().equals("Aircraft")) {
			arraymsg[2] = clickedPanel.getName();
			clickedPanel.setVisible(false);

			salta = false;

		} else if (clickedPanel.getName().equals("Destroyer1")) {
			clickedPanel.setVisible(false);

			arraymsg[2] = clickedPanel.getName();
			System.out.println("barca cliccata " + arraymsg[2]);
			salta = false;
		} else if (clickedPanel.getName().equals("Destroyer2")) {
			clickedPanel.setVisible(false);
			arraymsg[2] = clickedPanel.getName();
			System.out.println("barca cliccata " + arraymsg[2]);
			salta = false;
		} else if (clickedPanel.getName().equals("Cruiser1")) {
			clickedPanel.setVisible(false);
			arraymsg[2] = clickedPanel.getName();
			System.out.println("barca cliccata " + arraymsg[2]);
			salta = false;
		} else if (clickedPanel.getName().equals("Cruiser2")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + (clickedPanel.getName()));
			arraymsg[2] = (clickedPanel.getName());
			salta = false;
		}

		else if (clickedPanel.getName().equals("Cruiser3")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + (clickedPanel.getName()));
			arraymsg[2] = (clickedPanel.getName());
			salta = false;
		} else if (clickedPanel.getName().equals("Submarine1")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + clickedPanel.getName());
			arraymsg[2] = (clickedPanel.getName());
		} else if (clickedPanel.getName().equals("Submarine2")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + (clickedPanel.getName()));
			arraymsg[2] = (clickedPanel.getName());
		} else if (clickedPanel.getName().equals("Submarine3")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + (clickedPanel.getName()));
			arraymsg[2] = (clickedPanel.getName());
		} else if (clickedPanel.getName().equals("Submarine4")) {
			// cosa fare se clicco navi da 2
			clickedPanel.setVisible(false);
			System.out.println("barca cliccata " + (clickedPanel.getName()));
			arraymsg[2] = (clickedPanel.getName());

		}
	}

	public void ricevi2msg(ZMQ.Socket socket, int x, int y) throws InterruptedException, IOException {

		byte[] reply = socket.recv(0);// lo 0 blocca l'esecuzione della funzione finche non si riceve qualcosa
		rispostamsg = new String(reply, ZMQ.CHARSET);

		String[] arrayStringhe = rispostamsg.split(",");
		System.out.println();

		for (int i = 0; i < arrayStringhe.length; i++)
			arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
		System.out.println("Received msg 2 " + rispostamsg);

		// 0 1 2 3 4 5 6
		// x{ y St N E S O
		System.out.println("quello che ho mandato prima x: " + x + " quello che ricevo: " + arrayRisposta[0]);
		System.out.println("quello che ho mandato prima y: " + y + " quello che ricevo: " + arrayRisposta[1]);
		if (arrayRisposta[6] == 0) {
			while (y != arrayRisposta[1]) {
				colorebarca(grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]]);
				//grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setColor();
				arrayRisposta[1]--;
				grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);

			}

		}
		if (arrayRisposta[5] == 0) {
			while (x != arrayRisposta[0]) {
				colorebarca(grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]]);
				//grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setColor();
				arrayRisposta[0]++;
				grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);

			}
		}

		if (arrayRisposta[4] == 0) {
			while (y != arrayRisposta[1]) {
				colorebarca(grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]]);
				//grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setColor();
				arrayRisposta[1]++;
				grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);

			}
		}

		if (arrayRisposta[3] == 0) {
			while (x != arrayRisposta[0]) {
				colorebarca(grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]]);
				//grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setColor();
				arrayRisposta[0]--;
				grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setStato(1);

			}
		}
		colorabianco();
	}
	private void colorebarca(Square square) {
		String nome = arraymsg[2];
		for (InfoBoat boat : InfoBoat.values()) {
			if (boat.name().equalsIgnoreCase(nome)) {
				boatlenght = boat.getLunghezza();
			}
		}
		// TODO Auto-generated method stub
		if(boatlenght==1) {
			square.setColor(Color.decode("#00E6AC"));
		}
		if(boatlenght==2) {
			square.setColor(Color.decode("#9AFF6B"));
		}
		if(boatlenght==3) {
			square.setColor(Color.decode("#FFC20A"));
		}
		if(boatlenght==4) {
			square.setColor(Color.decode("#D147D1"));
		}
	}

	// tutto non cliccabile

	private void colorabianco() throws InterruptedException, IOException {

		// grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]].setBackground(Color.orange);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid.yourBoard[i][j].getColor() == Color.gray && grid.yourBoard[i][j].getStato() == 0) {

					grid.yourBoard[i][j].setReset();
				}
				grid.removeMouseListener(this, i, j);

			}

		}
		if (!(arraymsg[2].contains("Submarine"))) {
			aggiungiPanel();
		}

		Thread.sleep(3);
		if (arrayRisposta[7] == 1) {
			System.out.println("sono termianto, inizio a mandare ATA");
			terminaPosizionamento();

		}
	}

	public void ricevimsg(ZMQ.Socket socket) throws InterruptedException, IOException {

		// ZMQ.Socket socket = context.createSocket(SocketType.REQ);
		// Socket to talk to server
		primo = 1;
		byte[] reply = socket.recv(0);// lo 0 blocca l'esecuzione della funzione finche non si riceve qualcosa
		String rispostamsg = new String(reply, ZMQ.CHARSET);

		String[] arrayStringhe = rispostamsg.split(",");
		System.out.println();

		for (int i = 0; i < arrayStringhe.length; i++)
			arrayRisposta[i] = Integer.parseInt(arrayStringhe[i].trim());
		System.out.println("Received msg1" + rispostamsg);

		if (arrayRisposta[2] != -1) {
			colorebarca(grid.yourBoard[arrayRisposta[0]][arrayRisposta[1]]);

		}

		String nome = arraymsg[2];
		for (InfoBoat boat : InfoBoat.values()) {
			if (boat.name().equalsIgnoreCase(nome)) {
				boatlenght = boat.getLunghezza();
			}
	
		}
		if (boatlenght != 1) {
				togliPanel();
			}
			if (boatlenght == 1) {
				// clickcount--;
				salta = true;
				aggiungiPanel();
				if (arrayRisposta[7] == 1) {
					System.out.println("Mando ata dal receive");
					terminaPosizionamento();
				}

			}
		coloragrigio();
		errorePosizionamento();
		if (vai == true) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (grid.yourBoard[i][j].getColor() != Color.gray) {
						grid.removeMouseListener(this, i ,j);
					}

				}

			}

		}
		vai = true;
		salta = false;

	}

	public void errorePosizionamento() {
		if (arrayRisposta[3] != 0 && arrayRisposta[4] != 0 && arrayRisposta[5] != 0 && arrayRisposta[6] != 0
				&& !(arraymsg[2].contains("Submarine"))) {
			clickedPanel.setVisible(true);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					grid.removeMouseListener(this, i ,j);
				}

			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (grid.yourBoard[i][j].getStato()!=1) {
						grid.removeMouseListener(this, i, j);
					}

				}

			}
			aggiungiPanel();
			vai = false;
			salta = true;
		}
	}

	public void coloragrigio() {
		if (!(arraymsg[2].contains("Submarine"))) {
			togliPanel();
		}

		if (arrayRisposta[6] == 0) {

			for (int i = 1; i < boatlenght; i++) {
				if (grid.yourBoard[arrayRisposta[0]][arrayRisposta[1] - i].getStato() == 0) {
					grid.yourBoard[arrayRisposta[0]][arrayRisposta[1] - i].setGrigio();
				}
			}
		}
		if (arrayRisposta[5] == 0) {
			for (int i = 1; i < boatlenght; i++) {
				if (grid.yourBoard[arrayRisposta[0] + i][arrayRisposta[1]].getStato() == 0) {
					grid.yourBoard[arrayRisposta[0] + i][arrayRisposta[1]].setGrigio();
				}
			}
		}

		if (arrayRisposta[4] == 0) {
			for (int i = 1; i < boatlenght; i++) {
				if (grid.yourBoard[arrayRisposta[0]][arrayRisposta[1] + i].getStato() == 0) {
					grid.yourBoard[arrayRisposta[0]][arrayRisposta[1] + i].setGrigio();
				}
			}
		}

		if (arrayRisposta[3] == 0) {
			for (int i = 1; i < boatlenght; i++) {
				if (grid.yourBoard[arrayRisposta[0] - i][arrayRisposta[1]].getStato() == 0) {
					grid.yourBoard[arrayRisposta[0] - i][arrayRisposta[1]].setGrigio();
				}
			}
		}
	}

	public void aggiungiPanel() {
		grid.addMouseBarche(this);
	}

	public void togliPanel() {
		grid.removeMouseBarche(this);
	}

	private void assegnaPanel() {

		JPanel[] vettore;
		vettore = grid.getPanel();

		for (int i = 0; i < 10; i++) {
			if (vettore[i].getName().equals("0")) {
				vettore[i].setName("Aircraft");
				arrayPanel[0] = vettore[i];

			} else if (vettore[i].getName().equals("1")) {

				vettore[i].setName("Destroyer1");
				arrayPanel[1] = vettore[i];

			} else if (vettore[i].getName().equals("2")) {
				vettore[i].setName("Destroyer2");
				arrayPanel[2] = vettore[i];

			} else if (vettore[i].getName().equals("3")) {

				vettore[i].setName("Cruiser1");
				arrayPanel[3] = vettore[i];

			} else if (vettore[i].getName().equals("4")) {

				vettore[i].setName("Cruiser2");
				arrayPanel[4] = vettore[i];

			} else if (vettore[i].getName().equals("5")) {
				vettore[i].setName("Cruiser3");
				arrayPanel[5] = vettore[i];

			} else if (vettore[i].getName().equals("6")) {
				vettore[i].setName("Submarine1");
				arrayPanel[6] = vettore[i];

			} else if (vettore[i].getName().equals("7")) {
				vettore[i].setName("Submarine2");
				arrayPanel[7] = vettore[i];

			} else if (vettore[i].getName().equals("8")) {
				vettore[i].setName("Submarine3");
				arrayPanel[8] = vettore[i];

			} else if (vettore[i].getName().equals("9")) {

				vettore[i].setName("Submarine4");
				arrayPanel[9] = vettore[i];

			}
		}
		grid.setPanel(vettore);
	}

	public static String getIndirizzo() {
		return indirizzo;

	}

	public static void setIndirizzo(String indirizzo) {
		DoubleGameGridControl.indirizzo = indirizzo;
	}

	public void terminaPosizionamento() throws InterruptedException, IOException {

		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				// Esegui le operazioni di connessione qui
				boolean r = true;
				do {
					ataconta++;
					grid.waitPanel.setVisible(true);
					grid.waitPanel.setForeground(Color.DARK_GRAY);
					Thread.sleep(500);
					String sendMsg = "ATA"+ataconta;
					socket.send(sendMsg.getBytes(ZMQ.CHARSET), ZMQ.DONTWAIT);
					System.out.println("Inviato " + sendMsg);

					byte[] byteMsg = socket.recv(0);
					System.out.println("Ricevuto " + new String(byteMsg, ZMQ.CHARSET) + " ");
					String rispostaMsg = new String(byteMsg, ZMQ.CHARSET);

					if (rispostaMsg.equals("GIOCA")) {
						String filepath = "./music/Background_game_music.wav";
						SoundEffect se = new SoundEffect();
						se.playMusic(filepath,false,false);
						grid.waitPanel.setVisible(false);
						

						r = false;
					}

				} while (r == true);

				creazioneTurni();
				
				System.out.println("inizio turno");

				grid.turnoPanel.setVisible(true);
				grid.shipsPanel.setBackground(Color.decode("#659feb"));
				turni.turno();
				
				return null;
			}
		};
		worker.execute();

		// grid.waitPanelCreation();

	}
	
	

	public void creazioneTurni()
	{
		turni = new TurniControl(indirizzo, grid, this, tmp, obs);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListenerOpponentGriglia(DoubleGameGridView v, int i, int j) {
		// TODO Auto-generated method stub
		v.addListenerOpponentGriglia(this, i, j);
	}

	@Override
	public void removeMouseListener(DoubleGameGridView v,int i, int j) {
		// TODO Auto-generated method stub
		v.removeMouseListener(this,i,j);
	}

	@Override
	public void removeListenerOpponent(DoubleGameGridView v, int i, int j) {
		// TODO Auto-generated method stub
		v.removeListenerOpponent(this, i, j);
	}
}
