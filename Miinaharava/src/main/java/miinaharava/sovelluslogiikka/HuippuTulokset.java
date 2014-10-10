package miinaharava.sovelluslogiikka;

import miinaharava.domain.Tulos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import miinaharava.domain.Vaikeustaso;

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
     * @return ArrayList sisältäen helpon vaikeustason tulokset.
     */
    public ArrayList<Tulos> getHelpot() {
        return this.helppoTilasto;
    }

    /**
     * Metodi, joka palauttaa keskivaikean vaikeustason pelitulokset.
     *
     * @return ArrayList sisältäen keskivaikean vaikeustason tulokset.
     */
    public ArrayList<Tulos> getMediumit() {
        return mediumTilasto;
    }

    /**
     * Metodi, joka palauttaa vaikean vaikeustason pelitulokset.
     *
     * @return ArrayList sisältäen vaikean vaikeustason tulokset.
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

        // Siirretään tilasto parempaan tietorakenteeseen. Jos tiedoston sisältö
        // on virheellinen, luodaan uusi.
        try {
            for (int i = 0; i < 20; i += 2) {
                helppoTilasto.add(new Tulos(Integer.parseInt(helppoHuiput[i]), helppoHuiput[i + 1]));
                mediumTilasto.add(new Tulos(Integer.parseInt(mediumHuiput[i]), mediumHuiput[i + 1]));
                vaikeaTilasto.add(new Tulos(Integer.parseInt(vaikeaHuiput[i]), vaikeaHuiput[i + 1]));
            }
        } catch (NumberFormatException e) {
            luoUusiTiedosto();
        }
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
     * Metodi tarkastaa, pääseekö peliin käytetyllä ajalla tietyllä
     * vaikeustasolla pelin tuloslistalle.
     *
     * @param aika Pelin voittamiseen kulunut aika.
     * @param vaikeustaso Enum Vaikeustaso
     * @return true, jos pääsee top 10:een; false, jos ei.
     */
    public boolean tarkastaTulos(int aika, Vaikeustaso vaikeustaso) {
        if (vaikeustaso == Vaikeustaso.HELPPO) {
            if (aika > helppoTilasto.get(9).getAika()) {
                return false;
            }
            return true;
        } else if (vaikeustaso == Vaikeustaso.KESKIVAIKEA) {
            if (mediumTilasto.get(9).getAika() < aika) {
                return false;
            }
            return true;
        } else if (vaikeustaso == Vaikeustaso.VAIKEA) {
            if (vaikeaTilasto.get(9).getAika() < aika) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Metodi sijoittaa ajan listalle ja siirtää huonompia aikoja listalla
     * alaspäin. Lopuksi talletetaan tulokset tiedostoon kutsumalla
     * talleta()-metodia.
     *
     * @param aika Pelin voittamiseen kulunut aika
     * @param nimi Pelaajan nimi
     * @param vaikeustaso Enum Vaikeustaso - HELPPO, KESKIVAIKEA, VAIKEA
     */
    public void sijoitaTulos(int aika, String nimi, Vaikeustaso vaikeustaso) {
        ArrayList<Tulos> tulosTilasto = new ArrayList();
        if (vaikeustaso == Vaikeustaso.HELPPO) {
            tulosTilasto = helppoTilasto;
        } else if (vaikeustaso == Vaikeustaso.KESKIVAIKEA) {
            tulosTilasto = mediumTilasto;
        } else if (vaikeustaso == Vaikeustaso.VAIKEA) {
            tulosTilasto = vaikeaTilasto;
        } else {
            return;
        }

        Tulos uusiTulos = new Tulos(aika, nimi);
        int sija = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (aika < tulosTilasto.get(i).getAika()) {
                sija = i;
                break;
            }
        }
        tulosTilasto.add(uusiTulos);
        Collections.sort(tulosTilasto);
        while (tulosTilasto.size() > 10) {
            tulosTilasto.remove(10);
        }

        if (vaikeustaso == Vaikeustaso.HELPPO) {
            helppoTilasto = tulosTilasto;
        } else if (vaikeustaso == Vaikeustaso.KESKIVAIKEA) {
            mediumTilasto = tulosTilasto;
        } else if (vaikeustaso == Vaikeustaso.VAIKEA) {
            vaikeaTilasto = tulosTilasto;
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
        helpot = (String) helpot.subSequence(0, helpot.length() - 1);
        mediumit = (String) mediumit.subSequence(0, mediumit.length() - 1);
        vaikeat = (String) vaikeat.subSequence(0, vaikeat.length() - 1);

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
