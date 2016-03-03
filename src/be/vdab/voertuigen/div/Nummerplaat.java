/*
* @Autor Frank
* Dit is een oefening.
 */

package be.vdab.voertuigen.div;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author frank.roelants
 */
public class Nummerplaat implements Serializable, Comparable<Nummerplaat> {
    private final String plaat;

    Nummerplaat(String plaat) {
        this.plaat = plaat;
    }

    public String getPlaat() {
        return plaat;
    }

    @Override
    public int compareTo(Nummerplaat o) {
        // compare 2 strings
        return this.plaat.compareTo(o.plaat);
    }

    @Override
    public String toString() {
        return plaat;
    }

    // automatisch gegenereerde hashCode()
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.plaat);
        return hash;
    }

    // automatisch gegenereerde equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nummerplaat other = (Nummerplaat) obj;
        
        return Objects.equals(this.plaat, other.plaat);            
    }
    
}
