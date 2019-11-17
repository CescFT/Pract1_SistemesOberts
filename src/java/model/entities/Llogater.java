/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Cesc
 */
@Entity
@XmlRootElement
@Path("/tenant")
@NamedQueries({
    @NamedQuery(name="tenant.findAll", query="SELECT r FROM Llogater r"),
    @NamedQuery(name="tenant.information", query="SELECT r FROM Llogater r WHERE r.id = :id AND r.autenticat=TRUE"),
    @NamedQuery(name="tenant.updateInfo", query="SELECT r FROM Llogater r WHERE r.id = :id"),
    @NamedQuery(name="tenant.deleteInfo", query="SELECT r FROM Llogater r WHERE r.id = :id")
})
public class Llogater implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Llogater_Gen")
    private Long id;
    
    @Embedded
    private informacioLlogater info;
    
    
    @OneToMany(mappedBy="llogater")
    private Collection<Habitacio> habitacions;

    public Llogater() {
        habitacions = new ArrayList<Habitacio>();
    }

    
    
    public void addHabitacio(Habitacio h){
        if (h!=null && !habitacions.contains(h))
            habitacions.add(h);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public informacioLlogater getInfo() {
        return info;
    }

    public void setInfo(informacioLlogater info) {
        this.info = info;
    }

    public Collection<Habitacio> getHabitacions() {
        return habitacions;
    }

    public void setHabitacions(Collection<Habitacio> habitacions) {
        this.habitacions = habitacions;
    }

    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Llogater)) {
            return false;
        }
        Llogater other = (Llogater) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id !=null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Llogater{" + "id=" + id + ", info=" + info + ", habitacions=" + habitacions + '}';
    }
    
    
    
    
}
