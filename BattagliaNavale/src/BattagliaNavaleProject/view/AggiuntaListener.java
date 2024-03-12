package BattagliaNavaleProject.view;

//Interfaccia che è stata creata per ridurre l'interdipendenza tra classi e 
//definisce i metodi implementati nella classe DoubleGameGridControl,
//il quale servono per:
//addListenerOppenentGriglia = viene utilizzato per aggiungere un listener ad una determinata cella
//della griglia dell'avversario
//removeMouseListener = viene utilizzato per rimuovere un listener da una determinata cella della griglia
//removeListenerOpponent = viene utilizzato per rimuovere un listener da una  cella della griglia 
//dell'avversario
public interface AggiuntaListener {
	public void addListenerOpponentGriglia(DoubleGameGridView v, int i, int j);
	public void removeMouseListener(DoubleGameGridView v, int i, int j);
	public void removeListenerOpponent(DoubleGameGridView v, int i, int j);
}
