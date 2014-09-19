package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
    private RuutuNappi[][] napisto;
    
    public Kayttoliittyma(Sovelluslogiikka sovelluslogiikka) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.ruudukko = sovelluslogiikka.getRuudukko();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        // Näin voidaan muuttaa ikkunan koko siten, että yksittäisen napin koko on aina sama
         frame.setPreferredSize(new Dimension(ruudukko.getLeveys()*40, ruudukko.getLeveys()*40+50));
        //frame.setPreferredSize(new Dimension(400, 450));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
    }
    
    public void luoKomponentit(Container container) {
        BorderLayout leiska = new BorderLayout();
        container.setLayout(leiska);
        
        String vaikeustaso = "";
        if (ruudukko.getLeveys() == 10) {
            vaikeustaso = "(helppo)";
        } else if (ruudukko.getLeveys() == 15) {
            vaikeustaso = "(keskivaikea)";
        } else if (ruudukko.getLeveys() == 20) {
            vaikeustaso = "(vaikea)";
        }
        
        container.add(new JLabel("Miinaharava #JavaLabra2014 - " + vaikeustaso), BorderLayout.NORTH);
        JPanel nappiruudukko = luoNapit(ruudukko.getLeveys(), ruudukko.getKorkeus());
        container.add(nappiruudukko);
    }
    
    public JPanel luoNapit(int x, int y) {
        JPanel miinanapit = new JPanel(new GridLayout(x, y));
        this.napisto = new RuutuNappi[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.napisto[i][j] = new RuutuNappi(ruudukko, i, j);
                this.napisto[i][j].addActionListener(new NapinKuuntelija(sovelluslogiikka, napisto[i][j], i, j));
                miinanapit.add(this.napisto[i][j]);
            }
        }
        //System.out.println("Napisto valmis");
        //System.out.println("napisto: " + napisto.length + " napisto[0]:" + napisto[0].length);
        return miinanapit;
    }
    
    public void gameOver() {
        // Tämä ei vielä tee mitään
        JFrame lasi = new JFrame("lasi");
        frame.setGlassPane(lasi);
    }
    
    public void painaNappiAlas(int x, int y) {
//        System.out.println("napisto:" + this.napisto);
//        System.out.println("napisto:" + this.napisto.length);
        //napisto.length aiheuttaa NullPointerExceptionin
        /*
            if (!(x < 0 || x > napisto.length)) {
                System.out.println("B");
                if (!(y < 0 || y > napisto[0].length)) {
                    napisto[x][y].setEnabled(false);
                }
            }
        /**/
    }
    
}
