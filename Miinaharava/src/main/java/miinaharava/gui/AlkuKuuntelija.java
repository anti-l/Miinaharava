/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author Antti
 */
public class AlkuKuuntelija implements ActionListener {

    private Sovelluslogiikka sovelluslogiikka;
    private JRadioButton helppo;
    private JRadioButton medium;
    private JRadioButton vaikea;
    private JButton aloita;
    private Kayttoliittyma kayttoliittyma;

    public AlkuKuuntelija(Sovelluslogiikka sovlog, JRadioButton helppo, JRadioButton medium, JRadioButton vaikea, JButton Aloita) {
        this.sovelluslogiikka = sovlog;
        this.helppo = helppo;
        this.medium = medium;
        this.vaikea = vaikea;
        this.aloita = aloita;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (medium.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(15, 15, 35);
        } else if (vaikea.isSelected()) {
            this.sovelluslogiikka.luoRuudukko(20, 20, 80);
        } else {
            this.sovelluslogiikka.luoRuudukko();
        }
        kayttoliittyma = new Kayttoliittyma(sovelluslogiikka);
        System.out.println(sovelluslogiikka.getRuudukko());
        kayttoliittyma.run();

    }

}
