package test.java.BattagliaNavaleProject.BattagliaNavale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import java.io.IOException;

import org.junit.Test;

import BattagliaNavaleProject.control.SchermataAttesaControl;

public class SchemataAttesaTest {
    
    @Test
    public void testGetIndirizzo() throws IOException, InterruptedException {
        BattagliaNavaleProject.control.SchermataAttesaControl control = new BattagliaNavaleProject.control.SchermataAttesaControl("msg", "username", null, null);
        assertNull(control.getIndirizzo());// non ho ancora settato l'indirizzo me lo aspetto nullo
        
        control.setIndirizzo("tcp://192.168.60.206:5555");
        assertEquals("tcp://192.168.60.206:5555", control.getIndirizzo());// m aspetto che in control io abbia passato lo stesso indirizzo settato nella schermata control
    }

    @Test
    public void testSetIndirizzo() throws IOException, InterruptedException {
    	 SchermataAttesaControl control = new SchermataAttesaControl("msg", "username", null, null);
        control.setIndirizzo("tcp://192.168.60.206:5555");
        assertEquals("tcp://192.168.60.206:5555", control.getIndirizzo());
    }
    //testo che ricevendo come risposta OK POS 2 cambi il booleano che regola il ciclo while
    public void testAttesa() throws IOException, InterruptedException {
    	String username= "fedegerva";
    	 SchermataAttesaControl control = new SchermataAttesaControl("msg", "username", null, null);
         String rispostaMsg="OK POS2";
         control.attesa(username);
         
         assertFalse(control.isR());// r è falso infatti la risposta è okpos2
    }
 
}