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
    
    /**
     * Konstruktori, joka luo uuden RuutuNapin. Samalla napille kerrotaan sen
     * koordinaatit ja ruudukko, jossa se sijaitsee.
     * @param ruudukko Napin alustana oleva Ruudukko.
     * @param x Napin x-koordinaatti.
     * @param y Napin y-koordinaatti. 
     */
    public RuutuNappi(Ruudukko ruudukko, int x, int y) {
        this.x = x;
        this.y = y;
        this.ruudukko = ruudukko;
        super.setMargin(new Insets(0, 0, 0, 0));
    }
    
}
