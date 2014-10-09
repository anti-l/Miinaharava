package miinaharava.gui;

import java.awt.Container;
import miinaharava.domain.Ruudukko;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class TulosIkkunaTest {
    
    private TulosIkkuna tulos;
    private Sovelluslogiikka sovlog;
    
    @Before
    public void setUp() {
        sovlog = new Sovelluslogiikka();
        tulos = new TulosIkkuna(sovlog);
    }
    
    @Test
    public void naytaIkkunaTest() {
        tulos.run();
    }
    
}
