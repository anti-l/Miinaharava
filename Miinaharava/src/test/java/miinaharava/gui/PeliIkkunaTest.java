package miinaharava.gui;

import java.awt.Container;
import miinaharava.domain.Ruudukko;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class PeliIkkunaTest {
    
    Sovelluslogiikka sovlog;
    PeliIkkuna peli;
    
    @Test
    public void konstruktoriTest() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
    }
    
    @Test
    public void ajoTesti() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        peli = new PeliIkkuna(sovlog);
        peli.run();
    }
    
    @Test
    public void lopetaPeli() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        peli.gameOver();
    }
    
    @Test
    public void voitaPeli() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        peli.peliVoitettu(5000);
    }
}
