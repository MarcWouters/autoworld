/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author marc.wouters
 */
public class Mens implements Comparable<Mens>, Serializable {

    private String naam;
    private Set<Rijbewijs> rijbewijzen = new TreeSet<>();

    public Mens(String naam) {
        this.naam = naam;
    }    
    public Mens (String naam, Rijbewijs... rijbewijzen) {
        this.naam = naam;      
        for(Rijbewijs rb : rijbewijzen) {
            this.rijbewijzen.add(rb);
        }
    }
    
    public String getNaam() {
        return naam;
    }

    public Rijbewijs[] getRijbewijs() {
        Rijbewijs[] rijbewijsArray = new Rijbewijs[rijbewijzen.size()];
        return rijbewijsArray = rijbewijzen.toArray(rijbewijsArray);
    }
        
    // automatisch gegenereerde equals()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.naam);
        hash = 13 * hash + Objects.hashCode(this.rijbewijzen);
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
        final Mens other = (Mens) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.rijbewijzen, other.rijbewijzen)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Mens o) {
          // compare 2 namen
        int vgl = this.naam.compareTo(o.naam);
        if (vgl == 0) {
            for ( Iterator<Rijbewijs> rb = rijbewijzen.iterator(); vgl == 0 && rb.hasNext() ; ) { 
                vgl = rb.next().compareTo(o.rijbewijzen.iterator().next());
            }
        }
        return vgl;
    }

    @Override
    public String toString() {
        String toS = naam;
        if (rijbewijzen.size() > 0) {
            toS += "(";
            for (Rijbewijs rb : this.rijbewijzen) {
                toS += rb.toString() + ", ";
            }
            toS = toS.substring(0, toS.length()-2) + ")";
        }    
        return toS;
    }
    
    
}
