package BattagliaNavaleProject.client;

import java.awt.Color;
import javax.swing.JPanel;

import BattagliaNavaleProject.Control.DoubleGameGridControl;

public class Square extends JPanel
{
	private Color color;
	public int x, y, stato;
	
	
	public Square(int x, int y, int stato)
	{
		color = Color.white;
		setBackground(Color.white);
		this.x = x;
		this.y = y;
		this.stato = stato;
	}
	
	public void resetColor()
	{
		setBackground(color);
	}
	
	public void setColor(Color c)
	{
		this.color = c;
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

	public void setStato(int stato) {
		this.stato = stato;
	}
	
}
