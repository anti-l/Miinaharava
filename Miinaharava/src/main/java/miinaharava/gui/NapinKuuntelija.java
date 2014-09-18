package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author Antti
 */
public class NapinKuuntelija implements ActionListener {
    
    private RuutuNappi nappi;
    private Sovelluslogiikka sovelluslogiikka;
    private int x;
    private int y;
    
    public NapinKuuntelija(Sovelluslogiikka sovelluslogiikka, RuutuNappi nappi, int x, int y) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.nappi = nappi;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        sovelluslogiikka.tarkistaRuutu(x, y, nappi);
    }
    
}
