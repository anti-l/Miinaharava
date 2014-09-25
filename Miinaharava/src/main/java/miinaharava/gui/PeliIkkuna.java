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
 * PeliIkkuna on luokka, joka sisältää pelilaudan nappeineen. PeliIkkunan
 * tekemät toiminnot ohjataan pelin SovellusLogiikalle.
 * 
 * @author Antti
 */
public class PeliIkkuna implements Runnable {
    
    private JFrame frame;
    private Ruudukko ruudukko;
    private Sovelluslogiikka sovelluslogiikka;
    private RuutuNappi[][] napisto;
    
    /**
     * Konstruktori, joka luo uuden pelilaudan. Konstruktori saa parametrinään
     * pelin sovelluslogiikan, jonka kautta peli toimii.
     * @param sovelluslogiikka 
     */
    public PeliIkkuna(Sovelluslogiikka sovelluslogiikka) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.ruudukko = sovelluslogiikka.getRuudukko();
    }
    
    /**
     * Metodi, joka luo ikkunan komponentit, säätää sen koon sopivaksi siten,
     * että pelilaudan napit ovat noin saman kokoisia eri vaikeustasoilla, ja
     * säätää pelilaudan näkyväksi.
     */
    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(ruudukko.getLeveys()*40, ruudukko.getLeveys()*40+50));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Metodi, joka luo PeliIkkunan komponentit eli pelilaudan napit ja muut
     * ikkunan osat.
     * @param container 
     */
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
    
    /**
     * Erillinen metodi pelilaudan nappien eli pelilaudan ruudukon luomiselle.
     * Tätä kutsutaan luoKomponentit() -metodista pelilaudan muodostamisen
     * yhteydessä. 
     * 
     * Metodi luo pelilaudan vaikeustason mukaisesti pelilaudan nappiruudukon
     * ja asettaa niille kuuntelijat.
     * @param x Nappiruudukon koko leveyssuunnassa
     * @param y Nappiruudukon koko pystysuunnassa
     * @return 
     */
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
    
    /**
     * Metodi, joka ilmoittaa pelaajalle häviöstä tämän osuttua miinaan.
     */
    public void gameOver() {
        JOptionPane.showMessageDialog(null, "Osuit miinaan ja hävisit!\nPeli on ohi.");
    }
    
    /**
     * Metodi, joka pelilaudan ruudun tarkastettua poistaa sen käytöstä, jotta
     * sitä ei voi valita enää uudelleen.
     * @param x Napin x-koordinaatti
     * @param y Napin y-koordinaatti
     */
    public void painaNappiAlas(int x, int y) {
        napisto[x][y].setEnabled(false);
    }
    
    /**
     * Metodi asettaa pelilaudan nappiruudukkoon haluttuihin koordinaatteihin
     * lipun, ja kertoo sovelluslogiikalle, että kyseinen ruutu on liputettu.
     * @param x Liputettavan ruudun x-koordinaatti
     * @param y Liputettavan ruudun y-koordinaatti
     */
    public void liputa(int x, int y) {
        if (ruudukko.getRuutu(x, y).getLiputettu() == false) {
            napisto[x][y].setIcon(new ImageIcon("flag.gif"));
        } else {
            napisto[x][y].setIcon(new ImageIcon(""));
        }
    }
    
    /**
     * Pelaajan painettua pelilaudan nappia, joka sisältää miinan, kutsutaan 
     * tätä metodia. Se muuttaa napin taustan punaiseksi ja paljastaa miinalogon
     * napin päälle.
     * @param x Miinan sisältävän ruudun x-koordinaatti
     * @param y Miinan sisältävän ruudun y-koordinaatti
     */
    public void miinoita(int x, int y) {
        napisto[x][y].setBackground(Color.RED);
        napisto[x][y].setIcon(new ImageIcon("minesweeper.gif"));
    }
    
    /**
     * Metodi, joka kertoo pelaajalle hänen onnistuneen pelissä ja voittaneen 
     * sen. Pelaajalle kerrotaan myös pelikentän pelaamiseen kulunut aika.
     * 
     * @param aika Pelin voittamiseen kulunut aika sekunneissa.
     */
    public void peliVoitettu(long aika) {
        JOptionPane.showMessageDialog(null, "Onneksi olkoon, voitit pelin!\nAika: " + aika + " sekuntia.");
    }
}
