package miinaharava.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import miinaharava.domain.Ruutu;
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

    private PeliIkkuna peliIkkuna;
    private Sovelluslogiikka sovlog;
    private int x;
    private int y;

    /**
     * Konstruktori, joka kuuntelee pelilaudan yhtä nappia.
     *
     * @param sovlog Pelin sovelluslogiikka
     * @param nappi Pelilaudan ruudukon nappi
     * @param x Pelilaudan napin x-koordinaatti
     * @param y Pelilaudan napin y-koordinaatti
     */
//    public NapinKuuntelija(Sovelluslogiikka sovlog, PeliIkkuna peliIkkuna, RuutuNappi nappi, int x, int y) {
    public NapinKuuntelija(Sovelluslogiikka sovelluslogiikka, PeliIkkuna peliIkkuna, int x, int y) {
        this.sovlog = sovelluslogiikka;
        this.peliIkkuna = peliIkkuna;
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
     * @param me MouseEvent-instanssi, joka kertoo mitä hiiren nappia on
     * painettu.
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        Ruutu ruutu = sovlog.getRuudukko().getRuutu(x, y);
        if (sovlog.onkoPeliOhi() == false) {
            if (me.getButton() == MouseEvent.BUTTON1) {
                if (ruutu.getLiputettu() == false) {
                    sovlog.tarkistaRuutu(x, y);
                    if (sovlog.getRuudukko().getRuutu(x, y).onkoMiinaa()) {
                        sovlog.setPeliOhi();
                        peliIkkuna.miinoita(x, y);
                        peliIkkuna.naytaMiinat();
                        peliIkkuna.gameOver();
                    } else {
                        peliIkkuna.asetaNapinTeksti(x, y, sovlog.ruudunTeksti(x, y));
                    }
                }
            } else if (me.getButton() == MouseEvent.BUTTON3) {
                peliIkkuna.liputa(x, y);
                sovlog.liputaRuutu(x, y);
            }
            peliIkkuna.paivitaNapit();
            if (sovlog.loppuukoPeli()) {
                peliIkkuna.peliVoitettu(sovlog.getPelinKesto());
            }
        }
    }

    /**
     * Ei käytössä.
     *
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
    }

    /**
     * Ei käytössä.
     *
     * @param me
     */
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    /**
     * Ei käytössä.
     *
     * @param me
     */
    @Override
    public void mouseEntered(MouseEvent me) {
    }

    /**
     * Ei käytössä.
     *
     * @param me
     */
    @Override
    public void mouseExited(MouseEvent me) {
    }

}
