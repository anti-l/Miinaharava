/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import miinaharava.domain.*;

/**
 *
 * @author Antti
 */
public class NapinKuuntelija implements ActionListener {
    
    private JButton nappi;
//    private Random rand;
    private Ruutu ruutu;
    
    public NapinKuuntelija(JButton nappi) {
        this.nappi = nappi;
//        rand = new Random();
    }

    public NapinKuuntelija(JButton nappi, Ruutu ruutu) {
        this.nappi = nappi;
        this.ruutu = ruutu;
//        rand = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        System.out.println("Painoit nappia ");
        if (ruutu.onkoMiinaa()) {
            nappi.setText("#");
        } else {
            nappi.setText("");
        }
//        nappi.setText("" + rand.nextInt(5));
        nappi.setEnabled(false);
        
    }
    
}
