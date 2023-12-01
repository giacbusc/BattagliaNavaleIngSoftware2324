package BattagliaNavaleProject.client;

import java.util.ArrayList;

public class Ship 
{
	public ArrayList<Piece> pieces;
	public boolean sunk = false;
	
	public Ship()
	{
		pieces = new ArrayList<>();
	}
	
	public Ship(ArrayList<Piece> pezzi)
	{
		this.pieces = new ArrayList<>();
		
		for(Piece p : pezzi)
		{
			this.pieces.add(p);
		}
	}
	
	public boolean add(int x, int y)
	{
		return pieces.add(new Piece(x, y));
	}
	
	public boolean checkSunk()
	{
		for(Piece p : pieces)
		{
			if(!p.hit)
				return false;
		}
		
		this.sunk = true;
		return true;
	}
}
