package miinaharava;
import javax.swing.SwingUtilities;
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
        AlkuIkkuna aloitus = new AlkuIkkuna(peli);
        PeliIkkuna peliIkkuna = new PeliIkkuna(peli);
        peli.setPeliIkkuna(peliIkkuna);
        SwingUtilities.invokeLater(aloitus);
    }
    
}
