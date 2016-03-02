/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author marc.wouters
 */
public class Mens implements Comparable<Mens> {
    private String naam;
    private List<Rijbewijs> rijbewijzen = new ArrayList<>();

    public Mens(String naam) {
        this.naam = naam;
    }
    
    public Mens (String naam, Rijbewijs... rijbewijs){
        this.naam = naam;
        this.rijbewijzen.add(rijbewijs[0]);
       
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
//        if (vgl == 0) {
//            for (Rijbewijs bewijs : rijbewijzen) {
//        while (vgl == 0) {
  //          rijbewijs
                
    //        }
      //  }
      return vgl;
    }
    
    
    
    
}
