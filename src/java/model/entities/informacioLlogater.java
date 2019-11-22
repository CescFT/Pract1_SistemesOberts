/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.Column;

import javax.validation.constraints.Size;
/**
 *
 * @author Cesc
 */
@Embeddable
public class informacioLlogater implements Serializable{
    @Column(name="NOM_LLOGATER") @Size(max=500)
    private String nom;
    @Column(name="COGNOM_LLOGATER") @Size(max=500)
    private String cognom;
    
    @Column(name="EDAT_LLOGATER")
    private int edat;
    
    private boolean fumador;
    
    private boolean teMascotes;
    
    private boolean home;
    
    private boolean dona;

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public boolean isTeMascotes() {
        return teMascotes;
    }

    public void setTeMascotes(boolean teMascotes) {
        this.teMascotes = teMascotes;
    }
    

    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
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

    @Override
    public String toString() {
        return "informacioLlogater{" + "nom=" + nom + ", cognom=" + cognom + ", edat=" + edat + ", fumador=" + fumador + ", teMascotes=" + teMascotes + ", home=" + home + ", dona=" + dona + '}';
    }

    

    
    
    
    
}
