/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.gui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *
 * @author Antti
 */
public class AlkuIkkuna implements Runnable {

    private JFrame frame;
    
    public AlkuIkkuna() {
        /*
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(300, 350));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        /**/
    }
    
    public void luoKomponentit(Container container) {
        // Luodaan radiobuttonit
        GridLayout leiska = new GridLayout(4,1);
        container.setLayout(leiska);
        JRadioButton helppoPeli = new JRadioButton("Helppo (10x10, 10 miinaa)");
        JRadioButton mediumPeli = new JRadioButton("Keskivaikea (15x15, 35 miinaa)");
        JRadioButton vaikeaPeli = new JRadioButton("Vaikea (20x20, 80 miinaa)");
        
        // Laitetaan kaikki samaan grouppiin, jotta vain yhden voi valita
        ButtonGroup peliValinta = new ButtonGroup();
        peliValinta.add(helppoPeli);
        peliValinta.add(mediumPeli);
        peliValinta.add(vaikeaPeli);
        helppoPeli.setSelected(true);
        
        // ActionListener napeille
        AlkuKuuntelija alkuKuuntelija = new AlkuKuuntelija();
        helppoPeli.addActionListener(alkuKuuntelija);
        mediumPeli.addActionListener(alkuKuuntelija);
        vaikeaPeli.addActionListener(alkuKuuntelija);
        
        container.add(helppoPeli);
        container.add(mediumPeli);
        container.add(vaikeaPeli);
        
        JButton aloita = new JButton("Aloita");
        aloita.addActionListener(alkuKuuntelija);
        container.add(aloita);
        
        
        
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        //
    }
}