package miinaharava;
import javax.swing.SwingUtilities;
import miinaharava.gui.*;
import miinaharava.sovelluslogiikka.*;

/**
 * Tämä on projektin pääluokka, joka käynnistää sen eri osat.
 * Projekti on harjoitustyö kurssille Ohjelmoinnin harjoitustyö, I/2014.
 * #Javalabra2014
 * 
 * @author Antti Lundén
 */
public class Main {
    
    /**
     * Pääluokka Miinaharava-pelille. Käynnistää sovelluslogiikan, aloitusikkunan ja pisteiden kirjaamisen.
     * @param args Ajoaikaiset parametrit (ei käytössä).
     */
    public static void main(String[] args) {
        
        Sovelluslogiikka peli = new Sovelluslogiikka();
        AlkuIkkuna aloitus = new AlkuIkkuna(peli);
        HuippuTulokset huipputulokset = new HuippuTulokset();
        SwingUtilities.invokeLater(aloitus);
    }
    
}
