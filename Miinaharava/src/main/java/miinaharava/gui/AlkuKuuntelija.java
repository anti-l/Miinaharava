package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * Alkukuuntelija on luokka, joka tarkastelee käyttäjän toimia AlkuIkkunassa.
 * Luokka käynnistää erikokoisia pelilautoja pelaajan toiveiden mukaisesti.
 *
 * @author Antti
 */
public class AlkuKuuntelija implements ActionListener {

    private Sovelluslogiikka sovelluslogiikka;
    private JRadioButton helppo, medium, vaikea, custom;
    private JTextField leveys, korkeus, miinoja;
    private JButton aloita, ohjeet, tulokset;
    private PeliIkkuna peliIkkuna;

    /**
     * Konstruktori, joka saa parametreinään AlkuIkkunan komponenttien sisällön.
     *
     * @param sovlog Pelin sovelluslogiikka.
     * @param helppo JRadioButton helpolle vaikeustasolle.
     * @param medium JRadioButton keskivaikealle vaikeustasolle.
     * @param vaikea JRadioButton vaikealle vaikeustasolle.
     * @param custom JRadioButton vapaavalintaiselle pelille.
     * @param leveys AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon
     * leveydelle.
     * @param korkeus AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon
     * korkeudelle.
     * @param miinoja AlkuIkkunan tekstikenttä vapaavalintaisen peliruudukon
     * miinojen määrälle.
     * @param aloita AlkuIkkunan Aloita-nappi.
     * @param ohjeet AlkuIkkunan Ohjeet-nappi.
     * @param tulokset AlkuIkkunan Huipputulokset-nappi.
     * 
     */
    public AlkuKuuntelija(Sovelluslogiikka sovlog, JRadioButton helppo, 
            JRadioButton medium, JRadioButton vaikea, JRadioButton custom,
            JTextField leveys, JTextField korkeus, JTextField miinoja,
            JButton aloita, JButton ohjeet, JButton tulokset) {
        this.sovelluslogiikka = sovlog;
        this.helppo = helppo;
        this.medium = medium;
        this.vaikea = vaikea;
        this.aloita = aloita;
        this.ohjeet = ohjeet;
        this.tulokset = tulokset;
        this.custom = custom;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinoja = miinoja;
    }

    /**
     * Kun AlkuIkkunan nappia painetaan, kutsutaan tätä metodia. AlkuKuuntelija
     * kuuntelee kaikkia AlkuIkkunan kolmea nappia, Ohjeet-napista tulostetaan
     * käyttäjälle ohjeet, Huipputulokset-napista näytetään pelin parhaat
     * tulokset tähän asti. Jos painetaan Aloita-nappia, metodi tarkastaa, mikä
     * vaikeustaso on valittu, ja sen perusteella käskee sovelluslogiikkaa
     * luomaan valitun vaikeustason mukaisen pelilaudan.
     *
     * @param ae ActionEvent, mikä pitää sisällään tiedon napin painamisesta.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ohjeet) {
            System.out.println("Ohjeita kaivataan.");
            JOptionPane.showMessageDialog(null, "Paina nappuloita.\n[PLACEHOLDER]", "Miinaharava", JOptionPane.INFORMATION_MESSAGE);
        }
        if (ae.getSource() == tulokset) {
            System.out.println("Tulokset?");
            JOptionPane.showMessageDialog(null, "Paras tulos: helppo: 11 sec.\n[PLACEHOLDER]", "Miinaharava", JOptionPane.INFORMATION_MESSAGE);
        }
        if (ae.getSource() == aloita) {
            if (vaikea.isSelected()) {
                this.sovelluslogiikka.luoRuudukko(20, 20, 80);
            } else if (medium.isSelected()) {
                this.sovelluslogiikka.luoRuudukko(15, 15, 35);
            } else if (custom.isSelected()) {
                int customLeveys, customKorkeus, customMiinat;
                try {
                    customLeveys  = Integer.parseInt(leveys.getText());
                } catch (NumberFormatException e) {
                    customLeveys = 10;
                    leveys.setText("10");
                }
                try {
                    customKorkeus = Integer.parseInt(korkeus.getText());
                } catch (NumberFormatException e) {
                    customKorkeus = 10;
                    korkeus.setText("10");
                }
                try {
                    customMiinat  = Integer.parseInt(miinoja.getText());
                } catch (NumberFormatException e) {
                    customMiinat = 10;
                    miinoja.setText("10");
                }
                this.sovelluslogiikka.luoRuudukko(customKorkeus, customLeveys, customMiinat);
            } else {
                this.sovelluslogiikka.luoRuudukko();
            }
            peliIkkuna = new PeliIkkuna(sovelluslogiikka);
//            System.out.println(sovelluslogiikka.getRuudukko());     // Debuggausta ja testejä varten
            peliIkkuna.run();
        }
    }

}
