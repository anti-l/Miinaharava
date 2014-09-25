package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * Alkuikkuna on pelin ensimmäinen ikkuna, josta ohjelman käyttäjä eli pelaaja
 * voi valita eri kokoisia pelilautoja ja käynnistää pelin.
 * @author Antti
 */
public class AlkuIkkuna1 implements Runnable {

    private JFrame frame;
    private Sovelluslogiikka sovelluslogiikka;
    
    /**
     * Konstruktori joka luo pelin alkuikkunan, josta pelaaja voi aloittaa eri
     * vaikeustasolla pelattavia pelejä.
     * @param sovlog Pelin sovelluslogiikka.
     */
    public AlkuIkkuna1(Sovelluslogiikka sovlog) {
        this.sovelluslogiikka = sovlog;
    }
    
    /**
     * Metodi, joka luo aloitusikkunan komponentit - tekstikentät, napit ja radionapit.
     * @param container 
     */
    public void luoKomponentit(Container container) {
        // Luodaan radiobuttonit
        GridLayout leiska = new GridLayout(4,1);
        container.setLayout(leiska);
        JRadioButton helppoPeli = new JRadioButton("Helppo (10x10, 10 miinaa)");
        JRadioButton mediumPeli = new JRadioButton("Keskivaikea (15x15, 35 miinaa)");
        JRadioButton vaikeaPeli = new JRadioButton("Vaikea (20x20, 80 miinaa)");
        JRadioButton customPeli = new JRadioButton("Oma valinta");
        
        // Laitetaan kaikki samaan grouppiin, jotta vain yhden voi valita
        ButtonGroup peliValinta = new ButtonGroup();
        peliValinta.add(helppoPeli);
        peliValinta.add(mediumPeli);
        peliValinta.add(vaikeaPeli);
        peliValinta.add(customPeli);
        helppoPeli.setSelected(true);
        
        // Custompelin valintalokerot
        JLabel leveys = new JLabel("Leveys:");
        JTextArea leveysInput = new JTextArea("10");
        JLabel korkeus = new JLabel("Leveys:");
        JTextArea korkeusInput = new JTextArea("10");
        JLabel miinat = new JLabel("Leveys:");
        JTextArea miinatInput = new JTextArea("10");
        
        // Pelin aloitusnappi
        JButton aloita = new JButton("Aloita");

        // ActionListener napeille
        AlkuKuuntelija alkuKuuntelija = new AlkuKuuntelija(sovelluslogiikka, helppoPeli, mediumPeli, vaikeaPeli, aloita);
        aloita.addActionListener(alkuKuuntelija);
        
        container.add(helppoPeli);
        container.add(mediumPeli);
        container.add(vaikeaPeli);
        container.add(customPeli);
        container.add(aloita);
    }

    /**
     * Metodi, joka käynnistää ikkunan. Ensin luodaan JFrame, jolle annetaan
     * sopiva koko. JFrameen luodaan sisältö luoKomponentit()-metodilla, ja
     * kun ne ovat valmiita, ikkunasta tehdään näkyvä.
     */
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
}
