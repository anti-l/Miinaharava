package miinaharava.sovelluslogiikka;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import miinaharava.domain.*;

/**
 *
 * @author Antti
 */
public class HuippuTuloksetTest {
    
    private HuippuTulokset tulokset;

    @Before
    public void setUp() {
        tulokset = new HuippuTulokset();
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
    public void tarkastaMediumTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(1000, Vaikeustaso.KESKIVAIKEA);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void tarkastaVaikeaTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(1000, Vaikeustaso.VAIKEA);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void sijoitaHelppoTulosTest() {
        tulokset.sijoitaTulos(90, "Peksi", Vaikeustaso.HELPPO);
    }
    
    @Test
    public void getHelpotTest() {
        ArrayList<Tulos> helpot = tulokset.getHelpot();
        assertEquals(999, helpot.get(9).getAika());
    }
    
    @Test
    public void getMediumitTest() {
        ArrayList<Tulos> mediumit = tulokset.getMediumit();
        assertEquals(999, mediumit.get(9).getAika());
    }
    
    @Test
    public void getVaikeatTest() {
        ArrayList<Tulos> vaikeat = tulokset.getVaikeat();
        assertEquals(999, vaikeat.get(9).getAika());
    }
    
    @Test
    public void talletaTest() {
        tulokset.talleta();
    }

    @Test
    public void luoTiedostoTest() {
        tulokset.luoUusiTiedosto();
        ArrayList<Tulos> vaikeat = tulokset.getVaikeat();
        assertEquals("Anonymous", vaikeat.get(0).getNimi());
    }
    
}
