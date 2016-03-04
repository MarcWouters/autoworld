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
public abstract class Voertuig implements Serializable, Comparable<Voertuig> {
    private final Nummerplaat nummerplaat = DIV.INSTANCE.getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private int zitplaatsen;
    private Mens bestuurder;
    private Set<Mens> ingezetenen = new TreeSet<>();

//    public Voertuig(String merk, Datum datum, int prijs, int zitplaatsen, Mens bestuurder, Mens... inzittenden) throws MensException {
    public Voertuig(String merk, Datum datum, int prijs, int plaatsen, Mens bestuurder, Mens... inzittenden) {
        setMerk (merk);
        setDatumEersteIngebruikname (datum);
        setAankoopprijs (prijs);
        setZitplaatsen (plaatsen);
        this.bestuurder = bestuurder;      
        if (inzittenden.length <= zitplaatsen) {
            for(Mens mens : inzittenden) {
                this.ingezetenen.add(mens);
            }
        }
/*        else {
            throw new MensException ("Teveel inzittenden voor deze auto");                        
        }
 */      
    }

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
    
//    private final void setZitplaatsen(int plaatsen) throws Exception {
    private final void setZitplaatsen(int plaatsen) {
        if (plaatsen > 0) {
            this.zitplaatsen = plaatsen;
        }
        else {
            // MOET IK HIER EEN exception throwen
//            throw new IllegalArgumentException();
        }
    }
    
    public Mens getBestuurder() {
        return bestuurder;
    }
    
//    public void setBestuurder(Mens bestuurder) throws MensException {
    public void setBestuurder(Mens bestuurder) {
        if ((ingezetenen.size() < zitplaatsen) || ingezetenen.contains(bestuurder)) {
            ingezetenen.add (this.bestuurder);
            this.bestuurder = bestuurder;
        }
        else {
            // geen plaats meer voor nieuwe bestuurder
            // throw new MensException ("Geen plaats meer voor de nieuwe bestuurder");
        }
    }
    
//    public void addIngezetene(Mens ingezetene) throws MensException {
    public void addIngezetene(Mens ingezetene) {
        if ((ingezetenen.size() < zitplaatsen) || ingezetenen.contains(ingezetene)) {
            ingezetenen.add (ingezetene);
        }
        else {
            // geen plaats meer voor nieuwe bestuurder
            // throw new MensException ("Geen plaats meer voor de nieuwe bestuurder");
        }
    }
    
    public Set<Mens> getIngezetenen() {
        Set<Mens> bestuurderPlusIngezetenen = new TreeSet<>(ingezetenen);
        bestuurderPlusIngezetenen.add(bestuurder);
        return bestuurderPlusIngezetenen;
    }

    public Set<Mens> getIngezeteneExclusiefBestuurder() {
        return ingezetenen;
    }

    public boolean isIngezetene (Mens m) {
        return true;
    }
    abstract Rijbewijs[] getToegestaneRijbewijzen();
    abstract int getMAX_ZITPLAATSEN();

 
    // automatische gegenereerde method
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nummerplaat);
        return hash;
    }

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
