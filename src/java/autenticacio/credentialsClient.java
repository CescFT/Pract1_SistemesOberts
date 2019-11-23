/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacio;
import java.io.Serializable;

import java.util.Base64;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Cesc
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="credentialsClient.findAll", query="SELECT c FROM credentialsClient c"),
    @NamedQuery(name="credentialsClient.matchUsername", query="SELECT c FROM credentialsClient c WHERE c.username= :username")
})

public class credentialsClient implements Serializable{
    
    @Column(name="USERNAME") @Size(max = 500)
    private String username;
    
    @Id @Column(name="PASSWORD") @Size(max = 500)
    private String password;
    
    @Column(name="E_MAIL") @Size(max = 500)
    private String email;
    
    private String tokenAutoritzacio;

    public credentialsClient() {
    }

    public credentialsClient(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getTokenAutoritzacio() {
        return tokenAutoritzacio;
    }

    public void setTokenAutoritzacio(String tokenAutoritzacio) {
       this.tokenAutoritzacio = tokenAutoritzacio;
        
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCryptedPassword(){
        return password;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
       byte[] decodedBytes = Base64.getDecoder().decode(password);
       return new String(decodedBytes);
    }

    public void setPassword(String password) {
        this.password= Base64.getEncoder().encodeToString(password.getBytes());
    }

    @Override
    public String toString() {
        return "credentialsClient{" + "username=" + username + ", password=" + password + ", email=" + email + '}';
    }

    
    
    
}
