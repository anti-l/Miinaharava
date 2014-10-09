package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import miinaharava.domain.Tulos;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author Antti
 */
public class TulosIkkuna implements Runnable {
    
    private JFrame frame;
    private JPanel helppo, medium, vaikea;
    private Sovelluslogiikka sovlog;
    private ArrayList<Tulos> helppoTilasto, mediumTilasto, vaikeaTilasto;
    
    public TulosIkkuna(Sovelluslogiikka sovelluslogiikka) {
        sovlog = sovelluslogiikka;
        helppoTilasto = sovlog.getHuippuTulokset().getHelpot();
        mediumTilasto = sovlog.getHuippuTulokset().getMediumit();
        vaikeaTilasto = sovlog.getHuippuTulokset().getVaikeat();
    }
    
    public void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());

        JPanel otsikot = new JPanel();
        otsikot.setLayout(new GridLayout(1,3));
        JLabel otsikkoHelppo = new JLabel("HELPPO");
        JLabel otsikkoMedium = new JLabel("KESKIVAIKEA");
        JLabel otsikkoVaikea = new JLabel("VAIKEA");
        otsikkoHelppo.setHorizontalAlignment(SwingConstants.CENTER);
        otsikkoMedium.setHorizontalAlignment(SwingConstants.CENTER);
        otsikkoVaikea.setHorizontalAlignment(SwingConstants.CENTER);
        otsikot.add(otsikkoHelppo);
        otsikot.add(otsikkoMedium);
        otsikot.add(otsikkoVaikea);
        container.add(otsikot, BorderLayout.PAGE_START);
        
        helppo = new JPanel();
        medium = new JPanel();
        vaikea = new JPanel();
        
        helppo.setLayout(new GridLayout(11, 3));
        medium.setLayout(new GridLayout(11, 3));
        vaikea.setLayout(new GridLayout(11, 3));
        

        helppo.add(new JLabel("Sija"));
        helppo.add(new JLabel("Nimi"));
        helppo.add(new JLabel("Aika"));
        int jarjestysLuku = 1;
        for (Tulos tulos : helppoTilasto) {
            helppo.add(new JLabel(jarjestysLuku + "."));
            helppo.add(new JLabel(tulos.getNimi()));
            helppo.add(new JLabel("" + tulos.getAika()));
            jarjestysLuku++;
        }
        
        medium.add(new JLabel("Sija"));
        medium.add(new JLabel("Nimi"));
        medium.add(new JLabel("Aika"));
        jarjestysLuku = 1;
        for (Tulos tulos : mediumTilasto) {
            medium.add(new JLabel(jarjestysLuku + "."));
            medium.add(new JLabel(tulos.getNimi()));
            medium.add(new JLabel("" + tulos.getAika()));
            jarjestysLuku++;
        }
        
        vaikea.add(new JLabel("Sija"));
        vaikea.add(new JLabel("Nimi"));
        vaikea.add(new JLabel("Aika"));
        jarjestysLuku = 1;
        for (Tulos tulos : vaikeaTilasto) {
            vaikea.add(new JLabel(jarjestysLuku + "."));
            vaikea.add(new JLabel(tulos.getNimi()));
            vaikea.add(new JLabel("" + tulos.getAika()));
            jarjestysLuku++;
        }
        
        JPanel tulokset = new JPanel();
        tulokset.setLayout(new GridLayout(1,3));
        tulokset.add(helppo);
        tulokset.add(medium);
        tulokset.add(vaikea);
        
        container.add(tulokset);
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Miinaharavan huipputulokset");
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
