package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.domain.Tulos;
import miinaharava.sovelluslogiikka.Sovelluslogiikka;

/**
 * TulosIkkunalla näytetään käyttäjälle pelin parhaat tulokset. Itse tulokset
 * saadaan HuippuTulokset-luokasta, TulosIkkuna koostaa niistä käyttäjälle
 * esitettävän version.
 * @author Antti
 */
public class TulosIkkuna implements Runnable {
    
    private JFrame frame;
    private JPanel helppo, medium, vaikea;
    private Sovelluslogiikka sovlog;
    private ArrayList<Tulos> helppoTilasto, mediumTilasto, vaikeaTilasto;
    
    
    /**
     * Konstruktori, joka saa parametrinä pelin sovelluslogiikan. Tämä mahdollistaa
     * ikkunan integroinnin muuhun projektiin. Konstruktori hakee pelin parhaat
     * ajat ja tallettaa ne omaan attribuuttiinsa.
     * @param sovelluslogiikka Käynnissä olevan pelin logiikka.
     */
    public TulosIkkuna(Sovelluslogiikka sovelluslogiikka) {
        sovlog = sovelluslogiikka;
        helppoTilasto = sovlog.getHuippuTulokset().getHelpot();
        mediumTilasto = sovlog.getHuippuTulokset().getMediumit();
        vaikeaTilasto = sovlog.getHuippuTulokset().getVaikeat();
    }
    
    /**
     * Metodi luo ikkunan komponentit.
     * @param container Käytössä olevan ikkunan sisältö.
     */
    public void luoKomponentit(Container container) {
        JLabel tulokset = new JLabel(luoTulosTaulukko());
        container.add(tulokset);
    }
    
    /**
     * Metodi, joka luo TulosIkkunan esitettävän osan ja näyttää sen käyttäjälle.
     */
    @Override
    public void run() {
        frame = new JFrame("Miinaharavan huipputulokset");
        frame.setPreferredSize(new Dimension(570, 300));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Metodi, jonka vastuulla on luoda tulostaulukko (teksti) TulosIkkunaan.
     * Metodi hakee parhaat tulokset kolmeen eri pelimoodiin HuippuTuloksista
     * ja koostaa niistä selkeän merkkijonoesityksen, joka voidaan näyttää
     * TulosIkkunassa.
     * @return HTML-muodossa oleva tulostaulukko merkkijonona.
     */
    public String luoTulosTaulukko() {
        String tulokset = "<html><body><div align=center><table border=1 cellspacing=0 cellpadding=2 width='550'><tr><th>HELPPO</th><th>KESKIVAIKEA</th><th>VAIKEA</th></tr>";
        String tuloksetHelppo = "<table border=0 width=175><tr><th>Sija</th><th>Nimi</th><th>Aika</th></tr>";
        String tuloksetMedium = "<table border=0 width=175><tr><th>Sija</th><th>Nimi</th><th>Aika</th></tr>";
        String tuloksetVaikea = "<table border=0 width=175><tr><th>Sija</th><th>Nimi</th><th>Aika</th></tr>";

        for (int i = 0; i < 10; i++) {
            tuloksetHelppo += "<tr><td align=center>" + (i+1) + ".</td><td>" + helppoTilasto.get(i).getNimi() + "</td><td align=center>" + helppoTilasto.get(i).getAika() + "</td></tr>";
            tuloksetMedium += "<tr><td align=center>" + (i+1) + ".</td><td>" + mediumTilasto.get(i).getNimi() + "</td><td align=center>" + mediumTilasto.get(i).getAika() + "</td></tr>";
            tuloksetVaikea += "<tr><td align=center>" + (i+1) + ".</td><td>" + vaikeaTilasto.get(i).getNimi() + "</td><td align=center>" + vaikeaTilasto.get(i).getAika() + "</td></tr>";
        }
        tuloksetHelppo += "</table>";
        tuloksetMedium += "</table>";
        tuloksetVaikea += "</table>";
        
        tulokset += "<tr><td>" + tuloksetHelppo + "</td><td>" + tuloksetMedium + "</td><td>" + tuloksetVaikea + "</td></tr></table></div></body></html>";
        
        return tulokset;
    }
    
}
