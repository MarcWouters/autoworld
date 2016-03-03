/*
* @Autor Frank
* Dit is een oefening.
 */

package be.vdab.voertuigen.div;

/**
 *
 * @author frank.roelants
 */
public class Nummerplaat {
    private String plaat;

    public Nummerplaat(String plaat) {
        this.plaat = plaat;
    }

    public String getPlaat() {
        return plaat;
    }

    @Override
    public String toString() {
        return plaat;
    }
    
}
