
package miinaharava.domain;

public class Ruutu {
    
    private boolean miina;
    private int viereisia;
    
    public Ruutu() {
        this.miina = false;
        this.viereisia = 0;
    }
    
    public void setMiina() {
        this.miina = true;
    }
    
    public void setViereisia(int maara) {
        if (maara <= 8 || maara >= 0) {
            this.viereisia = maara;
        }
    }
    
}
