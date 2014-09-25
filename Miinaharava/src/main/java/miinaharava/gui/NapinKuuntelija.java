package miinaharava.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * NapinKuuntelija on MouseListener-luokasta peritty kustomoitu luokka, joka 
 * kuuntelee pelaajan toimia pelilaudalla. Pelaaja voi painaa pelilaudan nappeja
 * joko vasemmalla hiiden napilla, jolloin kyseinen ruutu tarkastetaan, tai
 * oikealla hiiren napilla, jolloin ruutuun asetetaan lippu miinan merkiksi.
 * 
 * @author Antti
 */
public class NapinKuuntelija implements MouseListener {

    private RuutuNappi nappi;
    private Sovelluslogiikka sovelluslogiikka;
    private int x;
    private int y;

    /**
     * Konstruktori, joka kuuntelee pelilaudan yhtä nappia.
     * @param sovelluslogiikka Pelin sovelluslogiikka
     * @param nappi Pelilaudan ruudukon nappi
     * @param x Pelilaudan napin x-koordinaatti
     * @param y Pelilaudan napin y-koordinaatti
     */
    public NapinKuuntelija(Sovelluslogiikka sovelluslogiikka, RuutuNappi nappi, int x, int y) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.nappi = nappi;
        this.x = x;
        this.y = y;
    }

    /**
     * Metodi, jota kutsutaan, kun pelaaja painaa yhtä hiiren nappia pelilaudan
     * Ruudun (napin) päällä.
     * 
     * Jos Ruutua painetaan hiiren vasemmalla painikkeella, ruutu valitaan ja
     * sen sisältö tarkistetaan. Jos taas käytetään oikeaa painiketta hiirellä,
     * Ruutu liputetaan miinan sisältäväksi.
     * 
     * @param me MouseEvent-instanssi, joka kertoo mitä hiiren nappia on painettu.
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            if (sovelluslogiikka.getRuudukko().getRuutu(x, y).getLiputettu() == false) {
                sovelluslogiikka.tarkistaRuutu(x, y, nappi);
                nappi.setEnabled(false);
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
