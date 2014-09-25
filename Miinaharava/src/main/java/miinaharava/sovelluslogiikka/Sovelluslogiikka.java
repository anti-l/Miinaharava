package miinaharava.sovelluslogiikka;

import java.awt.Color;
import javax.swing.ImageIcon;
import miinaharava.domain.*;
import miinaharava.gui.RuutuNappi;
import miinaharava.gui.PeliIkkuna;

/**
 * Sovelluslogiikka on koko miinaharava-projektin selkäranka ja aivot.
 * Koko pelin sisältö ja sen tapahtumat kulkevat tämän luokan kautta, se on
 * ensimmäinen asia mikä käynnistetään peliä avatessa.
 * @author Antti
 */
public class Sovelluslogiikka {

    private Ruudukko ruudukko;
    private PeliIkkuna peliIkkuna;
    private int miinoja;
    private int lippuja;
    private long alkuAika;
    private long loppuAika;

    /**
     * Konstruktori, muu toiminnallisuus viety muihin metodeihin.
     * Tätä tuskin enää edes tarvitaan.
     */
    public Sovelluslogiikka() {
    }

    /**
     * Metodi, jota kutsutaan Sovelluslogiikan käynnistyessä tai uutta peliä
     * aloittaessa. 
     * 
     * Metodi luo uuden ruudukon pelille standardimitoissa (LxK:10x10, 10 miinaa),
     * ja laittaa pelikellon käyntiin.
     */
    public void luoRuudukko() {
        this.ruudukko = new Ruudukko();
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
        this.alkuAika = System.currentTimeMillis();
    }
    
    /**
     * Metodi, jota kutsutaan Sovelluslogiikan käynnistyessä tai uutta peliä
     * aloittaessa. 
     * 
     * Metodi luo uuden ruudukon parametreinä annettujen arvojen mukaisesti,
     * ja laittaa pelikellon käyntiin.
     * 
     * @param leveys Uuden ruudukon leveys
     * @param korkeus Uuden ruudukon korkeus
     * @param miinoja Uuden ruudukon miinojen määrä
     */
    public void luoRuudukko(int leveys, int korkeus, int miinoja) {
        this.ruudukko = new Ruudukko(leveys, korkeus, miinoja);
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
        this.alkuAika = System.currentTimeMillis();
    }

    /** 
     * Palauttaa tällä hetkellä käytössä olevan Ruudukon.
     * @return Käytössä oleva Ruudukko.
     */
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    /**
     * Metodi kertoo sovelluslogiikalle käytössä olevan PeliIkkunan.
     * @param peliIkkuna käytössä oleva PeliIkkuna 
     */
    public void setPeliIkkuna(PeliIkkuna peliIkkuna) {
        this.peliIkkuna = peliIkkuna;
    }

