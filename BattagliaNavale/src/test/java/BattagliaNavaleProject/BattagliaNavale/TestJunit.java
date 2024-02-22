package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;

import BattagliaNavaleProject.control.DoubleGameGridControl;
import BattagliaNavaleProject.doubleGameGridModel.Square;
import BattagliaNavaleProject.multiplayer.Partita;
import BattagliaNavaleProject.view.DoubleGameGridView;

public class TestJunit {
	@Test
	public void Test() throws IOException, InterruptedException {
		DoubleGameGridView v = new DoubleGameGridView("jack");
		DoubleGameGridControl control = new DoubleGameGridControl(v);

		control.arrayRisposta[0] = 1;
		control.arrayRisposta[1] = 1;
		control.arrayRisposta[2] = 1;
		control.ricevimsg(null);
		assertEquals(0, v.yourBoard[1][1].getStato());
	}

	@Test
	public void provaAffondata() {
		Square[][] player = new Square[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				player[i][j] = new Square(i, j, 0, "0000000000");
			}
		}

		player[1][1].setNome("Aircraft");

		assertEquals("Aircraft", player[1][1].getNome());

		Partita p = new Partita();
		assertFalse(p.controllaAffondata(player, 1, 1));
		// essendo che Aircraft è una barca con lunghezza 4 mi aspetto che ci siano 4
		// celle con il nome "aircraft" settato
		// visto che per ora solo la cella [1][1] ha il nome aircraft e nessun altra il
		// metodo dovrebbe restituire false
		// in quanto non ci sono 4 celle con lo stato a 2 (che significa che sono celle
		// colpite) che si chiamano aircraft

		player[1][2].setNome("Aircraft");

		assertFalse(p.controllaAffondata(player, 1, 2));
		// Per ora so che sono state colpite le celle 1,1 e 1,2 che appartengono alla
		// barca aircraft. Perchè il metodo
		// restituisca vero mi aspetto che ci siano 4 celle di nome aircraft con lo
		// stato di colpito!
		// Attenzione: quando si chiama il metodo controllaAffondata si va a verificare
		// settando la cella stessa come colpita

		player[1][2].setStato(2);
		player[1][3].setNome("Aircraft");
		player[1][3].setStato(2);
		player[1][4].setNome("Aircraft");

		assertTrue(p.controllaAffondata(player, 1, 4));
		// Mi aspetto di ricevere true in quanto ci sono 4 celle con il nome Aircraft
		// con lo stato di 2, ovvero colpite.
		// il loro stato quindi dovrà variare diventando a 3 che indica che la barca è
		// affondata
	}

}
