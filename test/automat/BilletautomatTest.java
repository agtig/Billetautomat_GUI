/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automat;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jorn
 */
public class BilletautomatTest {
    
    public BilletautomatTest() {
    }
        Billetautomat automat = new Billetautomat();

    @Test
    public void indsætPenge() {
        //Places 10 coins in the machine and expect 10 
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(10);
        assertEquals(10, automat.getBalance(), 0.01);
      
    }
    @Test
    public void indsætnegativpenge() {
        //places -50 and expect 0, so should true.
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(-50);
        assertEquals(0, automat.returpenge(), 0.01);

    }
    @Test
    public void indsætPengeFail() {
        //places 20 and expect 10, so should give a fail message.
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(20);
        assertNotEquals(10, automat.getBalance(), 0.01);
      
    }
    @Test
    public void pengereturn() {
        //places 30 and expect 30 return, so should give a true.
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(30);
        assertEquals(30, automat.returpenge(), 0.01);
      
    }
    @Test
    public void pengereturnFail() {
        //places 30 and expect 0, so should give a fail message.
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(30);
        assertNotEquals(0, automat.returpenge(), 0.01);

    }
    @Test
    public void montørDerændrebilletpriss() {
        //login with montør and then changes ticket to 35, expect true.
        Billetautomat automat = new Billetautomat();
        automat.montørLogin("1234");
        assertTrue(automat.erMontør());
        automat.setBilletpris(35);
        assertEquals(35, automat.getBilletpris(), 0.01);

    }
}