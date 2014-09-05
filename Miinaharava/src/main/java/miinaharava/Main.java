
package miinaharava;
import javax.swing.SwingUtilities;
import miinaharava.domain.*;
import miinaharava.gui.*;

/**
 * Javalabra2014
 * 
 * @author Antti Lundén
 */
public class Main {
    
    public static void main(String[] args) {
        
        Ruudukko pelilauta = new Ruudukko();
        System.out.println(pelilauta);
        
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}
