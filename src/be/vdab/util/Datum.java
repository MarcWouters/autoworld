/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author marc.wouters
 */
public class Datum implements Serializable, Comparable<Datum> {
    private final int dag, maand, jaar;

    public Datum(int dag, int maand, int jaar) throws DatumException {
        // eerst nakijken of dag, maand en jaar een geldige datum maken
        if (checkData(dag, maand, jaar)) {            
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        } else {
            throw new DatumException ("Ongeldige data");
        }
    }

    public int getDag() {
        return dag;
    }
    public int getMaand() {
        return maand;
    }
    public int getJaar() {
        return jaar;
    }

    private boolean checkData (int dag, int maand, int jaar){
        // buitengrenzen van de parameters testen
        if (jaar < 1583 || jaar > 4099 || maand < 1 || maand > 12 || dag < 1 || dag > 31) {
            return false;
        }
        // maanden 1, 3, 5, 7, 8, 10 en 12 moeten niet meer getest worden
        switch (maand) {
            case 4:
            case 6:
            case 9:
            case 11:
                // april, juni, september en november hebben maar 30 dagen
                return (dag <= 30);
            case 2:
                // februari 29 (in schrikkeljaar) of 28 in niet-schrikkeljaar
                return ( (dag <= 29 && isSchrikkeljaar (jaar)) || (dag <= 28 && !isSchrikkeljaar (jaar)) );                    
        }
        return true;
    }
    
    private boolean isSchrikkeljaar (int jaar) {
        // elke 4 jaar, niet elke 100 jaar, maar wel elke 400 jaar
        return (jaar % 4 == 0 && (!(jaar % 100 == 0) || jaar % 400 == 0));
    }
    
    @Override
    public String toString() {
        // formaat : dd/mm/jjjj
        return String.format("%02d/%02d/%4d", dag, maand, jaar);
    }

    @Override
    public int compareTo(Datum d) {
        // eerst vergelijken op jaar, dan op maand, dan op dag
        int vgl = jaar - d.jaar;
        if (vgl == 0) {
            vgl = maand - d.maand;
            if (vgl == 0) {
                vgl = dag - d.dag;
            }
        }
        return vgl;
        
    }

    // automatisch gegenereerde equals()
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.dag;
        hash = 83 * hash + this.maand;
        hash = 83 * hash + this.jaar;
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
        final Datum other = (Datum) obj;
        if (this.dag != other.dag) {
            return false;
        }
        if (this.maand != other.maand) {
            return false;
        }
        if (this.jaar != other.jaar) {
            return false;
        }
        return true;
    }       
    
}
