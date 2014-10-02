package miinaharava;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testipaketti Main-luokalle, ihan vaan näön vuoksi.
 * @author Antti
 */
public class MainTest {
    
    @Test
    public void ajaMain() {
        Main main = new Main();
        String[] args = {""};
        main.main(args);
    }
    
}
