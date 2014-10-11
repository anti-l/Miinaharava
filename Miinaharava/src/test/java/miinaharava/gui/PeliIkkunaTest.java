package miinaharava.gui;

import java.awt.Container;
import miinaharava.domain.Ruudukko;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class PeliIkkunaTest {
    
    private Sovelluslogiikka sovlog;
    private  PeliIkkuna peli;
    
    @Before
    public void setUp() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        peli = new PeliIkkuna(sovlog);
    }
    
    @Test
    public void konstruktoriTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        peli = new PeliIkkuna(sovlog);
    }
    
    @Test
    public void ajoTesti() {
        Ruudukko ruudukko = sovlog.getRuudukko();
        peli = new PeliIkkuna(sovlog);
        peli.run();
    }
    
    @Test
    public void painaNappiAlasText() {
        peli.run();
        peli.painaNappiAlas(0, 0);
        peli.paivitaNapit();
    }
    
    @Test
    public void asetaNapinTekstiTest() {
        peli.run();
        peli.asetaNapinTeksti(0, 0, "3");
        peli.paivitaNapit();
    }
    
    @Test
    public void liputaNappiTest() {
        peli.run();
        peli.liputa(0, 0);
        peli.paivitaNapit();
    }
    
    @Test
    public void miinoitaNappiTest() {
        peli.run();
        peli.miinoita(0, 0);
        peli.paivitaNapit();
    }
    
    @Test
    public void naytaMiinatTest() {
        peli.run();
        peli.naytaMiinat();
    }
    
    /*
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
    */
}
