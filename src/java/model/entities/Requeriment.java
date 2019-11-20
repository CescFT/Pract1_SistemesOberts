/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Cesc
 */
@Embeddable
public class Requeriment implements Serializable{
    
    
    private boolean home;
    
    private boolean dona;
    
    private boolean unisex;
    
    private int rangEdatMin;
    private int rangEdatMax;
    
    private Boolean fumador;
    
    private Boolean mascotes;
    
    public Requeriment(){
        
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public boolean isDona() {
        return dona;
    }

    public void setDona(boolean dona) {
        this.dona = dona;
    }

    public boolean isUnisex() {
        return unisex;
    }

    public void setUnisex(boolean unisex) {
        this.unisex = unisex;
    }
    
    

    public int getRangEdatMin() {
        return rangEdatMin;
    }

    public void setRangEdatMin(int rangEdatMin) {
        if(rangEdatMin < 0 ) this.rangEdatMin = 0;
        else this.rangEdatMin = rangEdatMin;
        
        if(this.rangEdatMax < this.rangEdatMin){
            this.rangEdatMin = this.rangEdatMax;
            this.rangEdatMax = this.rangEdatMin;
        }
    }

    public int getRangEdatMax() {
        return rangEdatMax;
    }

    public void setRangEdatMax(int rangEdatMax) {
        if(rangEdatMax > 99) this.rangEdatMax = 99;
        else this.rangEdatMax = rangEdatMax;
        
        if(this.rangEdatMin > this.rangEdatMax){
            this.rangEdatMax = this.rangEdatMin;
            this.rangEdatMin = this.rangEdatMax;
        }
    }
    
    
    

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public boolean isMascotes() {
        return mascotes;
    }

    public void setMascotes(boolean mascotes) {
        this.mascotes = mascotes;
    }

    

    
    
   
    
    
    
    
}
