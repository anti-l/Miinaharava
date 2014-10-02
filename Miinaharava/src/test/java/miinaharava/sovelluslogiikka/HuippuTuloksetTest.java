package miinaharava.sovelluslogiikka;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class HuippuTuloksetTest {
    
    HuippuTulokset tulokset;

    
    @Before
    public void setUp() {
        tulokset = new HuippuTulokset();
    }
    
    @Test
    public void luoTiedostoTest() {
        tulokset.luoUusiTiedosto();
    }
    
    @Test
    public void tulostaHelpotTest() {
        tulokset.tulostaHelpot();
    }
    
    @Test
    public void tarkastaHelppoTulosPaaseeListalle() {
        boolean tarkastus = tulokset.tarkastaHelppoTulos(99);
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastaHelppoTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaHelppoTulos(1000);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void sijoitaHelppoTulosTest() {
        tulokset.sijoitaHelppoTulos(10, "Peksi");
    }
    
}
