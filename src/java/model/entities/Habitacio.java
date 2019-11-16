/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.*;
import java.io.Serializable;


import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Cesc
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="room.findAllCondicionalASC", query="SELECT r FROM Habitacio r WHERE r.ciutat =:city ORDER BY r.preuMes ASC"), //criterion ha de ser ASC o DESC
    @NamedQuery(name="room.findAllCondicionalDESC", query="SELECT r FROM Habitacio r WHERE r.ciutat =:city ORDER BY r.preuMes DESC"), //criterion ha de ser ASC o DESC
    //@NamedQuery(name="room.findAll", query="SELECT r FROM Habitacio r ORDER BY r.preuMes :criterion"),
    @NamedQuery(name="room.information", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id"),
    @NamedQuery(name="room.updateInfo", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id"),
    @NamedQuery(name="room.deleteInfo", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id")
})
public class Habitacio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Habitacio_Gen")
    private Long idHabitacio;
    private String descripcio;
    private String adresa;
    private String ciutat;
    
    @Enumerated(EnumType.STRING)
    private TipusHabitacio tipus;
    private float preuMes;
    
    @Embedded
    private Requeriment requeriment; 

    @ManyToOne
    private Llogater llogater;
    
    
    
    public void setTipus(TipusHabitacio tipus) {
        this.tipus = tipus;
    }

    public Llogater getLlogater() {
        return llogater;
    }

    public void setLlogater(Llogater llogater) {
        this.llogater = llogater;
    }
    
    
    
    public Habitacio() {
        
    }

    public Long getIdHabitacio() {
        return idHabitacio;
    }

    public void setIdHabitacio(Long idHabitacio) {
        this.idHabitacio = idHabitacio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public float getPreuMes() {
        return preuMes;
    }

    public void setPreuMes(float preuMes) {
        this.preuMes = preuMes;
    }

    public Requeriment getRequeriment() {
        return requeriment;
    }

    public void setRequeriment(Requeriment requeriment) {
        this.requeriment = requeriment;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Habitacio)) {
            return false;
        }
        Habitacio other = (Habitacio) object;
        if ((this.idHabitacio == null && other.idHabitacio != null) || (this.idHabitacio != null && !this.idHabitacio.equals(other.idHabitacio))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabitacio !=null ? idHabitacio.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Habitacio{" + "idHabitacio=" + idHabitacio +
                ", descripcio=" + descripcio + ", adresa="
                + adresa + ", ciutat=" + ciutat + ","
                + " tipus=" + tipus + ", preuMes="
                + preuMes + ", requeriment=" + requeriment + '}';
    }
    
    
    
    
    
}
