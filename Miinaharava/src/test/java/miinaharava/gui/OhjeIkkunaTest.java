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
public class OhjeIkkunaTest {
    
    private OhjeIkkuna ohjeet;
    
    @Before
    public void setUp() {
        ohjeet = new OhjeIkkuna();
    }
    
    @Test
    public void naytaIkkuna() {
        ohjeet.run();
    }
    
}
