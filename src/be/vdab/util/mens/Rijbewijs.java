/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

/**
 *
 * @author marc.wouters
 */
public enum Rijbewijs {
    // volgorde van items is belangrijk!!
    A, B, BE, C, CE, D, DE;


    @Override
    public String toString() {
        String s = super.toString();
        if (s.contains("E")){
            s = s.substring(0,1) + "+" + s.substring(1);
        }
        return s;
    }
    
    
    
    
}
