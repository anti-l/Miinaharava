/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    public void painaNappia(int x, int y) {
        testaaRuutu(x, y);
        testaaViereisetRuudut(x, y);
    }
    
    public void testaaRuutu(int x, int y) {
        if (ruudukko.getRuutu(x, y).onkoMiinaa()) {
            System.out.println("Osuit miinaan!");
        }

    }
    public void testaaViereisetRuudut(int x, int y) {
        
    }
    
    
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }
}
