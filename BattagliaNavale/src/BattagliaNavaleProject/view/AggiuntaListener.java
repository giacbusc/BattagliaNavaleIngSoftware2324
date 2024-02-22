package BattagliaNavaleProject.view;

import java.awt.event.MouseListener;

public interface AggiuntaListener {
	public void addListenerOpponentGriglia(DoubleGameGridView v, int i, int j);
	public void removeMouseListener(DoubleGameGridView v, int i, int j);
	public void removeListenerOpponent(DoubleGameGridView v, int i, int j);
}
