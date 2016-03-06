/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import be.vdab.util.Volume;

/**
 *
 * @author Marc
 */
public interface Laadbaar {
    
    public abstract Volume getLaadvolume();
    public abstract void setLaadvolume(Volume vol);
    
}
