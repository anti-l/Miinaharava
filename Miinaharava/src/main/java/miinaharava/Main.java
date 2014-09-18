package miinaharava;
import javax.swing.SwingUtilities;
import miinaharava.domain.*;
import miinaharava.gui.*;
import miinaharava.sovelluslogiikka.*;

/**
 * Javalabra2014
 * 
 * @author Antti Lundén
 */
public class Main {
    
    public static void main(String[] args) {
        
        Sovelluslogiikka peli = new Sovelluslogiikka();
        AlkuIkkuna aloitus = new AlkuIkkuna();
        System.out.println(peli.getRuudukko());
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(aloitus);
//        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}
