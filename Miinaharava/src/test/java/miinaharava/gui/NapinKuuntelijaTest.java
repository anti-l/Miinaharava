package miinaharava.gui;

import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class NapinKuuntelijaTest {

    Sovelluslogiikka sovlog;
    PeliIkkuna peli;
    NapinKuuntelija napinkuuntelija;
    RuutuNappi nappi;
    
    @Before
    public void setUp() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        napinkuuntelija = new NapinKuuntelija(sovlog, peli, 0, 0);
    }
    
    @Test
    public void konstruktoritesti() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        napinkuuntelija = new NapinKuuntelija(sovlog, peli, 0, 0);
    }
    
    @Test
    public void nappiTesti() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        napinkuuntelija = new NapinKuuntelija(sovlog, peli, 0, 0);
//        napinkuuntelija.mouseReleased(null);
    }
    
    @Test
    public void mousePressedTest() {
        napinkuuntelija.mousePressed(null);
    }
    
    @Test
    public void mouseClickedTest() {
        napinkuuntelija.mouseClicked(null);
    }
    
    @Test
    public void mouseEnteredTest() {
        napinkuuntelija.mouseEntered(null);
    }
    
    @Test
    public void mouseExitedTest() {
        napinkuuntelija.mouseExited(null);
    }
    
}


