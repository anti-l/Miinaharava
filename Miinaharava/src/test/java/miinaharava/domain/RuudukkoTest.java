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
    
    @Test
    public void luoVirheellinenRuudukko() {
        Ruudukko virheRuudukko = new Ruudukko(0,0,0);
        assertEquals(10, virheRuudukko.getLeveys());
        assertEquals(10, virheRuudukko.getKorkeus());
        assertEquals(10, virheRuudukko.getMiinoja());
    }
    
    @Test
    public void testaaAsetaMiinatUudelleen() {
        Ruudukko ruudukko = new Ruudukko();
        ruudukko.asetaMiinat(10);
        int miinojenMaara = 0;
        String ruudukonKuvaus = ruudukko.toString();
        for (int i = 0; i < ruudukonKuvaus.length()-1; i++) {
            if (ruudukonKuvaus.charAt(i) == 'X') {
                miinojenMaara++;
            }
        }
        assertEquals(20, miinojenMaara);
    }
    
    @Test
    public void testaaAsetaMiinatLiianVahanMiinoja() {
        Ruudukko ruudukko = new Ruudukko();
        ruudukko.asetaMiinat(0);
        assertEquals(true, ruudukko.toString().contains("X"));
    }
    
    @Test
    public void testaaYmparoivienMiinojenLaskua() {
        Ruudukko ruudukko = new Ruudukko();
        ruudukko.getRuutu(0, 1).setMiina();
        ruudukko.getRuutu(1, 0).setMiina();
        ruudukko.getRuutu(1, 1).setMiina();
        int miinojenMaara = ruudukko.laskeRuutuaYmparoivatMiinat(0, 0);
        assertEquals(3, miinojenMaara);
    }
    
    @Test
    public void testaaMiinojenLiputus() {
        Ruudukko ruudukko = new Ruudukko();
        boolean onkoLiputettu = ruudukko.onkoMiinatLiputettu();
        assertEquals(false, onkoLiputettu);
    }
    
    @Test
    public void testaaMiinojenLiputusLisatyillaLipuilla() {
        Ruudukko ruudukko = new Ruudukko();
        for (int i = 0; i < ruudukko.getLeveys(); i++) {
            for (int j = 0; j < ruudukko.getKorkeus(); j++) {
                if (ruudukko.getRuutu(i, j).onkoMiinaa()) {
                    ruudukko.getRuutu(i, j).setLiputettu();
                }
            }
        }
        boolean onkoLiputettu = ruudukko.onkoMiinatLiputettu();
        assertEquals(true, onkoLiputettu);
    }
    
    @Test
    public void onkoRuudukossaTestKunEiPitaisiOlla() {
        Ruudukko ruudukko = new Ruudukko();
        assertEquals(false, ruudukko.onRuudukossa(-1, -1));
    }
    
    @Test
    public void onkoRuudukossaTestKunPitaisiOlla() {
        Ruudukko ruudukko = new Ruudukko();
        assertEquals(true, ruudukko.onRuudukossa(1, 1));
    }
    
    @Test
    public void merkkijononTestaus() {
        Ruudukko ruudukko = new Ruudukko();
        String ruudukonMerkkijono = ruudukko.toString();
        assertEquals(110, ruudukonMerkkijono.length());
    }
    
}
