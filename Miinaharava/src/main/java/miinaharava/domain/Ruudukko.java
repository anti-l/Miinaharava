
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
        this.leveys = 10;
        this.korkeus = 10;
        this.miinoja = 10;
        ruudukko = new Ruutu[leveys][korkeus];
        
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                ruudukko[i][j] = new Ruutu();
            }
        }
        int miinojaAsetettu = 0;
        while (miinojaAsetettu < miinoja) {
            int x = rand.nextInt(leveys);
            int y = rand.nextInt(korkeus);
            if (!(ruudukko[x][y].onkoMiinaa())) {
                ruudukko[x][y].setMiina();
                miinojaAsetettu++;
            }
        }
    }
    
    /**
     * Metodi, joka kertoo pelilaudan leveyden.
     * @return Pelilaudan leveys kokonaislukuna. Vakioarvo 10.
     */
    public int getLeveys() {
        return this.leveys;
    }
    
    /**
     * Metodi, joka kertoo pelilaudan korkeuden.
     * @return Pelilaudan korkeus kokonaislukuna. Vakioarvo 10.
     */
    public int getKorkeus() {
        return this.korkeus;
    }
    
    /**
     * Metodi, joka kertoo pelilaudan miinojen määrän.
     * @return Miinojen määrä kokonaislukuna. Vakioarvo 10.
     */
    public int getMiinoja() {
        return this.miinoja;
    }
    
    /**
     * Metodi, joka palauttaa pelilaudan tekstimuodossa. Tämä metodi käy läpi
     * koko ruudukko-taulukon, ja kysyy taulukossa olevan Ruutu-olion toString()
     * -metodia.
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
    
}
