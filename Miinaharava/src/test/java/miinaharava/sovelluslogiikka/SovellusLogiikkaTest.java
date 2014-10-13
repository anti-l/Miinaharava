package miinaharava.sovelluslogiikka;

import java.util.ArrayList;
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
        sovlog.luoRuudukko(2, 2, 1);
        sovlog.getRuudukko().getRuutu(0, 0).setMiina();
        sovlog.getRuudukko().getRuutu(0, 1).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 0).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 1).poistaMiina();
        sovlog.tarkistaRuutu(0, 0);
        assertEquals(true, sovlog.getRuudukko().getRuutu(0, 0).onkoMiinaa());
    }

    @Test
    public void ruudunTekstiMiinallaTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(2, 2, 1);
        sovlog.getRuudukko().getRuutu(0, 0).setMiina();
        sovlog.getRuudukko().getRuutu(0, 1).setMiina();
        sovlog.getRuudukko().getRuutu(1, 0).setMiina();
        sovlog.getRuudukko().getRuutu(1, 1).setMiina();
        sovlog.getRuudukko().asetaViereistenMiinojenMaarat();
        String teksti = sovlog.ruudunTeksti(0, 0);
        assertEquals("3", teksti);
    }

    @Test
    public void tarkistaRuutuTyhja() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(3, 3, 1);
        ArrayList<Ruutu> koord = sovlog.getRuudukko().etsiMiinojenKoordinaatit();
        sovlog.getRuudukko().getRuutu(koord.get(0).getKoordinaatit()[0], koord.get(0).getKoordinaatit()[1]).poistaMiina();
        sovlog.tarkistaRuutu(0, 0);
        String teksti = sovlog.ruudunTeksti(0, 0);
        assertEquals("", teksti);
    }

    @Test
    public void ruudunTekstiTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.getRuudukko().getRuutu(0, 0).poistaMiina();
        sovlog.getRuudukko().getRuutu(0, 1).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 0).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 1).poistaMiina();
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
    public void liputaRuutuLiputettuJoTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.liputaRuutu(0, 0);
        sovlog.liputaRuutu(0, 0);
        assertEquals(false, sovlog.getRuudukko().getRuutu(0, 0).getLiputettu());
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
        sovlog.getRuudukko().getRuutu(0, 0).poistaMiina();
        sovlog.getRuudukko().getRuutu(0, 1).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 0).poistaMiina();
        sovlog.getRuudukko().getRuutu(1, 1).poistaMiina();
        sovlog.getRuudukko().asetaViereistenMiinojenMaarat();
        sovlog.paljastaTyhjat(0, 0);
        assertEquals(true, sovlog.getRuudukko().getRuutu(1, 1).getKatsottu());

    }

    @Test
    public void paljastaTyhjat3x3Ruudukossa() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(5, 5, 1);
        ArrayList<Ruutu> koord = sovlog.getRuudukko().etsiMiinojenKoordinaatit();
        sovlog.getRuudukko().getRuutu(koord.get(0).getKoordinaatit()[0], koord.get(0).getKoordinaatit()[1]).poistaMiina();
        sovlog.getRuudukko().getRuutu(4, 4).setMiina();
        sovlog.paljastaTyhjat(0, 0);
        String teksti = sovlog.ruudunTeksti(0, 0);
        assertEquals("", teksti);
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

    @Test
    public void annaVihjeitaJaLopetaPeli() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(2, 2, 1);
        ruudukko = sovlog.getRuudukko();
        int[] koordinaatit = sovlog.annaVihje();
        sovlog.liputaRuutu(koordinaatit[0], koordinaatit[1]);
        sovlog.loppuukoPeli();
    }
    
    @Test
    public void annaVihjeKunKaikkiLiputettuJo() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(2, 2, 4);
        ruudukko = sovlog.getRuudukko();
        sovlog.getRuudukko().getRuutu(0, 0).setLiputettu();
        sovlog.getRuudukko().getRuutu(0, 1).setLiputettu();
        sovlog.getRuudukko().getRuutu(1, 0).setLiputettu();
        sovlog.getRuudukko().getRuutu(1, 1).setLiputettu();
        assertEquals(null, sovlog.annaVihje());
    }

    @Test
    public void paaseekoListalleTest() {
        sovlog = new Sovelluslogiikka();

        sovlog.luoRuudukko();
        for (int i = 0; i < 10; i++) {
            int[] koordinaatit = sovlog.annaVihje();
            sovlog.liputaRuutu(koordinaatit[0], koordinaatit[1]);
        }
        boolean paaseekoListalle = sovlog.paaseekoListalle();
        assertEquals(true, paaseekoListalle);
    }

    @Test
    public void peliAikaTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        long aika = sovlog.getAika();
        assertEquals(1, aika);
    }
    
    @Test
    public void sijoitaTulosTest() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko();
        sovlog.sijoitaTulos(800, "Testi");
        ArrayList<Tulos> helpotTulokset = sovlog.getHuippuTulokset().getHelpot();
        assertEquals(800, helpotTulokset.get(0).getAika());
        assertEquals("Testi", helpotTulokset.get(0).getNimi());
    }

}
