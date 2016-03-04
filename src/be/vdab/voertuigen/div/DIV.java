/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

/**
 *
 * @author marc.wouters
 */
public enum DIV {
    // unieke enum : singleton
    INSTANCE;
    
    // package visibility : enig nummer
    static int nummer = 0;
            
    public Nummerplaat getNummerplaat() {
        // nummer gaat omhoog bij iedere aanvraag
        // van 999 naar 001
        nummer = (nummer == 999) ? 1 : ++nummer;
        return new Nummerplaat(String.format("AAA%3d", nummer));
    }
}
