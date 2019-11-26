/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    
    @Enumerated(EnumType.STRING)
    private SexeLlogater sexe;

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

    public SexeLlogater getSexe() {
        return sexe;
    }

    public void setSexe(SexeLlogater sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "informacioLlogater{" + "nom=" + nom + ", cognom=" + cognom + ", edat=" + edat + ", fumador=" + fumador + ", teMascotes=" + teMascotes + ", sexe=" + sexe + '}';
    }

       
}
