package miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * Alkuikkuna on pelin ensimmäinen ikkuna, josta ohjelman käyttäjä eli pelaaja
 * voi valita eri kokoisia pelilautoja ja käynnistää pelin.
 * @author Antti
 */
public class AlkuIkkuna implements Runnable {

    private JFrame frame;
    private Sovelluslogiikka sovelluslogiikka;
    
    /**
     * Konstruktori joka luo pelin alkuikkunan, josta pelaaja voi aloittaa eri
     * vaikeustasolla pelattavia pelejä.
     * @param sovlog Pelin sovelluslogiikka.
     */
    public AlkuIkkuna(Sovelluslogiikka sovlog) {
        this.sovelluslogiikka = sovlog;
    }
    
    /**
     * Metodi, joka luo aloitusikkunan komponentit - tekstikentät, napit ja radionapit.
     * @param container framen contentpane()
     */
    public void luoKomponentit(Container container) {
        // Luodaan radiobuttonit
        BorderLayout leiska = new BorderLayout();
        container.setLayout(leiska);
        
        JPanel radionapit = new JPanel();
        radionapit.setLayout(new GridLayout(5,1));
        JPanel customValinnat = new JPanel();
        customValinnat.setLayout(new GridLayout(5,2));
        JPanel napit = new JPanel();
        napit.setLayout(new FlowLayout());
        
        JLabel vaikeustasoteksti = new JLabel("Vaikeustaso:");
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
        
        // Lisätään kaikki radionapit yhteen JPaneliin
        radionapit.add(vaikeustasoteksti);
        radionapit.add(helppoPeli);
        radionapit.add(mediumPeli);
        radionapit.add(vaikeaPeli);
        
        // Custompelin valintalokerot
        JLabel tyhjateksti = new JLabel(" ");
        JLabel leveys = new JLabel("Leveys:");
        JLabel korkeus = new JLabel("Korkeus:");
        JLabel miinat = new JLabel("Miinoja:");
        JTextField leveysInput = new JTextField("10");
        JTextField korkeusInput = new JTextField("10");
        JTextField miinatInput = new JTextField("10");
        
        // Lisätään tekstit ja tekstikentät toiseen JPaneliin
        customValinnat.add(customPeli);
        customValinnat.add(tyhjateksti);
        customValinnat.add(leveys);
        customValinnat.add(leveysInput);
        customValinnat.add(korkeus);
        customValinnat.add(korkeusInput);
        customValinnat.add(miinat);
        customValinnat.add(miinatInput);
        
        // Pelin aloitusnappi, ohjenappi ja parhaiden tulosten nappi
        JButton aloita = new JButton("Aloita");
        JButton ohjeet = new JButton("Ohjeet");
        JButton tulokset = new JButton("Huipputulokset");

        // Lisätään napit viimeiseen JPaneliin
        napit.add(ohjeet);
        napit.add(tulokset);
        napit.add(aloita);

        // ActionListener napeille
        AlkuKuuntelija alkuKuuntelija = new AlkuKuuntelija(sovelluslogiikka, helppoPeli, mediumPeli, vaikeaPeli, customPeli, leveysInput, korkeusInput, miinatInput, aloita, ohjeet, tulokset);
        aloita.addActionListener(alkuKuuntelija);
        ohjeet.addActionListener(alkuKuuntelija);
        tulokset.addActionListener(alkuKuuntelija);
        
        container.add(radionapit, BorderLayout.LINE_START);
        container.add(customValinnat, BorderLayout.CENTER);
        container.add(napit, BorderLayout.PAGE_END);
    }

    /**
     * Metodi, joka käynnistää ikkunan. Ensin luodaan JFrame, jolle annetaan
     * sopiva koko. JFrameen luodaan sisältö luoKomponentit()-metodilla, ja
     * kun ne ovat valmiita, ikkunasta tehdään näkyvä.
     */
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(450, 225));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
}
