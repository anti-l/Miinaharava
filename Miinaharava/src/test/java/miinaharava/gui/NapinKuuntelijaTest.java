package miinaharava.gui;

import miinaharava.sovelluslogiikka.Sovelluslogiikka;
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
        
    }
}


