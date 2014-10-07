package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    public TulosIkkuna(Sovelluslogiikka sovelluslogiikka) {
        this.sovlog = sovelluslogiikka;
    }
    
    public void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(1,3));
        
        helppo = new JPanel();
        medium = new JPanel();
        vaikea = new JPanel();
        
        helppo.setLayout(new GridLayout(12,3));
        medium.setLayout(new GridLayout(12,3));
        vaikea.setLayout(new GridLayout(12,3));
        ArrayList<Tulos> helppoTilasto = sovlog.getHuippuTulokset().getHelpot();
        ArrayList<Tulos> mediumTilasto = sovlog.getHuippuTulokset().getMediumit();
        ArrayList<Tulos> vaikeaTilasto = sovlog.getHuippuTulokset().getVaikeat();
        
        helppo.add(new JLabel(""));
        helppo.add(new JLabel("HELPPO"));
        helppo.add(new JLabel(""));
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
        
        medium.add(new JLabel(""));
        medium.add(new JLabel("KESKIVAIKEA"));
        medium.add(new JLabel(""));
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
        
        vaikea.add(new JLabel(""));
        vaikea.add(new JLabel("VAIKEA"));
        vaikea.add(new JLabel(""));
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
        
        container.add(helppo);
        container.add(medium);
        container.add(vaikea);
        
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
