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

/**
 *
 * @author Antti
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    
    public Kayttoliittyma() {
        
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
        /*        
        JButton uusiPeli = new JButton("Uusi peli");
        container.add(uusiPeli);
        */
        BorderLayout leiska = new BorderLayout();
        container.setLayout(leiska);
        container.add(new JLabel("Miinaharava #JavaLabra2014"), BorderLayout.NORTH);
        container.add(luoNapit(10, 10));
        
    }
    
    public JPanel luoNapit(int x, int y) {
        JPanel miinanapit = new JPanel(new GridLayout(x, y));
        
        ArrayList<JButton> nappiruudukko = new ArrayList<JButton>();
        for (int i = 0; i < (x * y); i++) {
            JButton nappi = new JButton("");
            nappi.setMargin(new Insets(0,0,0,0));
            nappiruudukko.add(nappi);
        }
        for (JButton jb : nappiruudukko) {
            miinanapit.add(jb);
        }

        // testi, miten poistaa kerran painettu nappi käytöstä
        nappiruudukko.get(45).setEnabled(false);
        nappiruudukko.get(34).setEnabled(false);
        nappiruudukko.get(23).setEnabled(false);
        nappiruudukko.get(23).setText("1");
        nappiruudukko.get(24).setText("1");
        return miinanapit;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
