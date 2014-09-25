/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.sovelluslogiikka;

import miinaharava.domain.*;
import miinaharava.gui.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class SovellusLogiikkaTest {

    Sovelluslogiikka sovlog;
    PeliIkkuna peliIkkuna;
    
    /*
    @Before
    public void setUp() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna kayttis = new PeliIkkuna(sovlog);
    }
    */
    
    @Test
    public void luoPerusRuudukko() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko r = sovlog.getRuudukko();
        assertEquals(10, r.getMiinoja());
    }
    
    @Test
    public void luoIsoRuudukko() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(15, 15, 15);
        Ruudukko r = sovlog.getRuudukko();
        assertEquals(15, r.getMiinoja());
    }
    
    @Test
    public void peliIkkunanTest() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna peliIkkuna = new PeliIkkuna(sovlog);
        sovlog.setPeliIkkuna(peliIkkuna);
    }
    
    @Test
    public void liputaRuutuTest() {
        /*
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna peliIkkuna = new PeliIkkuna(sovlog);
//        Ruudukko ruudukko = sovlog.getRuudukko();
//        Ruutu ruutu = ruudukko.getRuutu(0, 0);
        Ruutu ruutu = sovlog.getRuudukko().getRuutu(0,0);
        sovlog.liputaRuutu(0, 0);
        
        assertEquals(true, ruutu.getLiputettu());
        */
    }
    
    @Test
    public void tarkistaRuutuTest() {
        /*
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna peliIkkuna = new PeliIkkuna(sovlog);
        sovlog.luoRuudukko();
        sovlog.setPeliIkkuna(peliIkkuna);
        peliIkkuna.run();
        Ruudukko r = sovlog.getRuudukko();
        RuutuNappi nappi = new RuutuNappi(r, 0, 0);
        sovlog.tarkistaRuutu(0, 0, nappi);
        */
    }
    
    @Test
    public void tarkistaRuutuOnMiinaTest() {
        /*
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna peliIkkuna = new PeliIkkuna(sovlog);
        sovlog.setPeliIkkuna(peliIkkuna);
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        Ruutu ruutu = ruudukko.getRuutu(0, 0);
        ruutu.setMiina();
        RuutuNappi nappi = new RuutuNappi(ruudukko, 0, 0);
        sovlog.tarkistaRuutu(0, 0, nappi);
        */
    }
    
}
