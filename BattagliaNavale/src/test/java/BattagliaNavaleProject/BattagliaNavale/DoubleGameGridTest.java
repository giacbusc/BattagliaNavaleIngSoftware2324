package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.IOException;

import org.junit.Test;

import BattagliaNavaleProject.BattagliaNavaleServer.Accessorio.Square;
import BattagliaNavaleProject.control.DoubleGameGridControl;
import BattagliaNavaleProject.view.DoubleGameGridView;

public class DoubleGameGridTest {

	String username = "lucaciancio";
	String indirizzo = "tcp://192.168.60.206:5555";
	@Test public void Test1() throws IOException, InterruptedException
	{
		

		// 0 1 2 3 4 5 6
		// x y St N E S O
		
		BattagliaNavaleProject.control.DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null,null, indirizzo);

		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "0";
		DGGC.colorabianco();

		assertEquals(Color.white,DGGC.grid.yourBoard[0][0].getColor());

	}
	
	@Test public void Test2() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "Submarine1";
		DGGC.arrayRisposta[0] = 0;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]]);
		
		assertEquals(Color.decode("#00E6AC"), DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]].getColor());
	}
	
	@Test public void Test3() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);

		
		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "Cruiser1";
		DGGC.arrayRisposta[0] = 0;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]]);
		
		assertEquals(Color.decode("#9AFF6B"), DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]].getColor());
	}
	
	@Test public void Test4() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "Destroyer1";
		DGGC.arrayRisposta[0] = 0;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]]);
		
		assertEquals(Color.decode("#FFC20A"), DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]].getColor());
	}
	
	@Test public void Test5() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "Aircraft";
		DGGC.arrayRisposta[0] = 0;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]]);
		
		assertEquals(Color.decode("#D147D1"), DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]].getColor());
	}
	
	@Test public void Test6() throws IOException, InterruptedException
	{
		//Per fare questo test bisogna commentare le righe del metodo ricevimsg() nella classe DoubleGameGridControl
		//dalla 367 alla 375
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		DGGC.arraymsg[0] = "0";
		DGGC.arraymsg[1] = "0";
		DGGC.arraymsg[2] = "Cruiser1";
		DGGC.arrayRisposta[0] = 0;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.arrayRisposta[3] = - 1;
		DGGC.arrayRisposta[4] = - 1;
		DGGC.arrayRisposta[5] = 0;
		DGGC.arrayRisposta[6] = - 1;
		
		DGGC.ricevimsg(null);
		assertEquals(Color.gray, DGGC.grid.yourBoard[1][DGGC.arrayRisposta[1]].getColor());
	}
	
	@Test public void Test7() throws IOException, InterruptedException
	{
		//Per fare questo test bisogna commentare le righe del metodo ricevi2msg() nella classe DoubleGameGridControl
		//dalla 257 alla 265
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		DGGC.arraymsg[2] = "Destroyer1";
		DGGC.arrayRisposta[0] = 2;
		DGGC.arrayRisposta[1] = 0;
		DGGC.arrayRisposta[2] = 0;
		DGGC.arrayRisposta[3] = 0;
		DGGC.arrayRisposta[4] = - 1;
		DGGC.arrayRisposta[5] = -1;
		DGGC.arrayRisposta[6] = - 1;
		DGGC.x = 0;
		DGGC.y = 0;
		
		DGGC.ricevi2msg(null, DGGC.x, DGGC.y);
		assertEquals(Color.decode("#FFC20A"),DGGC.grid.yourBoard[DGGC.arrayRisposta[0]][DGGC.arrayRisposta[1]].getColor());
	}
}
