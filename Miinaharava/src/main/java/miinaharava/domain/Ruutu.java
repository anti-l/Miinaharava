package miinaharava.domain;

/**
 *
 * @author Antti Lundén
 */
public class Ruutu {
    
    private boolean miina;
    private boolean katsottu;
    private boolean liputettu;
    private int viereisia;
    
    public Ruutu() {
        this.miina = false;
        this.katsottu = false;
        this.liputettu = false;
        this.viereisia = 0;
    }
    
    public void setMiina() {
        this.miina = true;
    }
    
    public void setViereisia(int maara) {
        if (maara <= 8 && maara >= 0) {
            this.viereisia = maara;
        }
    }
    
    public int getViereiset() {
        return viereisia;
    }
    
    public boolean onkoMiinaa() {
        if (miina) {
            return true;
        }
        return false;
    }
    
    public boolean getKatsottu() {
        return this.katsottu;
    }
    
    public void katsoRuutu() {
        this.katsottu = true;
    }
    
    public boolean onkoTyhja() {
        if (miina == false && viereisia == 0) {
            return true;
        }
        return false;
    }
    
    public void setLiputettu() {
        this.liputettu = true;
    }
    
    public void removeLiputettu() {
        this.liputettu = false;
    }
    
    public boolean getLiputettu() {
        return this.liputettu;
    }
    
    
    public String toString() {
        if (miina) {
            return "X";
        }
        return ".";
    }
    
}
