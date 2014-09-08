package miinaharava.sovelluslogiikka;

import miinaharava.domain.*;

/**
 *
 * @author Antti
 */
public class Sovelluslogiikka {
    
    Ruudukko ruudukko;
    
    public Sovelluslogiikka() {
        ruudukko = new Ruudukko();
    }
    
    public void yritaRuutua(int x, int y) {
        if (ruudukko.getRuutu(x, y).onkoMiinaa()) {
            System.out.println("Osuit miinaan!");
        }
    }
    
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }
}
