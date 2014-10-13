package miinaharava.gui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class VihjeKuuntelijaTest {
    
    private Sovelluslogiikka sovlog;
    private PeliIkkuna peliIkkuna;
    private VihjeKuuntelija viku;
    private PeliKello kello;
    private JButton vihje;
    private JLabel kellonLabel;
    
    @Before
    public void setUp() {
        sovlog = new Sovelluslogiikka();
        sovlog.luoRuudukko(5, 5, 1);
        peliIkkuna = new PeliIkkuna(sovlog);
        vihje = new JButton("vihje");
        kellonLabel = new JLabel("0:00");
        kello = new PeliKello(kellonLabel, sovlog);
        viku = new VihjeKuuntelija(vihje, sovlog, peliIkkuna, kello);
    }
    
    @Test
    public void actionPerformedTest() {
//        Object o = new Object();
//        viku.actionPerformed(new ActionEvent(o, 0, ""));
    }
    
}
