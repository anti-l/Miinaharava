package miinaharava.sovelluslogiikka;

import miinaharava.domain.Tulos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * HuippuTulokset on luokka, joka pitää huolta pelin pistetilastosta. Luokka
 * lukee ja kirjoittaa tekstitiedostoa, joka sisältää pelin parhaat
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
     * Metodi, joka palauttaa helpon vaikeustason pelitulokset.
     *
     * @return ArrayList<Tulos> sisältäen helpon vaikeustason tulokset.
     */
    public ArrayList<Tulos> getHelpot() {
        return helppoTilasto;
    }

    /**
     * Metodi, joka palauttaa keskivaikean vaikeustason pelitulokset.
     *
     * @return ArrayList<Tulos> sisältäen keskivaikean vaikeustason tulokset.
     */
    public ArrayList<Tulos> getMediumit() {
        return mediumTilasto;
    }

    /**
     * Metodi, joka palauttaa vaikean vaikeustason pelitulokset.
     *
     * @return ArrayList<Tulos> sisältäen vaikean vaikeustason tulokset.
     */
    public ArrayList<Tulos> getVaikeat() {
        return vaikeaTilasto;
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

        boolean lukeminenOnnistuu = false;
        while (!lukeminenOnnistuu) {
            try {
                lukija = new Scanner(tiedosto);
                lukeminenOnnistuu = true;
            } catch (FileNotFoundException e) {
                System.out.println("Tiedostoa ei löytynyt. Luodaan uusi.");
                luoUusiTiedosto();
            }
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
        for (int i = 0; i < 20; i += 2) {
            Tulos tulos = new Tulos(Integer.parseInt(helppoHuiput[i]), helppoHuiput[i + 1]);
            helppoTilasto.add(tulos);
            mediumTilasto.add(new Tulos(Integer.parseInt(mediumHuiput[i]), mediumHuiput[i + 1]));
            vaikeaTilasto.add(new Tulos(Integer.parseInt(vaikeaHuiput[i]), vaikeaHuiput[i + 1]));
        }
        /**/
//        tulostaHelpot();

    }

    /**
     * Jos peli käynnistetään ensimmäistä kertaa, tämä metodi luo uuden
     * tiedoston parhaiden pisteiden säilyttämistä ja lukemista varten. Uudessa
     * tiedostossa parhaat pelaajat ovat nimettömiä ja ratkaisuun on kulunut 999
     * sekuntia.
     */
    public void luoUusiTiedosto() {
        String helppo = "";
        String medium = "";
        String vaikea = "";
        for (int i = 0; i < 10; i++) {
            helppo += "999:Anonymous:";
        }
        helppo = helppo.substring(0, helppo.length() - 1);
        for (int i = 0; i < 10; i++) {
            medium += "999:Anonymous:";
        }
        medium = medium.substring(0, medium.length() - 1);
        for (int i = 0; i < 10; i++) {
            vaikea += "999:Anonymous:";
        }
        vaikea = vaikea.substring(0, vaikea.length() - 1);
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
     * Testimetodi. Tulostaa helpon vaikeustason tulokset.
     */
    public void tulostaHelpot() {
        for (int i = 0; i < 10; i++) {
            Tulos t = helppoTilasto.get(i);
            System.out.println((i + 1) + ". " + t.getNimi() + ": " + t.getAika());
        }
    }

    /**
     * Metodi tarkastaa, onko pelin suorittamiseen kulunut aika niin hyvä, että
     * se mahtuu parhaiden tulosten listalle helpolla vaikeustasolla.
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
     * Metodi tarkastaa, onko pelin suorittamiseen kulunut aika niin hyvä, että
     * se mahtuu parhaiden tulosten listalle keskivaikealla vaikeustasolla.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @return true, jos aika ansaitsee paikan parhaiden tulosten listalla
     */
    public boolean tarkastaMediumTulos(int aika) {
        // Jos tilastossa on jo 10 nopeampaa aikaa, palautetaan false
        if (mediumTilasto.get(9).getAika() < aika) {
            return false;
        }
        return true;
    }

    /**
     * Metodi tarkastaa, onko pelin suorittamiseen kulunut aika niin hyvä, että
     * se mahtuu parhaiden tulosten listalle vaikealla vaikeustasolla.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @return true, jos aika ansaitsee paikan parhaiden tulosten listalla
     */
    public boolean tarkastaVaikeaTulos(int aika) {
        // Jos tilastossa on jo 10 nopeampaa aikaa, palautetaan false
        if (vaikeaTilasto.get(9).getAika() < aika) {
            return false;
        }
        return true;
    }

    /**
     * Metodi sijoittaa ajan listalle ja siirtää huonompia aikoja listalla
     * alaspäin. Lopuksi talletetaan tulokset tiedostoon kutsumalla
     * talleta()-metodia.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @param nimi Pelaajan nimi
     */
    public void sijoitaHelppoTulos(int aika, String nimi) {
        Tulos uusiTulos = new Tulos(aika, nimi);
        int sija = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (aika < helppoTilasto.get(i).getAika()) {
                sija = i;
                break;
            }
        }
        helppoTilasto.add(uusiTulos);
        Collections.sort(helppoTilasto);
        while (helppoTilasto.size() > 10) {
            helppoTilasto.remove(10);
        }
        talleta();
    }

    /**
     * Metodi sijoittaa ajan listalle ja siirtää huonompia aikoja listalla
     * alaspäin. Lopuksi talletetaan tulokset tiedostoon kutsumalla
     * talleta()-metodia.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @param nimi Pelaajan nimi
     */
    public void sijoitaMediumTulos(int aika, String nimi) {
        Tulos uusiTulos = new Tulos(aika, nimi);
        int sija = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (aika < mediumTilasto.get(i).getAika()) {
                sija = i;
                break;
            }
        }
        mediumTilasto.add(uusiTulos);
        Collections.sort(mediumTilasto);
        while (mediumTilasto.size() > 10) {
            mediumTilasto.remove(10);
        }
        talleta();
    }

    /**
     * Metodi sijoittaa ajan listalle ja siirtää huonompia aikoja listalla
     * alaspäin. Lopuksi talletetaan tulokset tiedostoon kutsumalla
     * talleta()-metodia.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @param nimi Pelaajan nimi
     */
    public void sijoitaVaikeaTulos(int aika, String nimi) {
        Tulos uusiTulos = new Tulos(aika, nimi);
        int sija = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (aika < vaikeaTilasto.get(i).getAika()) {
                sija = i;
                break;
            }
        }
        vaikeaTilasto.add(uusiTulos);
        Collections.sort(vaikeaTilasto);
        while (vaikeaTilasto.size() > 10) {
            vaikeaTilasto.remove(10);
        }
        talleta();
    }

    /**
     * Metodi, joka kirjoittaa päivitetyt tulokset kaikista vaikeustasoista
     * tiedostoon.
     */
    public void talleta() {
        String helpot = "";
        String mediumit = "";
        String vaikeat = "";
        for (Tulos t : helppoTilasto) {
            helpot += t + ":";
        }
        for (Tulos t : mediumTilasto) {
            mediumit += t + ":";
        }
        for (Tulos t : vaikeaTilasto) {
            vaikeat += t + ":";
        }
        helpot.subSequence(0, helpot.length() - 1);
        mediumit.subSequence(0, mediumit.length() - 1);
        vaikeat.subSequence(0, vaikeat.length() - 1);

        try {
            kirjoittaja = new FileWriter(tiedosto);
        } catch (IOException e) {
            System.out.println("Tiedostoon ei päästy käsiksi.");
        }

        try {
            kirjoittaja.write(helpot + "\n");
            kirjoittaja.write(mediumit + "\n");
            kirjoittaja.write(vaikeat + "\n");
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }

    }

}
