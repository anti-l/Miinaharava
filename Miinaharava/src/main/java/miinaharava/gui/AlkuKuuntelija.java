package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * Alkukuuntelija on luokka, joka tarkastelee käyttäjän toimia AlkuIkkunassa.
 * Luokka käynnistää erikokoisia pelilautoja pelaajan toiveiden mukaisesti.
 * @author Antti
 */
public class AlkuKuuntelija implements ActionListener {

    private Sovelluslogiikka sovelluslogiikka;
    private JRadioButton helppo;
    private JRadioButton medium;
    private JRadioButton vaikea;
    private JButton aloita;
    private PeliIkkuna peliIkkuna;

    
    /**
     * Konstruktori, joka saa parametreinään AlkuIkkunan komponenttien sisällön.
     * @param sovlog Pelin sovelluslogiikka.
     * @param helppo JRadioButton helpolle vaikeustasolle.
     * @param medium JRadioButton keskivaikealle vaikeustasolle.
     * @param vaikea JRadioButton vaikealle vaikeustasolle.
     * @param Aloita AlkuIkkunan Aloita-nappi.
     */
    public AlkuKuuntelija(Sovelluslogiikka sovlog, JRadioButton helppo, JRadioButton medium, JRadioButton vaikea, JButton Aloita) {
        this.sovelluslogiikka = sovlog;
        this.helppo = helppo;
        this.medium = medium;
        this.vaikea = vaikea;
        this.aloita = aloita;
    }

    /**
     * Kun Aloita-nappia painetaan, kutsutaan tätä metodia. Metodi tarkastaa,
     * mikä vaikeustaso on valittu, ja sen perusteella käskee sovelluslogiikkaa
     * luomaan valitun vaikeustason mukaisen pelilaudan.
     * @param ae ActionEvent, mikä pitää sisällään tiedon napin painamisesta.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (medium.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(15, 15, 35);
        } else if (vaikea.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(20, 20, 80);
        } else {
            this.sovelluslogiikka.luoRuudukko();
        }
        peliIkkuna = new PeliIkkuna(sovelluslogiikka);
        sovelluslogiikka.setPeliIkkuna(peliIkkuna);
        System.out.println(sovelluslogiikka.getRuudukko());
        peliIkkuna.run();

    }

}
