/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import java.io.Serializable;
import be.vdab.voertuigen.div.*;
import be.vdab.util.*;
import be.vdab.util.mens.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author marc.wouters
 */
public abstract class Voertuig implements Serializable, Comparable<Voertuig>  {
    private final Nummerplaat nummerplaat = DIV.INSTANCE.getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private int zitplaatsen;
    private Mens bestuurder;
    private Set<Mens> ingezetenen = new TreeSet<>();

    // Constructor moet de error MensException catchen,
    // want niet gedeclareerd in de @Test
    public Voertuig(String merk, Datum datum, int prijs, int plaatsen, Mens chauffeur, Mens... passagiers) {
        setMerk (merk);
        setDatumEersteIngebruikname (datum);
        setAankoopprijs (prijs);
        setZitplaatsen (plaatsen);
        if (checkRijbewijs (chauffeur)) {
            this.bestuurder = chauffeur;
        }
        for (Mens pas : passagiers) {
            if (checkAantalIngezetenen (pas)) {
                ingezetenen.add (pas);
            } 
        }
    }
 
    abstract Rijbewijs[] getToegestaneRijbewijzen();
    abstract int getMAX_ZITPLAATSEN();

    public Nummerplaat getNummerplaat () {
        return nummerplaat;
    }
    
    public String getMerk() {
        return merk;
    }
    public void setMerk(String merk) {
        this.merk = merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }
    public void setDatumEersteIngebruikname(Datum datum) {
        this.datumEersteIngebruikname = datum;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }
    public void setAankoopprijs(int prijs) {
        this.aankoopprijs = prijs;
    }

    public int getZitplaatsen() {
        return zitplaatsen;
    }       
    private final void setZitplaatsen(int plaatsen) {
        if (plaatsen > 0 && plaatsen <= getMAX_ZITPLAATSEN()) {
            this.zitplaatsen = plaatsen;
        } else {           
            throw new IllegalArgumentException();
        }
    }
    
    // Getters voor de inzittenden : bestuurder, passagiers, of iedereen samen
    public Mens getBestuurder() {
        return bestuurder;
    }
    public Set<Mens> getIngezeteneExclusiefBestuurder() {
        return ingezetenen;
    }
    public Set<Mens> getIngezetenen() {
        Set<Mens> bestuurderPlusIngezetenen = new TreeSet<>(ingezetenen);
        bestuurderPlusIngezetenen.add(bestuurder);
        return bestuurderPlusIngezetenen;
    }

    // Setter (voor bestuurder) en adder (voor passagier)
    // Geen try en catch, want RuntimeException = unchecked
    public void setBestuurder(Mens chauffeur) {           
    
        if (!bestuurder.equals(chauffeur) && checkRijbewijs(chauffeur) && checkAantalIngezetenen (chauffeur)) {
            ingezetenen.add (this.bestuurder);
            this.bestuurder = chauffeur;
        }            
    }       
    public void addIngezetene(Mens passagier) {
            if (!bestuurder.equals(passagier) && checkAantalIngezetenen (passagier)) {
                ingezetenen.add (passagier);
            }
    }
    
    // chauffeur moet een rijbewijs hebben, 
    // en het moet geldig zijn voor het huidige voertuig
//    private boolean checkRijbewijs (Mens chauffeur) throws MensException {
    private boolean checkRijbewijs (Mens chauffeur) {
        
        if (chauffeur.getRijbewijs().length == 0) {
            throw new MensException (chauffeur.toString() + " : geen rijbewijs");
        } else {
            // TODO : hier moet waarschijnlijk een 'matchesAny' of zoiets komen om de code compacter te maken
            boolean geldig = false;
            for (Rijbewijs geldigRB : getToegestaneRijbewijzen()) {
                for (Rijbewijs chauffeurRB : chauffeur.getRijbewijs()) {
                    if(geldigRB.equals(chauffeurRB)) {
                        geldig = true;
                    }
                }
            }
            if (geldig) {
                return true;
            } else {
                throw new MensException (chauffeur.toString () + " : ongeldig rijbewijs");
            }  
        }
    }
    
//    private boolean checkAantalIngezetenen (Mens nieuwePassagier) throws MensException {
    private boolean checkAantalIngezetenen (Mens nieuwePassagier) {
        
        Set<Mens> huidigePassagiers = getIngezetenen();
        if (huidigePassagiers.size() < zitplaatsen) {
            return true;
        }
/*  TODO:   deze code blijkt niet te werken, geen idee waarom
        if (ingezetenen.contains(nieuwePassagier) ) {  
            return true;
        }
*/
        for (Mens mens : huidigePassagiers) {
            if (mens.equals(nieuwePassagier)) {
                return true;
            }
        }
        // als de code hier geraakt, is het niet goed
        throw new MensException (nieuwePassagier.toString () + " kan er niet meer bij");
        
    }    
  
// TODO  : de tests voor deze methods testen alleen op assertTrue;
// als ik steeds "return true;" stuur pass ik elke test, zonder echt te checken
    public boolean isIngezetene (Mens mens) {
        // TODO : waarom werkt die eerste lijn niet ???
        // return getIngezetenen().contains (mens);
        return  (bestuurder.equals(mens) || ingezetenen.contains(mens));
    }
 
    // automatische gegenereerde method
 /*   @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.nummerplaat);
        return hash;
    }
*/
    // automatische gegenereerde method
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
        final Voertuig other = (Voertuig) obj;
        if (!Objects.equals(this.nummerplaat, other.nummerplaat)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Voertuig o) {
        return this.nummerplaat.compareTo (o.getNummerplaat());
        
    }


    public static Comparator<Voertuig> getMerkComparator () {
        
        return new Comparator<Voertuig> () {
        
           // @Override
            public int compare (Voertuig v1, Voertuig v2) {
                return v1.merk.compareTo (v2.merk);
            }
        };
    }    

    public static Comparator<Voertuig> getAankoopprijsComparator () {
        
        return new Comparator<Voertuig> () {
        
            //@Override
            public int compare (Voertuig v1, Voertuig v2) {
                return v1.aankoopprijs - v2.aankoopprijs;
            }
        };
    }    
    
    
    @Override
    public String toString() {
 
        String toS = nummerplaat + " " + merk + " " + datumEersteIngebruikname +  " " +
                 Integer.toString(aankoopprijs) + " " + bestuurder.toString();
        if (ingezetenen.size() > 0) {
            toS += " [";
            for (Mens ing : ingezetenen) {
                toS += ing.getNaam() + ", ";
            }
            toS = toS.substring(0, toS.length()-2) + "]";
        }    
        
        return toS;
    }
    
    
}
