/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;

/**
 *
 * @author Marc
 */
public class Personenwagen extends Voertuig {
    private Color kleur;
    private static final int MAX_ZITPLAATSEN = 8; 

    public Personenwagen(String merk, Datum datum, int prijs, int plaatsen, Color kleur, Mens chauffeur, Mens... passagiers) {
        super(merk, datum, prijs, plaatsen, chauffeur, passagiers);
        this.kleur = kleur;
        if (plaatsen > MAX_ZITPLAATSEN) {
            throw new  IllegalArgumentException();
        }        
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }
    
    @Override    
    protected int getMAX_ZITPLAATSEN() {            /// zelf toegevoegd
            return MAX_ZITPLAATSEN;
        }

    public Color getKleur() {
        return kleur;
    }
    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    
    @Override
    public String toString() {
        return super.toString() + " " + Integer.toString(getZitplaatsen());
    }
  
}
