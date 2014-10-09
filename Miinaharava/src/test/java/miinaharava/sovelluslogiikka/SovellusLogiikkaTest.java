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
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        ruudukko = sovlog.getRuudukko();
        assertEquals(10, ruudukko.getMiinoja());
    }
    
    @Test
    public void luoIsoRuudukko() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(15, 15, 15);
        ruudukko = sovlog.getRuudukko();
        assertEquals(15, ruudukko.getMiinoja());
    }
    
    @Test
    public void tarkistaRuutuTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        ruudukko = sovlog.getRuudukko();
        sovlog.tarkistaRuutu(0, 0);
    }
    
    @Test
    public void tarkistaRuutuOnMiinaTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.getRuudukko().getRuutu(0,0).setMiina();
        sovlog.tarkistaRuutu(0, 0);
    }
    
    @Test
    public void ruudunTekstiMiinallaTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.getRuudukko().getRuutu(0,0).setMiina();
        sovlog.getRuudukko().getRuutu(0,1).setMiina();
        sovlog.getRuudukko().getRuutu(1,0).setMiina();
        sovlog.getRuudukko().getRuutu(1,1).setMiina();
        sovlog.getRuudukko().asetaViereistenMiinojenMaarat();
        String teksti = sovlog.ruudunTeksti(0, 0);
        assertEquals("3", teksti);
    }
    
    @Test
    public void ruudunTekstiTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.getRuudukko().getRuutu(0,0).poistaMiina();
        sovlog.getRuudukko().getRuutu(0,1).poistaMiina();
        sovlog.getRuudukko().getRuutu(1,0).poistaMiina();
        sovlog.getRuudukko().getRuutu(1,1).poistaMiina();
        sovlog.getRuudukko().asetaViereistenMiinojenMaarat();
        String teksti = sovlog.ruudunTeksti(0, 0);
        assertEquals("", teksti);
    }
    
    @Test
    public void liputaRuutuTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.liputaRuutu(0, 0);
        assertEquals(true, sovlog.getRuudukko().getRuutu(0, 0).getLiputettu());
    }
    
    @Test
    public void peliOhiTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.setPeliOhi();
        assertEquals(true, sovlog.onkoPeliOhi());
    }
    
    @Test
    public void paljastaTyhjatTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(10, 10, 1);
        sovlog.paljastaTyhjat(0,0);
    }
    
    @Test
    public void setVaikeusTasoTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.setVaikeustaso(Vaikeustaso.HELPPO);
        assertEquals(Vaikeustaso.HELPPO, sovlog.getVaikeustaso());
    }
    
    @Test
    public void getPelinKestoTest() {
        sovlog = new Sovelluslogiikka();
        long aika = sovlog.getPelinKesto();
        assertEquals(0, aika);
    }
    
    @Test
    public void getTuloksetTest() {
        sovlog = new Sovelluslogiikka();
        HuippuTulokset tulokset = sovlog.getHuippuTulokset();
    }
}
