package BattagliaNavaleProject.BattagliaNavaleServer;

import java.util.ArrayList;

import org.zeromq.ZMQ;

import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.Square;

public interface InterfacciaServerPartita {
	//ServerSocket getInstanceServer();
	ZMQ.Socket getSocketServer();
	Square[][] getPlayer1();
	Square[][] getPlayer2();
	ArrayList<String> getConnectedclients();
}
