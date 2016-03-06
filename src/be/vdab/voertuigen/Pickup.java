/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Volume;
import be.vdab.util.mens.Laadbaar;
import be.vdab.util.mens.Mens;
import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author Marc
 */
public class Pickup extends Personenwagen implements Laadbaar {
    private Volume laadvolume;
    
    public Pickup(String merk, Datum datum, int prijs, int plaatsen, Color kleur, Volume volume, Mens chauffeur, Mens... passagiers) {
        super(merk, datum, prijs, plaatsen, kleur, chauffeur, passagiers);
        this.laadvolume = volume;
    }
    
    @Override
    public void setLaadvolume (Volume vol) {
        this.laadvolume = vol;
    }
    
    @Override
    public Volume getLaadvolume () {
        return laadvolume;
    }    
   
    @Override
    public String toString() {
        return super.toString() + " " + laadvolume.toString();
    }
  
}
