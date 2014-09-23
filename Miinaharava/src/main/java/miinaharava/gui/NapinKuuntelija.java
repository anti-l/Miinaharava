package miinaharava.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * Tämä ehkä pitää muuttaa myöhemmin MouseListeneriksi, jotta saadaan molempien
 * hiirennappien käyttö peliin mukaan.
 *
 * @author Antti
 */
public class NapinKuuntelija implements MouseListener {

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
    public void mouseReleased(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            if (sovelluslogiikka.getRuudukko().getRuutu(x, y).getLiputettu() == false) {
                sovelluslogiikka.tarkistaRuutu(x, y, nappi);
            }
        } else if (me.getButton() == MouseEvent.BUTTON3) {
            sovelluslogiikka.liputaRuutu(x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
