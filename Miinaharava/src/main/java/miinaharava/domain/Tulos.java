package miinaharava.domain;

/**
 * Luokka, joka pitää kirjaa yhdestä huipputuloksesta.
 *
 * @author Antti
 */
public class Tulos implements Comparable<Tulos> {

    private int aika;
    private String nimi;

    /**
     * Konstruktori, jolle annetaan tuloksen aika ja pelaajan nimi.
     *
     * @param aika Peliin käytetty aika kokonaislukuna.
     * @param nimi Pelaajan nimi.
     */
    public Tulos(int aika, String nimi) {
        this.aika = aika;
        this.nimi = nimi;
    }

    /**
     * Palauttaa ajan tehneen pelaajan nimen.
     *
     * @return Pelaajan nimi.
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Palauttaa tämän tuloksen ajan.
     *
     * @return Tuloksen aika.
     */
    public int getAika() {
        return this.aika;
    }
    
    /**
     * Metodi, jolla voidaan suorittaa tulosten vertailua. 
     * Jos verrattava tulos on pienempi kuin tämä tulos, palautetaan -1.
     * Jos verrattava tulos on suurempi kuin tämä tulos, palautetaan  1.
     * Jos verrattavat tulokset ovat yhtä suuret, palautetaan 0.
     * 
     * @param toinen Toinen vertailtava tulos.
     * @return Kokonaisluku -1, 0 tai 1.
     */
    @Override
    public int compareTo(Tulos toinen) {
        Tulos tulos = (Tulos) toinen;
        if (tulos.getAika() < this.aika) {
            return 1;
        } else if (tulos.getAika() > this.aika) {
            return -1;
        }
        return 0;
    }

    /**
     * Metodi palauttaa tämän tuloksen ajan sekä tekijän nimen.
     * @return Palauttaa merkkijonon muotoa [aika]:[nimi]
     */
    public String toString() {
        return "" + this.aika + ":" + this.nimi;
    }
}
