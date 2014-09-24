package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.*;
import miinaharava.sovelluslogiikka.*;

/**
 *
 * @author Antti
 */
public class PeliIkkuna implements Runnable {
    
    private JFrame frame;
    private Ruudukko ruudukko;
    private Sovelluslogiikka sovelluslogiikka;
    private RuutuNappi[][] napisto;
    
    public PeliIkkuna(Sovelluslogiikka sovelluslogiikka) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.ruudukko = sovelluslogiikka.getRuudukko();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(ruudukko.getLeveys()*40, ruudukko.getLeveys()*40+50));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
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
                this.napisto[i][j].addMouseListener(new NapinKuuntelija(sovelluslogiikka, napisto[i][j], i, j));
                miinanapit.add(this.napisto[i][j]);
            }
        }
        return miinanapit;
    }
    
    public void gameOver() {
        JOptionPane.showMessageDialog(null, "Osuit miinaan ja hävisit!\nPeli on ohi.");
    }
    
    public void painaNappiAlas(int x, int y) {
        napisto[x][y].setEnabled(false);
    }
    
    public void liputa(int x, int y) {
        if (ruudukko.getRuutu(x, y).getLiputettu() == false) {
            napisto[x][y].setIcon(new ImageIcon("flag.gif"));
        } else {
            napisto[x][y].setIcon(new ImageIcon(""));
        }
    }
    
    public void miinoita(int x, int y) {
        napisto[x][y].setBackground(Color.RED);
        napisto[x][y].setIcon(new ImageIcon("minesweeper.gif"));
    }
    
    public void peliVoitettu(long aika) {
        JOptionPane.showMessageDialog(null, "Onneksi olkoon, voitit pelin!\nAika: " + aika + " sekuntia.");
    }
}
