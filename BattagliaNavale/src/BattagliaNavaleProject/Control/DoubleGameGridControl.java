package BattagliaNavaleProject.Control;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import BattagliaNavaleProject.client.DoubleGameGridGUI;
import BattagliaNavaleProject.client.Square;

public class DoubleGameGridControl implements MouseListener{
	public DoubleGameGridGUI grid;
	
	public DoubleGameGridControl (DoubleGameGridGUI grid)
	{	
		this.grid = grid;
	}
	
	
	public void mouseClicked(MouseEvent e, int i, int j) {
		if(e.getSource() instanceof Square ){
			System.out.println("sono la square");
			Square clickedSquare= (Square) e.getSource();
			if(clickedSquare.getName().equals("yourBoard")) {
				grid.yourBoard[i][j].setColor(Color.ORANGE);
			System.out.println("sono la square nell'if");
			
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		try {
			
			if(e.getSource() instanceof JPanel ) {
				JPanel clickedPanel= (JPanel) e.getSource();
				
				if(clickedPanel.getName().equals("0")) 
				{   
	                //cosa fare quando clicco la nave da 4
					System.out.println("ciao funziono");
					
					
				}
				if(clickedPanel.getName().equals("1") || clickedPanel.getName().equals("2")) 
				{
					//cosa fare se clicco la nave da 3
					
				}
				if(clickedPanel.getName().equals("3") || clickedPanel.getName().equals("4") || clickedPanel.getName().equals("4")) 
				{
					//cosa fare se clicco navi da 2
					
				}
				if(clickedPanel.getName().equals("5") || clickedPanel.getName().equals("6") || clickedPanel.getName().equals("7") || clickedPanel.getName().equals("8")) 
				{
					//cosa fare se clicco navi da 1
					
				}
			}
			if(e.getSource() instanceof Square ){
				System.out.println("sono la square");
				Square clickedSquare= (Square) e.getSource();
				if(clickedSquare.getName().equals("yourBoard")) {
					
				//grid.yourBoard[clickedSquare.getX()][clickedSquare.getY()].setColor(Color.ORANGE);
				//System.out.println("sono la square nell'if");
				
				}
			}
			
			
				
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
			
		
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
}
