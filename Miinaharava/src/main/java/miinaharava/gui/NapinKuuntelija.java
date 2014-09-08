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
    private Ruutu ruutu;
    
    public NapinKuuntelija(JButton nappi) {
        this.nappi = nappi;
    }

    public NapinKuuntelija(JButton nappi, Ruutu ruutu) {
        this.nappi = nappi;
        this.ruutu = ruutu;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ruutu.onkoMiinaa()) {
            nappi.setText("#");
        } else {
            String napinTeksti = "";
            if (ruutu.getViereiset() > 0) {
                napinTeksti += ruutu.getViereiset();
            }
            nappi.setText(napinTeksti);
        }
        this.ruutu.katsoRuutu();
        nappi.setEnabled(false);
    }
    
}
