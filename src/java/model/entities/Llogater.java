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
/**
 *
 * @author Cesc
 */
@Entity
@Path("/tenant")
@NamedQueries({
    @NamedQuery(name="tenant.findAll", query="SELECT r FROM Llogater r"),
    @NamedQuery(name="tenant.information", query="SELECT r FROM Llogater r WHERE r.id = :id AND r.autenticat=TRUE"),
    @NamedQuery(name="tenant.updateInfo", query="SELECT r FROM Habitacio r WHERE r.id = :id"),
    @NamedQuery(name="tenant.deleteInfo", query="SELECT r FROM Habitacio r WHERE r.id = :id")
})
public class Llogater implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Llogater_Gen")
    private int id;
    
    @Embedded
    private informacioLlogater info;
    
    private boolean autenticat;
    
    @OneToMany(mappedBy="llogater")
    private Collection<Habitacio> habitacions;

    public Llogater() {
        habitacions = new ArrayList<Habitacio>();
    }

    public boolean isAutenticat() {
        return autenticat;
    }

    public void setAutenticat(boolean autenticat) {
        this.autenticat = autenticat;
    }

    
    public void addHabitacio(Habitacio h){
        if (h!=null && !habitacions.contains(h))
            habitacions.add(h);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String toString() {
        return "Llogater{" + "id=" + id + ", info=" + info + ", autenticat=" + autenticat + ", habitacions=" + habitacions + '}';
    }

    
}
