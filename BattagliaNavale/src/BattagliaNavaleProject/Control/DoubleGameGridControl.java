package BattagliaNavaleProject.Control;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.TransferHandler;

import BattagliaNavaleProject.client.DoubleGameGridGUI;
import BattagliaNavaleProject.client.Square;

public class DoubleGameGridControl implements MouseListener, MouseMotionListener{
	
	private static final int GRID_DIMENSION = 10;
	public DoubleGameGridGUI grid;
	private JPanel selectedShip;
	private Point previousPoint;
	private Point currentPoint;
	
	public DoubleGameGridControl (DoubleGameGridGUI grid)
	{	
		this.grid = grid;
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
				if(clickedPanel.getName().equals("3") || clickedPanel.getName().equals("4") || clickedPanel.getName().equals("5")) 
				{
					//cosa fare se clicco navi da 2
					
				}
				if(clickedPanel.getName().equals("6") || clickedPanel.getName().equals("7") || clickedPanel.getName().equals("8") || clickedPanel.getName().equals("9")) 
				{
					//cosa fare se clicco navi da 1
					
				}
			}
			if(e.getSource() instanceof Square ){
				Square clickedSquare= (Square) e.getSource();
				System.out.println("sono la square" +clickedSquare.getx()+ clickedSquare.gety());
				if(clickedSquare.getName().equals("yourBoard")) {
					clickedSquare.setBackground(Color.ORANGE);
				}
			}
			
			
				
		}catch(Exception e3) {
			e3.printStackTrace();	
		}
			
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedShip = (JPanel) e.getSource();
		
		MouseListener[] listeners = selectedShip.getMouseListeners();
		System.out.println("Number of listeners: " + listeners.length);
		for (MouseListener listener : listeners) {
		    System.out.println("Listener class: " + listener.getClass());
		}

		System.out.println("Selected ship name: " + selectedShip.getName());

		if(selectedShip.getName().equals("0") || selectedShip.getName().equals("1") || selectedShip.getName().equals("2") || selectedShip.getName().equals("3") || selectedShip.getName().equals("4") || selectedShip.getName().equals("5") || selectedShip.getName().equals("6") || selectedShip.getName().equals("7") || selectedShip.getName().equals("8") || selectedShip.getName().equals("9"))
		{
			previousPoint = selectedShip.getLocation();
			System.out.println("Sono entrato in questa fantastica funzione" + previousPoint);
		}
		else
			previousPoint = null;
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(previousPoint != null && selectedShip != null)
		{
			System.out.println("Mi dovrei spostare");
			currentPoint = e.getPoint();
			selectedShip = (JPanel) e.getSource();
			selectedShip.setLocation((int) currentPoint.getX() - (int) previousPoint.getX(), (int) currentPoint.getY() - (int) previousPoint.getY());
			previousPoint = currentPoint;
		}
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
