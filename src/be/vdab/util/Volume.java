/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import be.vdab.voertuigen.Voertuig;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Marc
 */
public class Volume implements Serializable, Comparable<Volume>  {
    private final int hoogte, breedte, diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.diepte = diepte;
        if (getVolume() < 0) {
            throw new VolumeException ("Negatief volume mag niet");
        }
        this.maat = maat;
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }
    
    public long getVolume() {
        // TODO : vragen of alle volumes in zelfde maat moeten berekend worden
        // impact op compareTo method
        return (long) (hoogte * breedte * diepte);        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.hoogte;
        hash = 83 * hash + this.breedte;
        hash = 83 * hash + this.diepte;
        hash = 83 * hash + Objects.hashCode(this.maat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO : is 1x1x1 meter equal aan 10x10x10 decimeter ?
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.hoogte != other.hoogte) {
            return false;
        }
        if (this.breedte != other.breedte) {
            return false;
        }
        if (this.diepte != other.diepte) {
            return false;
        }
        if (this.maat != other.maat) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public int compareTo(Volume o) {
        long thisVolume = getVolume();
        long otherVolume = o.getVolume();
        
        switch (this.maat) {
            case decimeter : 
                thisVolume *= 1000L;
                break;
            case meter : 
                thisVolume *= 1000_000L;
                break;                
        }
        switch (o.getMaat()) {
            case decimeter : 
                otherVolume *= 1000L;
                break;
            case meter : 
                otherVolume *= 1000_000L;
                break;                
        }
        
        if (thisVolume == otherVolume) {
            return 0;
        }
        return (thisVolume >  otherVolume ? 1 : -1);
        
    }

    @Override
    public String toString() {
        return String.format("%d(h)x%d(b)x%d(d) %s", hoogte, breedte, diepte, maat.toString());
    }

 

}
