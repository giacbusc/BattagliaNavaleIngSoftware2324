package BattagliaNavaleProject.client;

import java.awt.Color;
import javax.swing.JPanel;

import BattagliaNavaleProject.Control.DoubleGameGridControl;

public class Square extends JPanel
{
	private Color color;
	public int x, y;
	
	public Square(int x, int y)
	{
		color = Color.white;
		setBackground(Color.white);
		this.x = x;
		this.y = y;
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
}
