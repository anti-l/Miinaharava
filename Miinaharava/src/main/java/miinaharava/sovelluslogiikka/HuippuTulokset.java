package miinaharava.sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * HuippuTulokset on luokka, joka pitää huolta pelin pistetilastosta.
 * Luokka lukee ja kirjoittaa tekstitiedostoa, joka sisältää pelin parhaat
 * ratkaisuajat.
 * 
 * @author Antti
 */
public class HuippuTulokset {
    
    private String[] helppoHuiput;
    private String[] mediumHuiput;
    private String[] vaikeaHuiput;
    private File tiedosto;
    private FileWriter kirjoittaja;
    private Scanner lukija;
    
    /**
     * Konstruktori, joka lukee huipputulosten tiedoston, ja jos sellaista ei
     * ole, käskee luoUusiTiedosto()-metodia luomaan sellaisen.
     * IO-poikkeukset ja -virheet käsitellään tässä luokassa myös.
     */
    
    public HuippuTulokset() {
        tiedosto = new File("miinaharavatulokset.txt");
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
            //
        }
    }
    /**/
    
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
            helppo += "Anonymous:999:";
        }
        helppo = helppo.substring(0, helppo.length()-1);
        for (int i = 0; i < 10; i++) {
            medium += "Anonymous:999:";
        }
        medium = medium.substring(0, medium.length()-1);
        for (int i = 0; i < 10; i++) {
            vaikea += "Anonymous:999:";
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
    /**/
    
}
