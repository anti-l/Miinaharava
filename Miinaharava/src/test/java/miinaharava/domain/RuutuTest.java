/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miinaharava.domain;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Antti
 */
public class RuutuTest {

    private Ruutu ruutu;
    
    public RuutuTest() {
    }

    
    @Before
    public void setUp() {
        ruutu = new Ruutu();
    }
    
    @Test
    public void luoRuutu() {
        Ruutu uusiRuutu = new Ruutu();
        assertEquals(".", uusiRuutu.toString());
    }
    
    @Test
    public void asetaMiina() {
        ruutu.setMiina();
        assertEquals("X", ruutu.toString());
    }
    
    @Test
    public void onkoMiinaaJosMiinaAsetettu() {
        ruutu.setMiina();
        assertEquals(true, ruutu.onkoMiinaa());
    }
    
    @Test
    public void onkoMiinaaJosMiinaaEiAsetettu() {
        assertEquals(false, ruutu.onkoMiinaa());
    }
    
    @Test
    public void setViereisiaAlleNolla() {
        ruutu.setViereisia(-1);
        assertEquals(0, ruutu.getViereiset());
    }
    
    @Test 
    public void setViereisiaYliKahdeksan() {
        ruutu.setViereisia(9);
        assertEquals(0, ruutu.getViereiset());
    }

    @Test
    public void setViereisiaKahdeksan() {
        ruutu.setViereisia(8);
        assertEquals(8, ruutu.getViereiset());
    }
    
    @Test
    public void setViereisiaNolla() {
        ruutu.setViereisia(0);
        assertEquals(0, ruutu.getViereiset());
    }
    
    @Test
    public void getKatsottuTest() {
        ruutu.katsoRuutu();
        assertEquals(true, ruutu.getKatsottu());
    }
    
    @Test
    public void setLiputettuTest() {
        ruutu.setLiputettu();
        assertEquals(true, ruutu.getLiputettu());
        
    }
    
    @Test
    public void removeLiputettuTest() {
        ruutu.setLiputettu();
        ruutu.removeLiputettu();
        assertEquals(false, ruutu.getLiputettu());
        
    }
    
    @Test
    public void onkoTyhjaTest() {
        Ruutu tyhjaRuutu = new Ruutu();
        assertEquals(true, tyhjaRuutu.onkoTyhja());
    }
    
    @Test
    public void onkoTyhjaJosMiinaTest() {
        Ruutu miinaRuutu = new Ruutu();
        miinaRuutu.setMiina();
        assertEquals(false, miinaRuutu.onkoTyhja());
    }
    
    @Test
    public void merkkijonoTesti1() {
        Ruutu ruutu = new Ruutu();
        ruutu.setMiina();
        ruutu.setLiputettu();
        assertEquals("x", ruutu.toString());
    }
    
    @Test
    public void merkkijonoTesti2() {
        Ruutu ruutu = new Ruutu();
        ruutu.katsoRuutu();
        assertEquals("#", ruutu.toString());
    }
    
    @Test
    public void merkkijonoTesti3() {
        Ruutu ruutu = new Ruutu();
        ruutu.setMiina();
        assertEquals("X", ruutu.toString());
    }
    
}
