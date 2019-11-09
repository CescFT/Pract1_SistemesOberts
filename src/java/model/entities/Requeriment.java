/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Cesc
 */
@Embeddable
public class Requeriment implements Serializable{
    
    
    @Enumerated(EnumType.STRING)
    private SexeLlogater sexe;
    
    private Collection<Integer> rangEdat;
    
    private boolean fumador;
    
    private boolean mascotes;
    
    public Requeriment(){
        rangEdat = new ArrayList<Integer>();
    }
    
    public void setRang(int min, int max){
        if(max < min){
            min = max;
            max = min;
            if(min>99) min = 99;
            if(max<0) max = 0;
        }else{
            if(min<0) min = 0;
            if(max>99) max = 99;
        } 
        rangEdat.add(min);
        rangEdat.add(max);
    }
    
    public Collection<Integer> getRang(){
        return rangEdat;
    }

    
    public SexeLlogater getSexe() {
        return sexe;
    }

    public void setSexe(SexeLlogater sexe) {
        this.sexe = sexe;
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
        return "Requeriment{" + "sexe=" + sexe + ", rangEdat=" + rangEdat + ", fumador=" + fumador + ", mascotes=" + mascotes + '}';
    }

    
    
   
    
    
    
    
}
