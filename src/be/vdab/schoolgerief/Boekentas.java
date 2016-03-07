/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Volume;
import be.vdab.util.mens.Laadbaar;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author marc.wouters
 */
public class Boekentas implements Serializable, Laadbaar {
    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume laadvolume) {
        setLaadvolume (laadvolume);
        setKleur (kleur);
    }
 
    // Getter en setter voor laadvolume
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }
    @Override
    public void setLaadvolume(Volume vol) {
        if (vol == null) {
            throw new IllegalArgumentException(); 
        } else {
            this.laadvolume = vol;
        }        
    }

    // Getter en setter voor kleur
    public String getKleur() {
        return kleur;
    }
    public void setKleur(String kleur) {
        if (kleur == null) {
            throw new IllegalArgumentException(); 
        } else {
            this.kleur = kleur;
        }                
    }

    // automatisch gegenereerde code
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.laadvolume);
        hash = 29 * hash + Objects.hashCode(this.kleur);
        return hash;
    }

    // automatisch gegenereerde code
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
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        if (!Objects.equals(this.laadvolume, other.laadvolume)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("boekentas %s %s",kleur, laadvolume.toString());
    }
    
    
    
}
