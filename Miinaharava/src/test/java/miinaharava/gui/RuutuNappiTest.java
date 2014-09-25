package miinaharava.gui;

import java.awt.Insets;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RuutuNappiTest {
    
    
    @Test
    public void luoRuutuNappi() {
        RuutuNappi nappi = new RuutuNappi();
        Insets marginaalit = nappi.getMargin();
        Insets verrokki = new Insets(0, 0, 0, 0);
        assertEquals(true, marginaalit.equals(verrokki));
    }
    
    
}
