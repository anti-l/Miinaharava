package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
    private JRadioButton custom;
    private JTextField leveys, korkeus, miinoja;
    private JButton aloita;
    private PeliIkkuna peliIkkuna;

    
    /**
     * Konstruktori, joka saa parametreinään AlkuIkkunan komponenttien sisällön.
     * @param sovlog Pelin sovelluslogiikka.
     * @param helppo JRadioButton helpolle vaikeustasolle.
     * @param medium JRadioButton keskivaikealle vaikeustasolle.
     * @param vaikea JRadioButton vaikealle vaikeustasolle.
     * @param custom JRadioButton vapaavalintaiselle pelille.
     * @param leveys AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon leveydelle.
     * @param korkeus AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon korkeudelle.
     * @param miinoja AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon miinojen määrälle.
     * @param aloita AlkuIkkunan Aloita-nappi.
     */
    public AlkuKuuntelija(Sovelluslogiikka sovlog, JRadioButton helppo, JRadioButton medium, JRadioButton vaikea, JRadioButton custom, JTextField leveys, JTextField korkeus, JTextField miinoja, JButton aloita) {
        this.sovelluslogiikka = sovlog;
        this.helppo = helppo;
        this.medium = medium;
        this.vaikea = vaikea;
        this.aloita = aloita;
        this.custom = custom;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
    }

    /**
     * Kun Aloita-nappia painetaan, kutsutaan tätä metodia. Metodi tarkastaa,
     * mikä vaikeustaso on valittu, ja sen perusteella käskee sovelluslogiikkaa
     * luomaan valitun vaikeustason mukaisen pelilaudan.
     * @param ae ActionEvent, mikä pitää sisällään tiedon napin painamisesta.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (vaikea.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(20, 20, 80);
        } else if (medium.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(15, 15, 35);
        } else if (custom.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(Integer.parseInt(leveys.getText()), Integer.parseInt(korkeus.getText()), Integer.parseInt(miinoja.getText()));
        } else {
            this.sovelluslogiikka.luoRuudukko();
        }
        peliIkkuna = new PeliIkkuna(sovelluslogiikka);
        System.out.println(sovelluslogiikka.getRuudukko());
        peliIkkuna.run();

    }

}
