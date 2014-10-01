package miinaharava.sovelluslogiikka;

import java.awt.Color;
import javax.swing.ImageIcon;
import miinaharava.domain.*;
import miinaharava.gui.RuutuNappi;
import miinaharava.gui.PeliIkkuna;

/**
 * Sovelluslogiikka on koko miinaharava-projektin selkÃ¤ranka ja aivot. Koko
 * pelin sisÃ¤ltÃ¶ ja sen tapahtumat kulkevat tÃ¤mÃ¤n luokan kautta, se on
 * ensimmÃ¤inen asia mikÃ¤ kÃ¤ynnistetÃ¤Ã¤n peliÃ¤ avatessa.
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

    /**
     * Konstruktori, muu toiminnallisuus viety muihin metodeihin. TÃ¤tÃ¤ tuskin
     * enÃ¤Ã¤ edes tarvitaan.
     */
    public Sovelluslogiikka() {
    }

    /**
     * Metodi, jota kutsutaan Sovelluslogiikan kÃ¤ynnistyessÃ¤ tai uutta peliÃ¤
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
    }

    /**
     * Metodi, jota kutsutaan Sovelluslogiikan kÃ¤ynnistyessÃ¤ tai uutta peliÃ¤
     * aloittaessa.
     *
     * Metodi luo uuden ruudukon parametreinÃ¤ annettujen arvojen mukaisesti.
     *
     * @param leveys Uuden ruudukon leveys
     * @param korkeus Uuden ruudukon korkeus
     * @param miinoja Uuden ruudukon miinojen mÃ¤Ã¤rÃ¤
     */
    public void luoRuudukko(int leveys, int korkeus, int miinoja) {
        this.ruudukko = new Ruudukko(leveys, korkeus, miinoja);
        this.lippuja = 0;
        this.miinoja = ruudukko.getMiinoja();
        this.alkuAika = System.currentTimeMillis();
        this.peliLoppui = false;
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
     * TÃ¤tÃ¤ metodia kutsutaan, kun pelaaja valitsee pelikentÃ¤n ruudun. Metodi
     * tarkastaa ruudun sisÃ¤llÃ¶n, onko siinÃ¤ miinaa, sekÃ¤ montako miinaa
     * lÃ¶ytyy viereisistÃ¤ ruuduista. Pelin ensimmÃ¤isellÃ¤ ruudun
     * tarkistuskerralla laitetaan pelikello kÃ¤yntiin.
     *
     * Jos osutaan miinaan, peli loppuu. Jos osutaan tyhjÃ¤Ã¤n ruutuun,
     * paljastetaan sen ympÃ¤riltÃ¤ muutkin tyhjÃ¤t ruudut
     * paljastaTyhjat()-metodilla.
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     */
    public void tarkistaRuutu(int x, int y) {
        if (alkuAika == 0) {
            this.alkuAika = System.currentTimeMillis();
        }
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
     * TÃ¤tÃ¤ metodia kutsutaan, kun UI:ssa painetaan nappia ja katsotaan, mitÃ¤
     * napin alta lÃ¶ytyy. Jos kyseessÃ¤ ei ole miina, tÃ¤mÃ¤ metodi palauttaa
     * joko tyhjÃ¤n merkkijonon tyhjille ruuduille tai viereisten miinojen
     * mÃ¤Ã¤rÃ¤n miinojen vieressÃ¤ oleville ruuduille.
     *
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     * @return Ruutuun asetettava teksti
     */
    public String ruudunTeksti(int x, int y) {
        String ruudunTeksti = "";
        Ruutu tamaRuutu = ruudukko.getRuutu(x, y);
        if (tamaRuutu.onkoTyhja()) {
            return ruudunTeksti;
        } else if (tamaRuutu.getViereiset() > 0) {
            ruudunTeksti = "" + tamaRuutu.getViereiset();
        }
        return ruudunTeksti;
    }

    /**
     * Kun pelaaja on valinnut ruudun tarkastettavaksi ja siitÃ¤ ei ole
     * lÃ¶ytynyt miinaa tai tietoa miinan paikasta viereisessÃ¤ ruudussa, tuo
     * ruutu on tyhjÃ¤. TyhjÃ¤Ã¤ ruutua klikatessa peli paljastaa muutkin
     * tyhjÃ¤t ruudut tuon valitun ruudun lÃ¤heisyydessÃ¤.
     *
     * Metodi tarkastaa annetut koordinaatit ja kÃ¤y lÃ¤pi viereiset ruudut,
     * jonka jÃ¤lkeen se kutsuu itseÃ¤Ã¤n uudelleen viereisille ruuduille.
     * Vastaan tulleet tyhjÃ¤t ruudut paljastetaan ja otetaan pois kÃ¤ytÃ¶stÃ¤.
     *
     * @param x tarkastettavan ruudun x-koordinaatti
     * @param y tarkastettavan ruudun y-koordinaatti
     */
    public void paljastaTyhjat(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == x && j == y) {                                             // Ruutu, mitÃ¤ pelaaja itse painoi -> jatketaan
                    continue;
                } else if (x + i < 0 || x + i >= ruudukko.getLeveys()) {            // Ruutu, joka menee vaakasuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                } else if (y + j < 0 || y + j >= ruudukko.getKorkeus()) {           // Ruutu, joka menee pystysuunnassa ruudukon ulkopuolelle -> jatketaan
                    continue;
                    // Katsotun ruudun kaikki 8 naapuria +/- 1 pysty- ja vaakasuuntaan:
                    // Jos ruutu on tyhjÃ¤ eikÃ¤ sitÃ¤ ole tarkistettu vielÃ¤
                } else if (ruudukko.getRuutu((x + i), (y + j)).onkoTyhja() && ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();               // MÃ¤Ã¤ritetÃ¤Ã¤n Ruutu katsotuksi
                    paljastaTyhjat(x + i, y + j);                                   // Kutsutaan rekursiivisesti viereisiÃ¤ ruutuja
                } else if (ruudukko.getRuutu((x + i), (y + j)).getKatsottu() == false) {
                    ruudukko.getRuutu((x + i), (y + j)).katsoRuutu();
                }
            }
        }
    }

    /**
     * Metodi kertoo Ruudukon yksittÃ¤iselle ruudulle, ettÃ¤ se on liputettu
     * pelaajan toimesta. Jos ruudussa on jo lippu, se poistetaan, jos sitÃ¤ ei
     * ole, ruutuun asetetaan lippu.
     *
     * Samalla pidetÃ¤Ã¤n kirjaa siitÃ¤, montako lippua pelaaja on asettanut.
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
     * TÃ¤mÃ¤ metodi tarkastaa, onko pelin loppumiseen tarvittavat kriteerit
     * tÃ¤yttyneet. Jos pelaaja on liputtanut ruutuja yhtÃ¤ monta kun pelissÃ¤
     * on miinoja, tarkistetaan, ovatko ne oikeissa paikoissa.
     *
     * Jos nÃ¤in kÃ¤y, pysÃ¤ytetÃ¤Ã¤n pelikello ja ilmoitetaan pelaajalle pelin
     * voittamisesta.
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
     * @return true, jos peli on ohi, false, jos se on vielä käynnissä.
     */
    public boolean onkoPeliOhi() {
        return peliLoppui;
    }
    
    public void setPeliOhi() {
        this.peliLoppui = true;
    }

    public long getPelinKesto() {
        long kulunutAikaSekunneissa = (loppuAika - alkuAika) / 1000;
        return kulunutAikaSekunneissa;
    }

}
