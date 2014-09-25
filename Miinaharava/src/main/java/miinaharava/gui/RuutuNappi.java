package miinaharava.gui;

import java.awt.Insets;
import javax.swing.JButton;
import miinaharava.domain.*;

/**
 * RuutuNappi on JButton-luokasta peritty nappi, jonka marginaaleja on muutettu.
 * @author Antti
 */
public class RuutuNappi extends JButton {

    /**
     * Konstruktori, joka luo uuden RuutuNapin. 
     */
    public RuutuNappi() {
        super.setMargin(new Insets(0, 0, 0, 0));
    }
    
}
