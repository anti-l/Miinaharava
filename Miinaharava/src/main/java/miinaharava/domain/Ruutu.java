package miinaharava.domain;

/**
 * Ruutu on luokka, joista pelilauta koostuu. Jokainen Ruutu sisältää tiedon
 * omasta statuksestaan - onko Ruudussa miinaa, onko Ruutu jo katsottu, tai 
 * onko se liputettu. Ruutu tietää myös viereisten miinojen määrät, jotka
 * Ruudukko on sille luontivaiheessa kertonut.
 * @author Antti Lundén
 */
public class Ruutu {
    
    private boolean miina;
    private boolean katsottu;
    private boolean liputettu;
    private int viereisia;
    
    /**
     * Konstruktori, joka asettaa kaikki attribuutit epätosiksi ja viereisten
     * miinojen määrän nollaksi. Myöhemmin attribuutit vaihdetaan tosiksi 
     * ruuduille, joissa on miinoja, tai joita pelaaja tarkastaa tai liputtaa.
     */
    public Ruutu() {
        this.miina = false;
        this.katsottu = false;
        this.liputettu = false;
        this.viereisia = 0;
    }
    
    /**
     * Asettaa ruutuun miinan. Tätä metodia kutsuu Ruudukko pelilautaa luodessaan.
     */
    public void setMiina() {
        this.miina = true;
    }
    
    /**
     * Asettaa ruudulle sen viereisten ruutujen sisältämien miinojen määrän.
     * @param maara Viereisten ruutujen sisältämien miinojen määrä.
     */
    public void setViereisia(int maara) {
        if (maara < 9 && maara >= 0) {
            this.viereisia = maara;
        }
    }
    
    /**
     * Metodi palauttaa viereisten ruutujen miinojen määrän.
     * @return Viereisten ruutujen miinojen määrä.
     */
    public int getViereiset() {
        return viereisia;
    }
    
    /**
     * Metodi kertoo, onko ruudussa miinaa vai ei.
     * @return true, jos ruudussa on miina, false, jos ei.
     */
    public boolean onkoMiinaa() {
        if (miina) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kertoo, onko ruutu jo katsottu pelaajan toimesta.
     * @return true, jos ruutu on jo katsottu, false, jos ei.
     */
    public boolean getKatsottu() {
        return this.katsottu;
    }
    
    /**
     * Asettaa ruudun katsotuksi.
     */
    public void katsoRuutu() {
        this.katsottu = true;
    }
    
    /**
     * Metodi katsoo, onko ruutu tyhjä. Ruutu on tyhjä, jos siinä ei ole miinaa,
     * eikä sen viereisistäkään ruuduista löydy miinoja.
     * @return true, jos ruutu on tyhjä, false, jos ei.
     */
    public boolean onkoTyhja() {
        if (miina == false && viereisia == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Asettaa ruudun liputetuksi. Pelaaja voi liputtaa ruutuja, jos niissä 
     * hänen mielestään on miina.
     */
    public void setLiputettu() {
        this.liputettu = true;
    }
    
    /**
     * Poistaa ruudusta lipun. Jos pelaajan mielestä ruudussa ei olekaan miinaa,
     * hän voi myös poistaa siihen asettamansa merkkilipun.
     */
    public void removeLiputettu() {
        this.liputettu = false;
    }
    
    /**
     * Metodi kertoo, onko ruutu liputettu vai ei.
     * @return true, jos ruutu on liputettu, false, jos ei.
     */
    public boolean getLiputettu() {
        return this.liputettu;
    }
    
    /**
     * Metodi palauttaa yhden merkin pituisen merkkijonon ruudun sisällöstä.
     * @return X, jos ruudussa on miina, ., jos ei.
     */
    @Override
    public String toString() {
        if (miina) {
            return "X";
        }
        return ".";
    }
    
}
