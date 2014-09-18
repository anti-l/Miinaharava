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

    public void tarkistaRuutu(int x, int y, RuutuNappi nappi) {
        Ruutu ruutu = this.ruudukko.getRuutu(x, y);
        ruutu.katsoRuutu();
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

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    public void paljastaTyhjat(int x, int y) {
        if (!(ruudukko.getRuutu(x, y).getKatsottu())) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == x && j == y) {
                        continue;
                    } else if (x + i < 0 || x + i >= ruudukko.getLeveys()) {
                        continue;
                    } else if (y + j < 0 || y + j >= ruudukko.getKorkeus()) {
                        continue;
                    } else if (ruudukko.getRuutu((x + i), (y + j)).onkoTyhja() && ruudukko.getRuutu((x + i), (y + j)).getKatsottu()) {
                        ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();
//                        kayttoliittyma.painaNappiAlas(x, y);
                        paljastaTyhjat(x + i, y + j);
                    }
                }
            }
        }
    }
    
    public void setKayttoliittyma(Kayttoliittyma kayttis) {
        this.kayttoliittyma = kayttis;
    }
}
