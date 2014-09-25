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
    Ruudukko ruudukko;
    Ruutu ruutu;
    
    @Before
    public void setUp() {
        /*
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        */
    }
    
    @Test
    public void luoPerusRuudukko() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        assertEquals(10, ruudukko.getMiinoja());
    }
    
    @Test
    public void luoIsoRuudukko() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(15, 15, 15);
        Ruudukko r = sovlog.getRuudukko();
        assertEquals(15, r.getMiinoja());
    }
    
    @Test
    public void tarkistaRuutuTest() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        sovlog.tarkistaRuutu(0, 0);
    }
    
    @Test
    public void tarkistaRuutuOnMiinaTest() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        Ruudukko ruudukko = sovlog.getRuudukko();
        Ruutu ruutu = ruudukko.getRuutu(0, 0);
        ruutu.setMiina();
        sovlog.tarkistaRuutu(0, 0);
    }
    
    @Test
    public void liputaRuutuTest() {
        /*
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
//        Ruudukko ruudukko = sovlog.getRuudukko();
//        Ruutu ruutu = ruudukko.getRuutu(0, 0);
//        Ruutu ruutu = sovlog.getRuudukko().getRuutu(0,0);
        sovlog.liputaRuutu(0, 0);
        assertEquals(true, sovlog.getRuudukko().getRuutu(0, 0).getLiputettu());
        */
    }
    
}
