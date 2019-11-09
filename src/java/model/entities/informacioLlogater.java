/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
/**
 *
 * @author Cesc
 */
@Embeddable
public class informacioLlogater implements Serializable{
    
    private String nom;
       
    private String cognom;
    
    @Enumerated(EnumType.STRING)
    private SexeLlogater sexe;

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
    
    
    
}
