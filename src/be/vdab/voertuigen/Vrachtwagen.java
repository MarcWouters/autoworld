/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Volume;
import be.vdab.util.mens.Laadbaar;
import be.vdab.util.mens.Mens;
import be.vdab.util.Datum;
import be.vdab.util.mens.Rijbewijs;

/**
 *
 * @author marc.wouters
 */
public class Vrachtwagen extends Voertuig implements Laadbaar {
    private Volume laadvolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    private static final int MAX_ZITPLAATSEN = 3; 

    public Vrachtwagen(String merk, Datum datum, int prijs, int plaatsen, Volume laadvolume, int maxMassa, int assen, Mens chauffeur, Mens... passagiers) {
        super(merk, datum, prijs, plaatsen, chauffeur, passagiers);
        if (plaatsen > MAX_ZITPLAATSEN) {
            throw new IllegalArgumentException();
        }
        setLaadvolume (laadvolume);
        setMaximaalToegelatenMassa (maxMassa);
        setAantalAssen (assen);
    }
    
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }
    
    @Override    
    protected int getMAX_ZITPLAATSEN() {            /// zelf toegevoegd
            return MAX_ZITPLAATSEN;
        }
    
    // Getter en setter voor laadvolume
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }
    @Override
    public void setLaadvolume(Volume laadvolume) {
        this.laadvolume = laadvolume;
    }
    
    // Getter en setter voor maximaalToegelatenMassa
    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }
    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }
    
    // Getter en setter voor aantalAssen
    public int getAantalAssen() {
        return aantalAssen;
    }
    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }

    @Override
    public String toString() {
        return super.toString() + 
                String.format(" assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", 
                Integer.toString(aantalAssen), Integer.toString(maximaalToegelatenMassa),
                laadvolume.toString());
    }
           
    
}
