package miinaharava.sovelluslogiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import miinaharava.domain.*;
import org.junit.After;

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
    public void tarkastaKeskiVaikeaTulosPaaseeListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(100, Vaikeustaso.KESKIVAIKEA);
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastaMediumTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(1000, Vaikeustaso.KESKIVAIKEA);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void tarkastaVaikeaTulosPaaseeListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(101, Vaikeustaso.VAIKEA);
        assertEquals(true, tarkastus);
    }
    
    @Test
    public void tarkastaVaikeaTulosEiPaaseListalle() {
        boolean tarkastus = tulokset.tarkastaTulos(1000, Vaikeustaso.VAIKEA);
        assertEquals(false, tarkastus);
    }
    
    @Test
    public void sijoitaHelppoTulosTest() {
        tulokset.sijoitaTulos(90, "Anonymous", Vaikeustaso.HELPPO);
    }
    
    @Test
    public void sijoitaMediumTulosTest() {
        tulokset.sijoitaTulos(90, "Anonymous", Vaikeustaso.KESKIVAIKEA);
    }
    
    @Test
    public void sijoitaVaikeaTulosTest() {
        tulokset.sijoitaTulos(90, "Anonymous", Vaikeustaso.VAIKEA);
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
    
    
    @Test
    public void lueVirheellinenTiedostoTest() {
        File tiedosto = new File("miinaharavatulokset.txt");
        FileWriter kirjoittaja;
        try {
            kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("");
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedoston avaminen ja sen kirjoittaminen epäonnistui");;
        }
        tulokset.lueTiedosto();
        assertEquals("Anonymous", tulokset.getHelpot().get(0).getNimi());
    }
    
    @Test
    public void luoUusiTiedostoTest() {
        File tiedosto = new File("miinaharavatulokset.txt");
        FileWriter kirjoittaja;
        try {
            kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("");
        } catch (IOException ex) {
            System.out.println("Tiedoston avaminen ja sen kirjoittaminen epäonnistui");;
        }
        tulokset.luoUusiTiedosto();
    }
    
    @Test
    public void sijoitaTulosTest() {
        tulokset.sijoitaTulos(10, "Helppo", Vaikeustaso.HELPPO);
        tulokset.sijoitaTulos(10, "Medium", Vaikeustaso.KESKIVAIKEA);
        tulokset.sijoitaTulos(10, "Vaikea", Vaikeustaso.VAIKEA);
        tulokset.sijoitaTulos(10, "Custom", Vaikeustaso.CUSTOM);
    }
    
    @After
    public void tearDown() {
        File tiedosto = new File("miinaharavatulokset.txt");
        tiedosto.delete();
        FileWriter kirjoittaja;
        try {
            kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("");
        } catch (IOException ex) {
            System.out.println("Tiedoston avaminen ja sen kirjoittaminen epäonnistui");;
        }
        tulokset.luoUusiTiedosto();
        
    }
    
}