    /**
     * Tätä metodia kutsutaan, kun pelaaja valitsee pelikentän ruudun.
     * Metodi tarkastaa ruudun sisällön, onko siinä miinaa, sekä montako miinaa 
     * löytyy viereisistä ruuduista.
     * 
     * Jos osutaan miinaan, peli loppuu. Jos osutaan tyhjään ruutuun, 
     * paljastetaan sen ympäriltä muutkin tyhjät ruudut paljastaTyhjat()-metodilla,
     * ja lopuksi otetaan nappi pois käytöstä.
     * 
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     * @param nappi PeliIkkunan nappi, jota painettiin
     */
    public void tarkistaRuutu(int x, int y, RuutuNappi nappi) {
        Ruutu ruutu = this.ruudukko.getRuutu(x, y);
        if (ruutu.onkoMiinaa()) {
            peliIkkuna.miinoita(x, y);
            peliIkkuna.gameOver();
        } else if (ruutu.onkoTyhja()) {
            paljastaTyhjat(x, y);
        } else {
            String napinTeksti = "";
            if (ruutu.getViereiset() > 0) {
                napinTeksti += ruutu.getViereiset();
            }
            nappi.setText(napinTeksti);
        }
        this.ruudukko.getRuutu(x, y).katsoRuutu();
        loppuukoPeli();
    }
    
    
    /**
     * Kun pelaaja on valinnut ruudun tarkastettavaksi ja siitä ei ole löytynyt
     * miinaa tai tietoa miinan paikasta viereisessä ruudussa, tuo ruutu on tyhjä.
     * Tyhjää ruutua klikatessa peli paljastaa muutkin tyhjät ruudut tuon
     * valitun ruudun läheisyydessä.
     * 
     * Metodi tarkastaa annetut koordinaatit ja käy läpi viereiset ruudut, jonka
     * jälkeen se kutsuu itseään uudelleen viereisille ruuduille. Vastaan 
     * tulleet tyhjät ruudut paljastetaan ja otetaan pois käytöstä.
     * 
     * @param x tarkastettavan ruudun x-koordinaatti
     * @param y tarkastettavan ruudun y-koordinaatti
     */
    public void paljastaTyhjat(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == x && j == y) {                                             // Ruutu, mitä pelaaja itse painoi -> jatketaan
                    continue;
                } else if (x + i < 0 || x + i >= ruudukko.getLeveys()) {            // Ruutu, joka menee vaakasuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                } else if (y + j < 0 || y + j >= ruudukko.getKorkeus()) {           // Ruutu, joka menee pystysuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                    // Katsotun ruudun kaikki 8 naapuria +/- 1 pysty- ja vaakasuuntaan:
                    // Jos ruutu on tyhjä eikä sitä ole tarkistettu vielä
                } else if (ruudukko.getRuutu((x + i), (y + j)).onkoTyhja() && ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();               // Määritetään Ruutu katsotuksi
                    peliIkkuna.painaNappiAlas(x + i, y + j);                            // Painetaan käyttöliittymän nappi alas
                    paljastaTyhjat(x + i, y + j);                                   // Kutsutaan rekursiivisesti viereisiä ruutuja
//                } else if (ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
//                    peliIkkuna.painaNappiAlas(x + i, y + j);
//                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();
                }
            }
        }
    }
    
    /**
     * Metodi kertoo Ruudukon yksittäiselle ruudulle, että se on liputettu
     * pelaajan toimesta. Jos ruudussa on jo lippu, se poistetaan, jos sitä ei 
     * ole, ruutuun asetetaan lippu.
     * 
     * Samalla pidetään kirjaa siitä, montako lippua pelaaja on asettanut. 
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     */
    public void liputaRuutu(int x, int y) {
        Ruutu tamaRuutu = ruudukko.getRuutu(x, y);
        
        peliIkkuna.liputa(x, y);
        if (tamaRuutu.getLiputettu() == false) {
            tamaRuutu.setLiputettu();
            lippuja++;
        } else {
            tamaRuutu.removeLiputettu();
            lippuja--;
        }
        loppuukoPeli();
    }
    
    /**
     * Tämä metodi tarkastaa, onko pelin loppumiseen tarvittavat kriteerit
     * täyttyneet. Jos pelaaja on liputtanut ruutuja yhtä monta kun pelissä on
     * miinoja, tarkistetaan, ovatko ne oikeissa paikoissa.
     * 
     * Jos näin käy, pysäytetään pelikello ja ilmoitetaan pelaajalle pelin
     * voittamisesta.
     */
    public void loppuukoPeli() {
        if (miinoja == lippuja) {
            boolean onkoMiinatLiputettu = ruudukko.onkoMiinatLiputettu();
            if (ruudukko.onkoMiinatLiputettu()) {
                loppuAika = System.currentTimeMillis();
                long kulunutAikaSekunneissa = (loppuAika - alkuAika) / 1000;
                peliIkkuna.peliVoitettu(kulunutAikaSekunneissa);
            }
        }
    }

}
