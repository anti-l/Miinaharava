package miinaharava.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Ikkuna, jossa näytetään ohjeet pelin pelaamiseen.
 *
 * @author Antti
 */
public class OhjeIkkuna implements Runnable {
    
    JFrame frame;
    
    public OhjeIkkuna() {
    }
    
    public void luoKomponentit(final Container container) {
        JLabel tekstiEka = new JLabel();
        
        tekstiEka.setText("<html><body style=\"width:100%\">"
                + "<h1>Ohjeet</h1>"
                + "<p>Peli koostuu ruudukosta, jonka alla on valitsemasi määrä miinoja. Sinun on etsittävä miinat ruudusta ja merkittävä niiden paikat merkkilipuilla. Kun kaikki miinat on oikein liputettu, peli päättyy voittoon.</p><br>"
                + "<p>Peliä pelataan valitsemalla ruutu ja painamalla sitä hiiren vasemmalla näppäimellä. Valittuasi ruudun peli tarkastaa, onko ruudussa miinaa. Jos osut miinaan, häviät ja peli on ohi. Jos ruudussa ei ole miinaa, peli paljastaa kyseisen ruudun pelilaudalta ja ilmoittaa numeroin, kuinka monta miinaa viereisissä ruuduissa on. Jos ruudussa tai sen viereisissä ruuduissa ei ole miinaa, peli paljastaa kaikki viereiset ruudut, jotka ovat myös tyhjiä, kunnes löytää ruutuja, joiden vieressä on miinoja.</p><br>"
                + "<p>Voit liputtaa ruudun, jossa mielestäsi on miina hiiren oikealla napilla. Tällöin ruutuun asetetaan lippu, ja sitä ei enää voi valita ottamatta lippua pois. Sinun on asetettava pelialueelle liput jokaisen miinan päälle voittaaksesi pelin. Kun lippuja on yhtä paljon kuin miinoja pelialueella ja ne ovat samoissa paikoissa, peli päättyy voittoon, ja saat tietää pelin pelaamiseen kuluneen ajan.</p> "
                + "</body></html>"
        );
        
        container.add(tekstiEka);
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Miinaharavan ohjeet");
        frame.setPreferredSize(new Dimension(400, 450));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
