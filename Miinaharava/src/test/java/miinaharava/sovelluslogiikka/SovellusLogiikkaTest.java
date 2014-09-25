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
    
    @Before
    public void setUp() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna kayttis = new PeliIkkuna(sovlog);
    }
    
    @Test
    public void luoPerusRuudukko() {
        sovlog.luoRuudukko();
        Ruudukko r = sovlog.getRuudukko();
        assertEquals(10, r.getMiinoja());
    }
    
    @Test
    public void luoIsoRuudukko() {
        sovlog.luoRuudukko(15, 15, 15);
        Ruudukko r = sovlog.getRuudukko();
        assertEquals(15, r.getMiinoja());
    }
    
    @Test
    public void tarkistaRuutuTest() {
        sovlog.luoRuudukko();
        Ruudukko r = sovlog.getRuudukko();
        RuutuNappi nappi = new RuutuNappi(r, 0, 0);
        sovlog.tarkistaRuutu(0, 0, nappi);
    }
    
}
