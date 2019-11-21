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
    
    private boolean home;
    
    private boolean dona;
    

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
        return "informacioLlogater{" + "nom=" + nom + ", cognom=" + cognom + ", home=" + home + ", dona=" + dona + '}';
    }

    
    
    
    
}
