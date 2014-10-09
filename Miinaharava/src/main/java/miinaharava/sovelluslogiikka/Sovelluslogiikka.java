package miinaharava.sovelluslogiikka;

import java.awt.Color;
import javax.swing.ImageIcon;
import miinaharava.domain.*;
import miinaharava.gui.RuutuNappi;
import miinaharava.gui.PeliIkkuna;

/**
 * Sovelluslogiikka on koko miinaharava-projektin selkäranka ja aivot. Koko
 * pelin sisältö ja sen tapahtumat kulkevat tämän luokan kautta, se on
 * ensimmäinen asia mikä käynnistetään peliä avatessa.
 *
 * @author Antti
 */
public class Sovelluslogiikka {

    private Ruudukko ruudukko;
    private int miinoja;
    private int lippuja;
    private long alkuAika = 0;
    private long loppuAika = 0;
    private boolean peliLoppui;
    private HuippuTulokset huippuTulokset;
    private Vaikeustaso vaikeustaso;

    /**
     * Konstruktori, muu toiminnallisuus viety muihin metodeihin. Tätä tuskin
     * enää edes tarvitaan.
     */
    public Sovelluslogiikka() {
        huippuTulokset = new HuippuTulokset();
    }

    /**
     * Metodi, jota kutsutaan Sovelluslogiikan käynnistyessä tai uutta peliä
     * aloittaessa.
     *
     * Metodi luo uuden ruudukon pelille standardimitoissa (LxK:10x10, 10
     * miinaa).
     */
    public void luoRuudukko() {
        this.ruudukko = new Ruudukko();
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
        this.alkuAika = System.currentTimeMillis();
        this.peliLoppui = false;
        this.vaikeustaso = Vaikeustaso.HELPPO;
    }

    /**
     * Metodi, jota kutsutaan Sovelluslogiikan käynnistyessä tai uutta peliä
     * aloittaessa.
     *
     * Metodi luo uuden ruudukon parametreinä annettujen arvojen mukaisesti.
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
        this.peliLoppui = false;
    }
    
    /**
     * Metodilla voidaan asettaa käynnissä olevan pelin vaikeustaso.
     * @param vaikeustaso Enum Vaikeustaso - HELPPO, MEDIUM, VAIKEA, CUSTOM
     */
    public void setVaikeustaso(Vaikeustaso vaikeustaso) {
        this.vaikeustaso = vaikeustaso;
    }
    
    /**
     * Metodi kertoo, mikä on pelin nykyinen vaikeustaso.
     * @return Enum Vaikeustaso - HELPPO, MEDIUM, VAIKEA, CUSTOM
     */
    public Vaikeustaso getVaikeustaso() {
        return this.vaikeustaso;
    }

    /**
     * Palauttaa tällä hetkellä käytössä olevan Ruudukon.
     *
     * @return Käytössä oleva Ruudukko.
     */
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    /**
     * Tätä metodia kutsutaan, kun pelaaja valitsee pelikentän ruudun. Metodi
     * tarkastaa ruudun sisällön, onko siinä miinaa, sekä montako miinaa
     * löytyy viereisistä ruuduista. Pelin ensimmäisellä ruudun
     * tarkistuskerralla laitetaan pelikello käyntiin.
     *
     * Jos osutaan miinaan, peli loppuu. Jos osutaan tyhjään ruutuun,
     * paljastetaan sen ympäriltä muutkin tyhjät ruudut
     * paljastaTyhjat()-metodilla.
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     */
    public void tarkistaRuutu(int x, int y) {
        Ruutu ruutu = this.ruudukko.getRuutu(x, y);
        if (ruutu.onkoMiinaa()) {
            System.out.println("Osuit miinaan!");
        } else if (ruutu.onkoTyhja()) {
            paljastaTyhjat(x, y);
        }
        this.ruudukko.getRuutu(x, y).katsoRuutu();
        loppuukoPeli();
    }

