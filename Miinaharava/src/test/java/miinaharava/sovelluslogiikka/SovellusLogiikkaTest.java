/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.sovelluslogiikka;

import miinaharava.domain.*;
import miinaharava.gui.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti
 */
public class SovellusLogiikkaTest {

    Sovelluslogiikka sovlog;
    PeliIkkuna kayttis;
    
    @Before
    public void setUp() {
        Sovelluslogiikka sovlog = new Sovelluslogiikka();
        PeliIkkuna kayttis = new PeliIkkuna(sovlog);
    }
    
    @Test
    public void tarkastaRuutuTest() {
        
    }
    
    @Test
    public void testKayttoliittymanAsetus() {
        
    }
    
    @Test
    public void onkoRuudukkoa() {
        assertEquals(true, true);
    }
}
