/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
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
        this.sovelluslogiikka = new Sovelluslogiikka();
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
        container.add(luoNapit(ruudukko.getLeveys(), ruudukko.getKorkeus()));
    }
    
    public JPanel luoNapit(int x, int y) {
        JPanel miinanapit = new JPanel(new GridLayout(x, y));
        ArrayList<JButton> nappiruudukko = new ArrayList<JButton>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JButton nappi = new JButton("");
                nappi.setMargin(new Insets(0, 0, 0, 0));
//                NapinKuuntelija kuuntelija = new NapinKuuntelija(nappi);
                NapinKuuntelija kuuntelija;
                kuuntelija = new NapinKuuntelija(nappi, ruudukko.getRuutu(i, j));
                nappi.addActionListener(kuuntelija);
                nappiruudukko.add(nappi);
            }
        }
        for (JButton jb : nappiruudukko) {
            miinanapit.add(jb);
        }
        return miinanapit;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
