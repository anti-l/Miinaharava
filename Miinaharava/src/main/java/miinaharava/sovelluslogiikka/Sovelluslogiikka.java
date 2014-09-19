package miinaharava.sovelluslogiikka;

import java.awt.Color;
import javax.swing.ImageIcon;
import miinaharava.domain.*;
import miinaharava.gui.RuutuNappi;
import miinaharava.gui.Kayttoliittyma;

/**
 *
 * @author Antti
 */
public class Sovelluslogiikka {

    private Ruudukko ruudukko;
    private Kayttoliittyma kayttoliittyma;

    public Sovelluslogiikka() {
        this.luoRuudukko();
    }
    
    public void luoRuudukko() {
        this.ruudukko = new Ruudukko();
    }
    
    public void luoRuudukko(int leveys, int korkeus, int miinoja) {
        this.ruudukko = new Ruudukko(leveys, korkeus, miinoja);
    }

    public void setKayttoliittyma(Kayttoliittyma kayttis) {
        this.kayttoliittyma = kayttis;
    }

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    public void tarkistaRuutu(int x, int y, RuutuNappi nappi) {
        Ruutu ruutu = this.ruudukko.getRuutu(x, y);
//        ruutu.katsoRuutu();
        if (ruutu.onkoMiinaa()) {
            nappi.setBackground(Color.RED);
//            nappi.setText("@");
            nappi.setIcon(new ImageIcon("minesweeper.gif"));
            //kayttoliittyma.gameOver();
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
        nappi.setEnabled(false);
    }

    public void paljastaTyhjat(int x, int y) {
        if (ruudukko.getRuutu(x, y).getKatsottu() == false) {
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
                        kayttoliittyma.painaNappiAlas(x, y);                            // Painetaan käyttöliittymän nappi alas
                        paljastaTyhjat(x + i, y + j);                                   // Kutsutaan rekursiivisesti viereisiä ruutuja
                    }
                }
            }
        }
    }
    
}