    /**
     * Tätä metodia kutsutaan, kun UI:ssa painetaan nappia ja katsotaan, mitä
     * napin alta löytyy. Jos kyseessä ei ole miina, tämä metodi palauttaa
     * joko tyhjän merkkijonon tyhjille ruuduille tai viereisten miinojen
     * määrän miinojen vieressä oleville ruuduille.
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     * @return Ruutuun asetettava teksti
     */
    public String ruudunTeksti(int x, int y) {
        String ruudunTeksti = "";
        Ruutu tamaRuutu = ruudukko.getRuutu(x, y);
        if (tamaRuutu.onkoTyhja()) {
            return "";
        } else if (tamaRuutu.getViereiset() > 0) {
            ruudunTeksti = "" + tamaRuutu.getViereiset();
        }
        return ruudunTeksti;
    }

    /**
     * Kun pelaaja on valinnut ruudun tarkastettavaksi ja siitä ei ole
     * lÃƒÂ¶ytynyt miinaa tai tietoa miinan paikasta viereisessä ruudussa, tuo
     * ruutu on tyhjä. Tyhjää ruutua klikatessa peli paljastaa muutkin
     * tyhjät ruudut tuon valitun ruudun läheisyydessä.
     *
     * Metodi tarkastaa annetut koordinaatit ja käy läpi viereiset ruudut,
     * jonka jälkeen se kutsuu itseään uudelleen viereisille ruuduille.
     * Vastaan tulleet tyhjät ruudut paljastetaan ja otetaan pois käytöstä.
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
                    paljastaTyhjat(x + i, y + j);                                   // Kutsutaan rekursiivisesti viereisiä ruutuja
                } else if (ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();
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
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     */
    public void liputaRuutu(int x, int y) {
        Ruutu tamaRuutu = this.ruudukko.getRuutu(x, y);
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
     * täyttyneet. Jos pelaaja on liputtanut ruutuja yhtä monta kun pelissä
     * on miinoja, tarkistetaan, ovatko ne oikeissa paikoissa.
     *
     * Jos näin käy, pysäytetään pelikello ja ilmoitetaan pelaajalle pelin
     * voittamisesta.
     * @return true, jos pelin loppumiseen edellytettävät kriteerit täyttyvät.
     */
    public boolean loppuukoPeli() {
            if (miinoja == lippuja) {
                if (ruudukko.onkoMiinatLiputettu()) {
                    loppuAika = System.currentTimeMillis();
                    peliLoppui = true;
                    return true;
                }
            }
        return false;
    }
    
    /**
     * Metodi, jolla tarkistetaan, onko peli jo ohi.
     * @return true, jos peli on ohi, false, jos se on vielÃ¤ kÃ¤ynnissÃ¤.
     */
    public boolean onkoPeliOhi() {
        return peliLoppui;
    }
    
    /**
     * Metodi, jolta voidaan kysyä, onko tämä peli jo asetettu loppunneeksi.
     */
    public void setPeliOhi() {
        this.peliLoppui = true;
    }

    /**
     * Metodi, joka kertoo pelin pelaamiseen kuluneen ajan. Aika lasketaan
     * peliruudukon luomisesta viimeisen oikean lipun asettamiseen.
     * @return Pelin voittamiseen kulunut aika sekunneissa.
     */
    public long getPelinKesto() {
        long kulunutAikaSekunneissa = (loppuAika - alkuAika) / 1000;
        return kulunutAikaSekunneissa;
    }

    /**
     * Palauttaa tämän peli-instanssin huipputulokset-ilmentymän.
     * @return käytössä oleva huipputulokset-olio.
     */
    public HuippuTulokset getHuippuTulokset() {
        return this.huippuTulokset;
    }
    
    /**
     * Testataan, pääseekö tällä tuloksella huipputulosten listalle.
     * @return true, jos pääsee, false, jos ei.
     */
    public boolean paaseekoListalle() {
        return huippuTulokset.tarkastaTulos((int) getPelinKesto(), vaikeustaso);
    }
    
    /**
     * Kun paaseekoListalle() on ilmoittanut, että tulos on tarpeeksi hyvä
     * huipputulosten listalle, tämä metodi käskee HuippuTuloksia kirjoittamaan
     * nimen ja ajan oikealle listalle oikeaan paikkaan.
     * @param aika Pelin voittamiseen kulunut aika.
     * @param nimi Pelaajan nimi.
     */
    public void sijoitaTulos(int aika, String nimi) {
        huippuTulokset.sijoitaTulos(aika, nimi, this.vaikeustaso);
    }
    
    
}
