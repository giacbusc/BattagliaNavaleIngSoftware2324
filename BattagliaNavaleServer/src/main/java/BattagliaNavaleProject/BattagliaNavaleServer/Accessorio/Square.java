package BattagliaNavaleProject.BattagliaNavaleServer.Accessorio;

import java.awt.Color;
import javax.swing.JPanel;


public class Square extends JPanel
{
	
	public int x, y, stato;
	public String nome;
	Griglia g;
	
	
	public Square(int x, int y, int stato, Griglia g)
	{
		
		setBackground(Color.white);
		this.g= g;
		this.x = x;
		this.y = y;
		this.stato = stato;
		g.addSquare(this);
	}

	public Square(int x, int y, int stato, String nome) {
		super();
		setBackground(Color.white);
		this.x = x;
		this.y = y;
		this.stato = stato;
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getx()
	{
		return this.x;
	}
	
	public int gety()
	{
		return this.y;
	}
	
	public int getStato()
	{
		return this.stato;
	}

	//Stato: 0 se è libero
	//		 1 se è occupato
	//		 2 se è colpito
	//		 3 se è affondato
	public void setStato(int stato) {
		this.stato = stato;
	}

	public Color getColor()
	{
		return this.getBackground();
	}
	public void setColor(Color c)
	{
		if(c!=Color.white) {
		this.stato=1;
		}
		this.setBackground(c);
	}
	
	public void setColpito()
	{
		this.setBackground(Color.red);
	}
	
	public void setGrigio()
	{
		this.setBackground(Color.gray);
	}
	public void setAffondato(Color nero)
	{
		this.setBackground(nero);
	}
	
	public void setReset()
	{
		this.setBackground(Color.white);
	}

	public void setAcqua() {
		this.setBackground(Color.blue);
		
	}
}
