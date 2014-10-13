package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author Antti
 */
public class VihjeKuuntelija implements ActionListener {

    private JButton vihjeNappi;
    private Sovelluslogiikka sovlog;
    private PeliIkkuna peliIkkuna;
    private PeliKello peliKello;

    /**
     * Konstruktori, joka luo uuden VihjeKuuntelijan PeliIkkunan Vihje-napille.
     *
     * @param vihje PeliIkkunan vihje-nappi
     * @param sovlog Käytössä oleva Sovelluslogiikka
     * @param peliIkkuna Käytössä oleva PeliIkkuna
     * @param peliKello Käytössä oleva PeliKello
     */
    public VihjeKuuntelija(JButton vihje, Sovelluslogiikka sovlog, PeliIkkuna peliIkkuna, PeliKello peliKello) {
        this.vihjeNappi = vihje;
        this.sovlog = sovlog;
        this.peliIkkuna = peliIkkuna;
        this.peliKello = peliKello;
    }

    /**
     * Metodi, joka kuuntelee napin painalluksia. Nappia painamalla
     * sovelluslogiikalta haetaan yhden ruudun koordinaatit, joiden avulla
     * PeliIkkunassa merkitään yksi ruutu miinoja sisältäväksi.
     *
     * @param ae ActionEvent, joka herää napin painallukseen.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!sovlog.onkoPeliOhi()) {
            int[] koordinaatit = sovlog.annaVihje();
            if (koordinaatit != null) {
                peliIkkuna.naytaVihje(koordinaatit[0], koordinaatit[1]);
                sovlog.liputaRuutu(koordinaatit[0], koordinaatit[1]);
                peliIkkuna.paivitaNapit();
                peliIkkuna.miinojaVahenna();
                peliKello.paivitaKello();
            }
            if (sovlog.loppuukoPeli()) {
                peliIkkuna.peliVoitettu(sovlog.getPelinKesto());
            }
        }
    }
}
