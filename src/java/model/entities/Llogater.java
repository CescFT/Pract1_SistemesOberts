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
    @NamedQuery(name="tenant.findAll", query="SELECT r FROM Llogater r"),
    @NamedQuery(name="tenant.information", query="SELECT r FROM Llogater r WHERE r.id = :id"),
    //@NamedQuery(name="tenant.updateInfo", query="SELECT r FROM Llogater r WHERE r.id = :id"),
    //@NamedQuery(name="tenant.deleteInfo", query="SELECT r FROM Llogater r WHERE r.id = :id")
})
public class Llogater implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Llogater_Gen")
    private Long id;
    
    @Embedded
    private informacioLlogater info;
    
    
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
        return "Llogater{" + "id=" + id + ", info=" + info + '}';
    }

     
}
