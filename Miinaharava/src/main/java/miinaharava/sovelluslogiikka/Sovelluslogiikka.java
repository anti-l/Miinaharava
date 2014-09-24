package miinaharava.sovelluslogiikka;

import java.awt.Color;
import javax.swing.ImageIcon;
import miinaharava.domain.*;
import miinaharava.gui.RuutuNappi;
import miinaharava.gui.PeliIkkuna;

/**
 *
 * @author Antti
 */
public class Sovelluslogiikka {

    private Ruudukko ruudukko;
    private PeliIkkuna peliIkkuna;
    private int miinoja;
    private int lippuja;
    private long alkuAika;
    private long loppuAika;

    public Sovelluslogiikka() {
        this.luoRuudukko();
        alkuAika = System.currentTimeMillis();
    }

    public void luoRuudukko() {
        this.ruudukko = new Ruudukko();
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
    }

    public void luoRuudukko(int leveys, int korkeus, int miinoja) {
        this.ruudukko = new Ruudukko(leveys, korkeus, miinoja);
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
    }

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    public void setPeliIkkuna(PeliIkkuna kayttis) {
        this.peliIkkuna = kayttis;
    }

    public void tarkistaRuutu(int x, int y, RuutuNappi nappi) {
        Ruutu ruutu = this.ruudukko.getRuutu(x, y);
        if (ruutu.onkoMiinaa()) {
            peliIkkuna.miinoita(x, y);
            //Tarviiko epäonnistumisen nopeudesta edes pitää kirjaa?
            //long kulunutAikaSekunneissa = (System.currentTimeMillis() - alkuAika) / 1000;
            peliIkkuna.gameOver();
        } else if (ruutu.onkoTyhja()) {
            paljastaTyhjat(x, y);
        } else {
            String napinTeksti = "";
            if (ruutu.getViereiset() > 0) {
                napinTeksti += ruutu.getViereiset();
            }
            nappi.setText(napinTeksti);
        }
        this.ruudukko.getRuutu(x, y).katsoRuutu();
        peliIkkuna.painaNappiAlas(x, y);
        loppuukoPeli();
    }

    public void paljastaTyhjat(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == x && j == y) {                                             // Ruutu, mitä pelaaja itse painoi -> jatketaan
                    continue;
                } else if (x + i < 0 || x + i >= ruudukko.getLeveys()) {            // Ruutu, joka menee vaakasuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                } else if (y + j < 0 || y + j >= ruudukko.getKorkeus()) {           // Ruutu, joka menee pystysuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                    // Katsotun ruudun kaikki 8 naapuria +/- 1 pysty- ja vaakasuuntaan:
                    // Jos ruutu on tyhjä eikä sitä ole tarkistettu vielä
                } else if (ruudukko.getRuutu((x + i), (y + j)).onkoTyhja() && ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();               // Määritetään Ruutu katsotuksi
                    peliIkkuna.painaNappiAlas(x + i, y + j);                            // Painetaan käyttöliittymän nappi alas
                    paljastaTyhjat(x + i, y + j);                                   // Kutsutaan rekursiivisesti viereisiä ruutuja
//                } else if (ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
//                    peliIkkuna.painaNappiAlas(x + i, y + j);
//                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();
                }
            }
        }
    }
    
    public void liputaRuutu(int x, int y) {
        Ruutu tamaRuutu = ruudukko.getRuutu(x, y);
        
        peliIkkuna.liputa(x, y);
        if (tamaRuutu.getLiputettu() == false) {
            tamaRuutu.setLiputettu();
            lippuja++;
        } else {
            tamaRuutu.removeLiputettu();
            lippuja--;
        }
        loppuukoPeli();
    }
    
    public void loppuukoPeli() {
        if (miinoja == lippuja) {
            boolean onkoMiinatLiputettu = ruudukko.onkoMiinatLiputettu();
            if (ruudukko.onkoMiinatLiputettu()) {
                loppuAika = System.currentTimeMillis();
                long kulunutAikaSekunneissa = (loppuAika - alkuAika) / 1000;
                peliIkkuna.peliVoitettu(kulunutAikaSekunneissa);
            }
        }
    }

}
