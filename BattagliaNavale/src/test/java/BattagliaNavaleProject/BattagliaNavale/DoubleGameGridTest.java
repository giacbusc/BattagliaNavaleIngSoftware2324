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
	@Test public void test1() throws IOException, InterruptedException
	{
		

		// 0 1 2 3 4 5 6
		// x y St N E S O
		
		BattagliaNavaleProject.control.DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null,null, indirizzo);

		String[] arraymsg = {"0", "0", "Submarine1"};
		DGGC.setArraymsg(arraymsg);
		int[] arrayRisposta = {0, 0, 0, -1, -1, -1, -1,-1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.colorabianco();
		//Verifica se la cella selezionata viene colorata di bianco
		assertEquals(Color.white,DGGC.grid.yourBoard[0][0].getColor());

	}
	
	@Test public void test2() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		String[] arraymsg = {"0", "0", "Submarine1"};
		DGGC.setArraymsg(arraymsg);
		int[] arrayRisposta = {0, 0, 0, -1, -1, -1, -1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)]);
		//Verifica se quando viene inserito un sottomarino, la griglia si colora del colore corretto
		assertEquals(Color.decode("#00E6AC"), DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)].getColor());
	}
	
	@Test public void test3() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);

		
		String[] arraymsg = {"0", "0", "Cruiser1"};
		DGGC.setArraymsg(arraymsg);
		int[] arrayRisposta = {0, 0, 0, -1, -1, -1, -1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)]);
		//Verifica se quando viene inserito una barca cruiser, la griglia si colora del colore corretto
		assertEquals(Color.decode("#9AFF6B"), DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)].getColor());
	}
	
	@Test public void test4() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		String[] arraymsg = {"0", "0", "Destroyer1"};
		DGGC.setArraymsg(arraymsg);		
		int[] arrayRisposta = {0, 0, 0, -1, -1, -1, -1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)]);
		//Verifica se quando viene inserito una barca destroyer, la griglia si colora del colore corretto
		assertEquals(Color.decode("#FFC20A"), DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)].getColor());
	}
	
	@Test public void test5() throws IOException, InterruptedException
	{
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		String[] arraymsg = {"0", "0", "Aircraft"};
		DGGC.setArraymsg(arraymsg);
		int[] arrayRisposta = {0, 0, 0, -1, -1, -1, -1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.colorebarca(DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)]);
		//Verifica se quando viene inserito una barca aircraft, la griglia si colora del colore corretto
		assertEquals(Color.decode("#D147D1"), DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)].getColor());
	}
	
	@Test public void test6() throws IOException, InterruptedException
	{
		//Per fare questo test bisogna commentare le righe del metodo ricevimsg() nella classe DoubleGameGridControl
		//dalla 373 alla 382
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		String[] arraymsg = {"0", "0", "Cruiser1"};
		DGGC.setArraymsg(arraymsg);
		int[] arrayRisposta = {0, 0, 0, -1, -1, 0, -1};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.ricevimsg(null);
		//Verifica se il metodo ricevi msg funziona correttamente
		//Verifica perciò che le celle vicino alle celle selezionate si colorino di grigio per indicare
		//che la barca può essere posizionata in quei punti
		assertEquals(Color.gray, DGGC.grid.yourBoard[1][DGGC.getArrayRisposta(1)].getColor());
	}
	
	@Test public void Test7() throws IOException, InterruptedException
	{
		//Per fare questo test bisogna commentare le righe del metodo ricevi2msg() nella classe DoubleGameGridControl
		//dalla 264 alla 272
		DoubleGameGridControl DGGC = new DoubleGameGridControl(username, null, null, indirizzo);
		
		String[] arraymsg = {"0", "0", "Destroyer1"};
		DGGC.setArraymsg(arraymsg);	
		int[] arrayRisposta = {3, 0, 0, 0, -1, -1,-1,0};
		DGGC.setArrayRisposta(arrayRisposta);
		DGGC.setX(0);
		DGGC.setY(0);
		
		DGGC.ricevi2msg(null, DGGC.getX(), DGGC.getY());
		assertEquals(Color.white,DGGC.grid.yourBoard[DGGC.getArrayRisposta(0)][DGGC.getArrayRisposta(1)].getColor());
	}
}
