package miinaharava.gui;

import java.awt.Insets;
import javax.swing.JButton;
import miinaharava.domain.*;

/**
 * RuutuNappi on JButton-luokasta peritty nappi, jolla on myös tieto omasta
 * sijainnistaan peliruudukossa. JButtonia on myös muokattu hieman, poistettu
 * marginaalit että pelilaudan tekstit ja kuvakkeet näkyvät siinä paremmin.
 * @author Antti
 */
public class RuutuNappi extends JButton {
    
    private Ruudukko ruudukko;
    private int x;
    private int y;
    
    public RuutuNappi(Ruudukko ruudukko, int x, int y) {
        this.x = x;
        this.y = y;
        this.ruudukko = ruudukko;
        super.setMargin(new Insets(0, 0, 0, 0));
    }
    
}
