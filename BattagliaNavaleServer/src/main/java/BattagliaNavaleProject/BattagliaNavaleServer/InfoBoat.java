package BattagliaNavaleProject.BattagliaNavaleServer;

public enum InfoBoat {
	Aircraft(4), Destroyer1(3), Destroyer2(3), Cruiser1(2), Cruiser2(2), Cruiser3(2), Submarine1(1), Submarine2(1),
	Submarine3(1), Submarine4(1);

	private final int lunghezza;

	InfoBoat(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getLunghezza() {
		return lunghezza;
	}
}
