/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
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
}
