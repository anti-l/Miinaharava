/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antti
 */
public class RuudukkoTest {

    private Ruudukko ruudukko;

    public RuudukkoTest() {

    }

    @Before
    public void setUp() {
        ruudukko = new Ruudukko();
    }
    
    @Test
    public void luoVakioRuudukko() {
        Ruudukko vakioRuudukko = new Ruudukko();
        assertEquals(10, vakioRuudukko.getLeveys());
        assertEquals(10, vakioRuudukko.getKorkeus());
        assertEquals(10, vakioRuudukko.getMiinoja());
    }
    
    @Test
    public void luoPieniRuudukko() {
        Ruudukko pikkuRuudukko = new Ruudukko(2, 2, 2);
        assertEquals(2, pikkuRuudukko.getLeveys());
        assertEquals(2, pikkuRuudukko.getKorkeus());
        assertEquals(2, pikkuRuudukko.getMiinoja());
    }
    
    @Test
    public void luoIsoRuudukko() {
        Ruudukko isoRuudukko = new Ruudukko(20, 20, 50);
        assertEquals(20, isoRuudukko.getLeveys());
        assertEquals(20, isoRuudukko.getKorkeus());
        assertEquals(50, isoRuudukko.getMiinoja());
    }
    
    @Test
    public void luoLiianKapeaRuudukko() {
        Ruudukko virheRuudukko = new Ruudukko(0, 10, 10);
        assertEquals(10, virheRuudukko.getLeveys());
        assertEquals(10, virheRuudukko.getKorkeus());
        assertEquals(10, virheRuudukko.getMiinoja());
    }
    
    @Test
    public void luoLiianMatalaRuudukko() {
        Ruudukko virheRuudukko = new Ruudukko(10, 0, 10);
        assertEquals(10, virheRuudukko.getLeveys());
        assertEquals(10, virheRuudukko.getKorkeus());
        assertEquals(10, virheRuudukko.getMiinoja());
    }
    
    @Test
    public void luoRuudukkoLiianVahaisillaMiinoilla() {
        Ruudukko virheRuudukko = new Ruudukko(10, 10, 0);
        assertEquals(10, virheRuudukko.getLeveys());
        assertEquals(10, virheRuudukko.getKorkeus());
        assertEquals(10, virheRuudukko.getMiinoja());
    }
    
    
    
}