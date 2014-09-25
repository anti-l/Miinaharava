package miinaharava.gui;

import java.awt.Container;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class AlkuIkkunaTest {
    
    Sovelluslogiikka sovlog;
    AlkuIkkuna alku;
    
    @Test
    public void konstruktoriTest() {
        sovlog = new Sovelluslogiikka();
        alku = new AlkuIkkuna(sovlog);
    }
    
    @Test
    public void komponenttienLuontiTest() {
        sovlog = new Sovelluslogiikka();
        alku = new AlkuIkkuna(sovlog);
        alku.luoKomponentit(new Container());
    }
    
    @Test
    public void ajoTesti() {
        sovlog = new Sovelluslogiikka();
        alku = new AlkuIkkuna(sovlog);
        alku.run();
    }
}
