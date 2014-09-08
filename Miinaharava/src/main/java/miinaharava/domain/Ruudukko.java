package miinaharava.domain;

import java.util.Random;

/**
 *
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

    public Ruudukko(int leveys, int korkeus, int miinoja) {
        if (leveys < 1 || korkeus < 1 || miinoja < 1) {
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

    public void asetaMiinat(int maara) {
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
    
    public void asetaViereistenMiinojenMaarat() {
        for (int i = 0; i < this.leveys; i++) {
            for (int j = 0; j < this.korkeus; j++) {
                int viereisiaMiinoja = laskeRuutuaYmparoivatMiinat(i, j);
                this.getRuutu(i, j).setViereisia(viereisiaMiinoja);
            }
        }
        
    }
    
    public int laskeRuutuaYmparoivatMiinat(int x, int y) {
        int viereisiaMiinoja = 0;
        int alaX, alaY, ylaX, ylaY;
        
        if (x == 0) {
            alaX = 0;
            ylaX = 1;
        } else if (x == leveys) {
            alaX = leveys - 1;
            ylaX = leveys;
        } else {
            alaX = x - 1;
            ylaX = x + 1;
        }
        
        if (y == 0) {
            alaY = 0;
            ylaY = 1;
        } else if (y == korkeus) {
            alaY = korkeus - 1;
            ylaY = korkeus;
        } else {
            alaY = y - 1;
            ylaY = y + 1;
        }
        
        for (int i = alaX; i < ylaX; i++) {
            for (int j = alaY; j < ylaY; j++) {
                System.out.println("TestX:" + x + " Y:" + y + " aX:" + alaX + " yX:" + ylaX + " aY:" + alaY + " yY:" + ylaY);
                if (this.getRuutu(i, j).onkoMiinaa()) {
                    viereisiaMiinoja++;
                }
            }
        }
        return viereisiaMiinoja;
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

    public Ruutu getRuutu(int x, int y) {
        return ruudukko[x][y];
    }

}
