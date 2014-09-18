package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.*;
import miinaharava.sovelluslogiikka.*;

/**
 *
 * @author Antti
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Ruudukko ruudukko;
    private Sovelluslogiikka sovelluslogiikka;
    
    public Kayttoliittyma(Sovelluslogiikka sovelluslogiikka) {
        this.sovelluslogiikka = sovelluslogiikka;
        ruudukko = sovelluslogiikka.getRuudukko();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(300, 350));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
//        frame.setResizable(false);
    }
    
    public void luoKomponentit(Container container) {
        BorderLayout leiska = new BorderLayout();
        container.setLayout(leiska);
        container.add(new JLabel("Miinaharava #JavaLabra2014"), BorderLayout.NORTH);
        JPanel nappiruudukko = luoNapit(ruudukko.getLeveys(), ruudukko.getKorkeus());
        container.add(nappiruudukko);
    }
    
    public JPanel luoNapit(int x, int y) {
        JPanel miinanapit = new JPanel(new GridLayout(x, y));
        RuutuNappi[][] napisto = new RuutuNappi[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                napisto[i][j] = new RuutuNappi(ruudukko, i, j);
                napisto[i][j].addActionListener(new NapinKuuntelija(sovelluslogiikka, napisto[i][j], i, j));
                miinanapit.add(napisto[i][j]);
            }
        }
        return miinanapit;
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
