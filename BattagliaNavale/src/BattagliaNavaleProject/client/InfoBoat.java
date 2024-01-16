package BattagliaNavaleProject.client;

public enum InfoBoat {
	Aircraft(4),
	Destroyer(3),
	Cruiser(2),
	Submarine(1);
	
	private final int lunghezza;
	
	InfoBoat(int lunghezza)
	{
		this.lunghezza = lunghezza;
	}
	
	public int getLunghezza() {
        return lunghezza;
    }
}
