/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.*;


import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Cesc
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="room.findAllCondicionalASC", query="SELECT r FROM Habitacio r WHERE r.ciutat =:location ORDER BY r.preuMes ASC"), //criterion ha de ser ASC o DESC
    @NamedQuery(name="room.findAllCondicionalDESC", query="SELECT r FROM Habitacio r WHERE r.ciutat =:location ORDER BY r.preuMes DESC"), //criterion ha de ser ASC o DESC
    //@NamedQuery(name="room.findAll", query="SELECT r FROM Habitacio r ORDER BY r.preuMes :criterion"),
    @NamedQuery(name="room.information", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id"),
    @NamedQuery(name="room.updateInfo", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id"),
    @NamedQuery(name="room.deleteInfo", query="SELECT r FROM Habitacio r WHERE r.idHabitacio = :id"),
    @NamedQuery(name="room.allRooms", query="SELECT r FROM Habitacio r"),
    @NamedQuery(name="room.allRoomsASC", query="SELECT r FROM Habitacio r ORDER BY r.preuMes ASC"),
    @NamedQuery(name="room.allRoomsDESC", query="SELECT r FROM Habitacio r ORDER BY r.preuMes DESC")
})
public class Habitacio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @NotNull @Column(name="HABITACIO_ID") @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Habitacio_Gen")
    private Long idHabitacio;
    @Size(max=500) @Column(name="DESCRIPCIO")
    private String descripcio; 
    @Column(name="ADREÇA") @Size(max=500)
    private String adresa;
    @Column(name="CIUTAT") @Size(max=500)
    private String ciutat;
    
    @Enumerated(EnumType.STRING)
    @Column(name="TIPUS")
    private TipusHabitacio tipusHabitacio;
    
    @Column(name="PREU_MES")
    private float preuMes;
    
    @Embedded
    private Requeriment requeriment; 

    @OneToOne
    private Llogater llogater;

    public TipusHabitacio getTipusHabitacio() {
        return tipusHabitacio;
    }

    public void setTipusHabitacio(TipusHabitacio tipusHabitacio) {
        this.tipusHabitacio = tipusHabitacio;
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
        return "Habitacio{" + "idHabitacio=" + idHabitacio + ", descripcio=" + descripcio + ", adresa=" + adresa + ", ciutat=" + ciutat + ", tipusHabitacio=" + tipusHabitacio + ", preuMes=" + preuMes + ", requeriment=" + requeriment + ", llogater=" + llogater + '}';
    }

   

    

    
    
    
    
    
    
}
