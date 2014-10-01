package miinaharava.sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * HuippuTulokset on luokka, joka pitää huolta pelin pistetilastosta.
 * Luokka lukee ja kirjoittaa tekstitiedostoa, joka sisältää pelin parhaat
 * ratkaisuajat.
 * 
 * @author Antti
 */
public class HuippuTulokset {
    
    private HashMap<Integer, String> helppoTilasto;
    private HashMap<Integer, String> mediumTilasto;
    private HashMap<Integer, String> vaikeaTilasto;
    private File tiedosto;
    private FileWriter kirjoittaja;
    private Scanner lukija;
    
    /**
     * Konstruktori, joka lukee huipputulosten tiedoston, ja jos sellaista ei
     * ole, käskee luoUusiTiedosto()-metodia luomaan sellaisen.
     * IO-poikkeukset ja -virheet käsitellään tässä luokassa myös.
     */
    /**
     * Konstruktori, joka 
     */
    public HuippuTulokset() {
        this.tiedosto = new File("miinaharavatulokset.txt");
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
//        tiedosto = new File("miinaharavatulokset.txt");
        
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
        /*
        for (int i = 0; i < helppoHuiput.length; i+=2) {
            helppoTilasto.put(Integer.parseInt(helppoHuiput[i]), helppoHuiput[i+1]);
            mediumTilasto.put(Integer.parseInt(mediumHuiput[i]), mediumHuiput[i+1]);
            vaikeaTilasto.put(Integer.parseInt(vaikeaHuiput[i]), vaikeaHuiput[i+1]);
        }
        /**/
        
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
    
    public void tulostaHelpot() {
        ArrayList<Integer> ajat = new ArrayList<>();
//        ajat = helppoTilasto.keySet();
    }
    /**/
    
}
