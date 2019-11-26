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
    
    
   @Enumerated(EnumType.STRING)
   private SexeLlogater sexe;
    
    private int rangEdatMin;
    private int rangEdatMax;
    
    private Boolean fumador;
    
    private Boolean mascotes;
    
    public Requeriment(){
        
    }

    public SexeLlogater getSexe() {
        return sexe;
    }

    public void setSexe(SexeLlogater sexe) {
        this.sexe = sexe;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public Boolean getMascotes() {
        return mascotes;
    }

    public void setMascotes(Boolean mascotes) {
        this.mascotes = mascotes;
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

    @Override
    public String toString() {
        return "Requeriment{" + "sexe=" + sexe + ", rangEdatMin=" + rangEdatMin + ", rangEdatMax=" + rangEdatMax + ", fumador=" + fumador + ", mascotes=" + mascotes + '}';
    }

       
    
}
