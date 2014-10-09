package miinaharava.sovelluslogiikka;

import miinaharava.domain.Vaikeustaso;
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
    public void tarkastaHelppoTulosPaaseeListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(99, Vaikeustaso.HELPPO);
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastaHelppoTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(1000, Vaikeustaso.HELPPO);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void sijoitaHelppoTulosTest() {
        tulokset.sijoitaTulos(10, "Peksi", Vaikeustaso.HELPPO);
    }
    
}
