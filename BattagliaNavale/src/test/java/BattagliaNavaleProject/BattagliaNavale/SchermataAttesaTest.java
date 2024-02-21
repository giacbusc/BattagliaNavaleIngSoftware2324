package test.java.BattagliaNavaleProject.BattagliaNavale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import java.io.IOException;

import org.junit.Test;

import BattagliaNavaleProject.Control.SchermataAttesaControl;

public class SchermataAttesaTest {
    
    @Test
    public void testGetIndirizzo() throws IOException, InterruptedException {
        SchermataAttesaControl control = new SchermataAttesaControl("msg", "username", null);
        assertNull(control.getIndirizzo());
        
        control.setIndirizzo("tcp://192.168.60.206:5555");
        assertEquals("tcp://192.168.60.206:5555", control.getIndirizzo());
    }

    @Test
    public void testSetIndirizzo() throws IOException, InterruptedException {
    	 SchermataAttesaControl control = new SchermataAttesaControl("msg", "username", null);
        control.setIndirizzo("tcp://192.168.60.206:5555");
        assertEquals("tcp://192.168.60.206:5555", control.getIndirizzo());
    }
    //testo che ricevendo come risposta OK POS 2 cambi il booleano che regola il ciclo while
    public void testAttesa() throws IOException, InterruptedException {
    	String username= "fedegerva";
    	 SchermataAttesaControl control = new SchermataAttesaControl("msg", "username", null);
         String rispostaMsg="OK POS2";
         control.attesa(username);
         
         assertFalse(control.r);
    }
 
}
