package miinaharava.gui;

import javax.swing.JLabel;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author Antti
 */
public class PeliKello implements Runnable {

    private JLabel kelloKentta;
    private Sovelluslogiikka sovlog;
    private boolean kelloKay;

    /**
     * Konstruktori, joka luo PeliKello-olion pitämään visuaalisesti kirjaa
     * peliin kuluneesta ajasta
     *
     * @param kelloKentta PeliIkkunan tekstikenttä
     * @param sovlog Käytössä oleva sovelluslogiikka
     */
    public PeliKello(JLabel kelloKentta, Sovelluslogiikka sovlog) {
        this.kelloKentta = kelloKentta;
        this.sovlog = sovlog;
        this.kelloKay = false;
    }

    /**
     * Metodi pysäyttää pelikellon päivittämisen PeliIkkunassa.
     */
    public void pysaytaKello() {
        this.kelloKay = false;
    }

    /**
     * Metodi käynnistää kellon päivittämisen PeliIkkunassa.
     */
    public void kelloKayntiin() {
        this.kelloKay = true;
    }

    /**
     * Metodi, jota kutsutaan, kun peli käynnistyy ja kello säikeistetään omaan
     * säikeeseensä.
     */
    @Override
    public void run() {
        while (kelloKay) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            paivitaKello();
        }
    }

    public void paivitaKello() {
        int kelloNyt = (int) sovlog.getAika();
        int minuutit = kelloNyt / 60;
        int sekunnit = kelloNyt % 60;
        String aikaTeksti = "" + minuutit + ":";
        if (sekunnit < 10) {
            aikaTeksti += "0" + sekunnit;
        } else {
            aikaTeksti += "" + sekunnit;
        }

        kelloKentta.setText(aikaTeksti);

    }

}
