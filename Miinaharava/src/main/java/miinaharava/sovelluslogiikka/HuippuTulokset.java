package miinaharava.sovelluslogiikka;

import miinaharava.domain.Tulos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HuippuTulokset on luokka, joka pitää huolta pelin pistetilastosta.
 * Luokka lukee ja kirjoittaa tekstitiedostoa, joka sisältää pelin parhaat
 * ratkaisuajat.
 * 
 * @author Antti
 */
public class HuippuTulokset {
    
    private ArrayList<Tulos> helppoTilasto, mediumTilasto, vaikeaTilasto;
    private File tiedosto;
    private FileWriter kirjoittaja;
    private Scanner lukija;
    
    /**
     * Konstruktori, joka luo tietorakenteet tuloksille, lukee tiedostosta
     * aiemmat huipputulokset, ja jos ei löydä niitä, luo uuden tiedoston.
     */
    public HuippuTulokset() {
        this.tiedosto = new File("miinaharavatulokset.txt");
        helppoTilasto = new ArrayList<Tulos>();
        mediumTilasto = new ArrayList<Tulos>();
        vaikeaTilasto = new ArrayList<Tulos>();
        lueTiedosto();
    }
    
    /**
     * Metodi, joka lukee tiedostosta pelin eri vaikeusasteiden huipputulokset
     * ja tallettaa ne muuttujiin. Jos tiedostoa ei ole olemassa tai sen rakenne
     * on virheellinen, luodaan uusi tiedosto huipputuloksille.
     */
    public void lueTiedosto() {
        String[] helppoHuiput = new String[20];
        String[] mediumHuiput = new String[20];
        String[] vaikeaHuiput = new String[20];
        
        try {
            lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löytynyt. Luodaan uusi.");
            luoUusiTiedosto();
        }
        
        try {
            helppoHuiput = lukija.nextLine().split(":");
            mediumHuiput = lukija.nextLine().split(":");
            vaikeaHuiput = lukija.nextLine().split(":");
        } catch (Exception e) {
            System.out.println("Tiedoston rakenne on virheellinen.");
            luoUusiTiedosto();
        }
        
        // Siirretään tilasto parempaan tietorakenteeseen.
        /**/
        for (int i = 0; i < helppoHuiput.length; i += 2) {
            Tulos tulos = new Tulos(Integer.parseInt(helppoHuiput[i]), helppoHuiput[i+1]);
            helppoTilasto.add(tulos);
            mediumTilasto.add(new Tulos(Integer.parseInt(mediumHuiput[i]), mediumHuiput[i+1]));
            vaikeaTilasto.add(new Tulos(Integer.parseInt(vaikeaHuiput[i]), vaikeaHuiput[i+1]));
        }
        /**/
//        tulostaHelpot();
        
    }
    
    /**
     * Jos peli käynnistetään ensimmäistä kertaa, tämä metodi luo uuden tiedoston
     * parhaiden pisteiden säilyttämistä ja lukemista varten. Uudessa tiedostossa
     * parhaat pelaajat ovat nimettömiä ja ratkaisuun on kulunut 999 sekuntia.
     */
    public void luoUusiTiedosto() {
        String helppo = "";
        String medium = "";
        String vaikea = "";
        for (int i = 0; i < 10; i++) {
            helppo += "999:Anonymous:";
        }
        helppo = helppo.substring(0, helppo.length()-1);
        for (int i = 0; i < 10; i++) {
            medium += "999:Anonymous:";
        }
        medium = medium.substring(0, medium.length()-1);
        for (int i = 0; i < 10; i++) {
            vaikea += "999:Anonymous:";
        }
        vaikea = vaikea.substring(0, vaikea.length()-1);
        String kirjoitettava = helppo + "\n" + medium + "\n" + vaikea + "\n";

        try {
            kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write(kirjoitettava);
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }
    }
    
    /**
     * Testimetodi.
     * Tulostaa helpon vaikeustason tulokset.
     */
    public void tulostaHelpot() {
        for (int i = 0; i < 10; i++) {
            Tulos t = helppoTilasto.get(i);
            System.out.println((i+1) + ". " + t.getNimi() + ": " + t.getAika());
        }
    }
    
    /**
     * Metodi tarkastaa, onko pelin suorittamiseen kulunut aika niin hyvä, että
     * se mahtuu parhaiden tulosten listalle.
     * 
     * @param aika Pelin voittamiseen kulunut aika
     * @return true, jos aika ansaitsee paikan parhaiden tulosten listalla
     */
    public boolean tarkastaHelppoTulos(int aika) {
        // Jos tilastossa on jo 10 nopeampaa aikaa, palautetaan false
        if (helppoTilasto.get(9).getAika() < aika) {
            return false;
        } 
        return true;
    }
    
    /**
     * Metodi sijoittaa ajan listalle ja siirtää huonompia aikoja listalla
     * alaspäin.
     * 
     * @param aika Pelin voittamiseen kulunut aika
     * @param nimi Pelaajan nimi
     */
    public void sijoitaHelppoTulos(int aika, String nimi) {
        Tulos uusiTulos = new Tulos(aika, nimi);
        int sija = 11;
        for (int i = 0; i < 10; i++) {
            if (aika < helppoTilasto.get(i).getAika()) {
                sija = i;
                break;
            }
        }
    }
}
