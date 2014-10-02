package miinaharava.gui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class AlkuKuuntelijaTest {

    Sovelluslogiikka sovlog;
    PeliIkkuna peli;
    AlkuKuuntelija alku;
    JButton aloita, ohjeet, tulokset;
    JRadioButton helppo, medium, vaikea, custom;
    JTextField leveys, korkeus, miinoja;

    @Before
    public void setUp() {
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        aloita = new JButton("Aloita");
        ohjeet = new JButton("Ohjeet");
        tulokset = new JButton("Tulokset");
        helppo = new JRadioButton();
        medium = new JRadioButton();
        vaikea = new JRadioButton();
        custom = new JRadioButton();
        alku = new AlkuKuuntelija(sovlog, helppo, medium, vaikea, custom, leveys, korkeus, miinoja, aloita, ohjeet, tulokset);
    }

    @Test
    public void konstruktoriTesti() {
        /*
        sovlog = new Sovelluslogiikka();
        peli = new PeliIkkuna(sovlog);
        aloita = new JButton("Aloita");
        helppo = new JRadioButton();
        medium = new JRadioButton();
        vaikea = new JRadioButton();
        alku = new AlkuKuuntelija(sovlog, helppo, medium, vaikea, aloita);
        */
    }
    
    
    /*
    @Test
    public void helppoTest() {
        helppo.setSelected(true);
//        ActionEvent ae = new ActionEvent(aloita, 0);
        alku.actionPerformed(aloita);
    }

    @Test
    public void mediumTest() {
        medium.setSelected(true);
        alku.actionPerformed(null);
    }

    @Test
    public void vaikeaTest() {
        vaikea.setSelected(true);
        alku.actionPerformed(null);
    }
    /**/
    
}
