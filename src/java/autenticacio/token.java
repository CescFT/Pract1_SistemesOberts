/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacio;
import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Cesc
 */
@Embeddable
@XmlRootElement
public class token implements Serializable{
    @Column(name="TOKEN") @Size(max=500)
    private String tokenAutoritzacio;

    public token(){
        
    }
    public token(String token) {
        this.tokenAutoritzacio = token;
    }

    public String getTokenAutoritzacio() {
        return tokenAutoritzacio;
    }

    public void setTokenAutoritzacio(String tokenAutoritzacio) {
        this.tokenAutoritzacio = tokenAutoritzacio;
    }

    @Override
    public String toString() {
        return "token{" + "tokenAutoritzacio=" + tokenAutoritzacio + '}';
    }
    
    public boolean compararTokens(token tk){
        return this.tokenAutoritzacio.equals(tk.getTokenAutoritzacio());
    }
    
}
