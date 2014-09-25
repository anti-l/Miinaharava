package miinaharava.domain;

import java.util.Random;

/**
 * Ruudukko on luokka, joka pitää huolta pelilaudasta. Ruudukko koostuu 
 * kaksiulotteisesta taulukosta Ruutuja, jotka tietävät itse sisältönsä.
 * Ruudukon vastuuna on pelilaudan luominen, miinojen asettaminen, viereisten
 * miinojen määrien kertominen Ruuduilleen sekä pelin loppuehtojen tarkastaminen.
 * @author Antti Lundén
 */
public class Ruudukko {

    private int leveys;
    private int korkeus;
    private int miinoja;
    private Ruutu[][] ruudukko;
    private Random rand = new Random();

    /**
     * Konstruktori luo uuden pelilaudan eli ruudukon. Ilman parametrejä
     * pelilaudan koko on 10x10 ruutua ja miinoja sinne asetetaan 10 kappaletta.
     *
     * Pelilauta luodaan kaksiulotteisesta taulukosta, jossa säilytetään Ruutu-
     * luokan olioita.
     */
    public Ruudukko() {
        this(10, 10, 10);
    }

    /**
     * Tällä konstruktorilla voidaan luoda myös muun kokoisia pelilautoja. 
     * Konstruktori tarkastaa, että parametrit ovat validit, ja jos ei ole, se 
     * luo standardikokoisen ruudukon.
     * 
     * Ruudukon luomisen jälkeen konstruktori asettaa miinat paikoilleen ja 
     * kertoo ruudukon Ruuduille niiden viereisten miinojen määrät.
     * 
     * @param leveys Ruudukon leveys kokonaislukuna.
     * @param korkeus Ruudukon korkeus kokonaislukuna.
     * @param miinoja Miinojen määrä kokonaislukuna.
     */
    public Ruudukko(int leveys, int korkeus, int miinoja) {
        if (leveys < 1 || korkeus < 1 || miinoja < 1 || miinoja > leveys * korkeus) {
            this.leveys = 10;
            this.korkeus = 10;
            this.miinoja = 10;
        } else {
            this.leveys = leveys;
            this.korkeus = korkeus;
            this.miinoja = miinoja;
        }
        ruudukko = new Ruutu[this.leveys][this.korkeus];

        for (int i = 0; i < this.leveys; i++) {
            for (int j = 0; j < this.korkeus; j++) {
                ruudukko[i][j] = new Ruutu();
            }
        }
        asetaMiinat(miinoja);
        asetaViereistenMiinojenMaarat();
    }
    
    /**
     * Asettaa arpoo ruudukkoon miinat paikoilleen. Miinaa ei aseteta, jos 
     * ruudussa on jo miina. 
     * @param maara Ruudukkoon asetettavien miinojen määrä.
     */

    public void asetaMiinat(int maara) {
        if (maara < 1) {
            maara = 1;
        }
        int miinojaAsetettu = 0;
        while (miinojaAsetettu < maara) {
            int x = rand.nextInt(leveys);
            int y = rand.nextInt(korkeus);
            if (!(ruudukko[x][y].onkoMiinaa())) {
                ruudukko[x][y].setMiina();
                miinojaAsetettu++;
            }
        }
    }
    
    /**
     * Kertoo pelilaudan Ruuduille niiden viereisten Ruutujen miinojen 
     * yhteenlasketun määrän.
     */
    public void asetaViereistenMiinojenMaarat() {
        for (int i = 0; i < this.leveys; i++) {
            for (int j = 0; j < this.korkeus; j++) {
                int viereisiaMiinoja = laskeRuutuaYmparoivatMiinat(i, j);
                this.getRuutu(i, j).setViereisia(viereisiaMiinoja);
            }
        }
        
    }
    
    /**
     * Metodi laskee yhtä ruutua ympäröivät miinat ja palauttaa laskemansa arvon.
     * @param x Testattavan ruudun x-koordinaatti
     * @param y Testattavan ruudun y-koordinaatti
     * @return ruudun viereisten ruutujen mahdollisten miinojen yhteenlaskettu määrä
     */
    public int laskeRuutuaYmparoivatMiinat(int x, int y) {
        int viereisiaMiinoja = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == x && j == y) {
                    continue;
                } else if (x + i < 0 || x + i >= this.leveys) {
                    continue;
                } else if (y + j < 0 || y + j >= this.korkeus) {
                    continue;
                } else if (this.getRuutu((x+i), (y+j)).onkoMiinaa()) {
                    viereisiaMiinoja++;
                }
            }
        }
        return viereisiaMiinoja;
    }
    
    /**
     * Metodi kertoo, onko koordinaatit ruudukon sisällä vai sen ulkopuolella.
     * @param x testattava y-koordinaatti
     * @param y testattava y-koordinaatti
     * @return true, jos ollaan ruudukon sisällä (10x10-ruudukossa arvot 0-9);
     *         false, jos ollaan ruudukon ulkopuolella.
     */
    public boolean onRuudukossa(int x, int y) {
        if (x < 0 || x >= this.leveys) {
            if (y < 0 || y >= this.korkeus) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodi, joka kertoo pelilaudan leveyden.
     *
     * @return Pelilaudan leveys kokonaislukuna. Vakioarvo 10.
     */
    public int getLeveys() {
        return this.leveys;
    }

    /**
     * Metodi, joka kertoo pelilaudan korkeuden.
     *
     * @return Pelilaudan korkeus kokonaislukuna. Vakioarvo 10.
     */
    public int getKorkeus() {
        return this.korkeus;
    }

    /**
     * Metodi, joka kertoo pelilaudan miinojen määrän.
     *
     * @return Miinojen määrä kokonaislukuna. Vakioarvo 10.
     */
    public int getMiinoja() {
        return this.miinoja;
    }

    /**
     * Metodi, joka palauttaa pelilaudan tekstimuodossa. Tämä metodi käy läpi
     * koko ruudukko-taulukon, ja kysyy taulukossa olevan Ruutu-olion toString()
     * -metodia.
     *
     * @return Tekstipohjainen kuvaus pelilaudasta, miinat mukaan lukien.
     */
    public String toString() {
        String kentta = "";
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                kentta += ruudukko[i][j];
            }
            kentta += "\n";
        }
        return kentta;
    }
    
    /**
     * Palauttaa yksittäisen ruudun annetuista koordinaateista.
     * @param x Halutun ruudun x-koordinaatti.
     * @param y Halutun ruudun y-koordinaatti.
     * @return Ruutu koordinaateista (x, y).
     */
    public Ruutu getRuutu(int x, int y) {
        return ruudukko[x][y];
    }
    
    
    /**
     * Metodi tarkastaa, onko pelaaja asettanut miinoja merkkaavat liput oikein
     * paikoilleen. 
     * @return true, jos miinojen ja lippujen paikat täsmäävät, false, jos ei.
     */
    public boolean onkoMiinatLiputettu() {
        boolean tasmaakoMiinojenJaLippujenPaikat = false;
        boolean onnistuukoSilmukka = true;
        for (int i = 0; i < this.leveys; i++) {
            for (int j = 0; j < this.korkeus; j++) {
                if (ruudukko[i][j].getLiputettu()) {
                    if (ruudukko[i][j].onkoMiinaa() == false) {
                       onnistuukoSilmukka = false;
                    }
                }
            }
        }
        if (onnistuukoSilmukka) {
            tasmaakoMiinojenJaLippujenPaikat = true;
        }
        return tasmaakoMiinojenJaLippujenPaikat;
    }
    
}
