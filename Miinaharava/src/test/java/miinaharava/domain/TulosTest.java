package miinaharava.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class TulosTest {
    
    private Tulos tulos;
    private Tulos parempi, huonompi, sama;
    
    @Before
    public void setUp() {
        tulos = new Tulos(35, "Antti");
        sama = new Tulos(35, "Sami");
        parempi = new Tulos(30, "Pekka");
        huonompi = new Tulos(36, "Harri");
    }
    
    @Test
    public void luoTulosTest() {
        Tulos uusiTulos = new Tulos(20, "Player One");
        assertEquals(20, uusiTulos.getAika());
    }
    
    @Test
    public void kysyNimiTest() {
        assertEquals("Antti", tulos.getNimi());
    }
    
    @Test
    public void vertaaHuonompaanTest() {
        assertEquals(1, tulos.compareTo(huonompi));
    }
    
    @Test
    public void vertaaParempaanTest() {
        assertEquals(-1, tulos.compareTo(parempi));
    }
    
    @Test
    public void vertaaSamaanTest() {
        assertEquals(0, tulos.compareTo(sama));
    }
}
