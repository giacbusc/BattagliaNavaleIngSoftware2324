package test.java.BattagliaNavaleProject.BattagliaNavale;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import BattagliaNavaleProject.Control.DoubleGameGridControl;
import BattagliaNavaleProject.formGui.DoubleGameGridView;

public class TestJunit {
@Test public void Test() throws IOException, InterruptedException {
	DoubleGameGridView v=new DoubleGameGridView("jack");
	DoubleGameGridControl control= new DoubleGameGridControl(v );
	
	control.arrayRisposta[0]=1;
	control.arrayRisposta[1]=1;
	control.arrayRisposta[2]=1;
	control.ricevimsg(null);
	assertEquals(0,v.yourBoard[1][1].getStato());
}

}
