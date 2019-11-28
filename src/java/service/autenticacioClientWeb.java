/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import autenticacio.credentialsClient;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import autenticacio.token;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author CescFT
 * @author AleixSP
 * 
 * Per tal de autenticar, quan executem el install.jsp, ens hem de quedar amb la password i el username que ens dona,
 * aixo es clau per a que ens retorni el token (únic per a cada sessió i persistent). Recordem que la contrassenya es xifra i es guarda a la bd, si la volem recuperar
 * hi ha un metode que ens la recupera, però es imprescindible el token.
 */
@Stateless
@Path("/autenticacio")
public class autenticacioClientWeb extends AbstractFacade<credentialsClient>{
    
    @PersistenceContext(unitName="Homework1PU")
    private EntityManager em;
    
    public autenticacioClientWeb(){
        super(credentialsClient.class);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticationClient(@FormParam("username") String username,
            @FormParam("password") String passwd){
        System.out.println(username);
        System.out.println(passwd);
        if(username == null || passwd == null)
            return Response.status(Response.Status.FORBIDDEN).entity("Falta usuari o contrassenya").build();
        try{
            //Autenticació de l'usuari fent servir les credencials donades
            if(authenticateClient(username, passwd)){
                String token = getToken(username);
                credentialsClient c = super.findClientAutoritizat(username);
                if(c.getTokenAutoritzacio() == null){
                    c.setTokenAutoritzacio(new token(token));
                    super.edit(c);
                    return Response.ok("YOUR TOKEN FOR DO IMPORTANT THINGS IS:\n\n"+c.getTokenAutoritzacio()).build();
                }else{
                    return Response.ok().entity("Aquest usuari ja té un token. Es aquest:\n\n"+c.getTokenAutoritzacio()).build();
                }
            }else
                return Response.status(Response.Status.NOT_FOUND).entity("No ets un client autoritzat.").build();
        }catch(Exception e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
               
    }
    
    @GET
    @Path("{username}")
    @Produces({"application/json"})
    public Response getTokenOfUsername(@PathParam("username") String username){
        credentialsClient c = super.findClientAutoritizat(username);
        if(c == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("No es troba aquest usuari web").build();
        else
            return Response.ok().entity(c).build();
    }
    
    @GET
    @Path("{username}/{token}")
    @Produces({"application/text"})
    public Response getMevaContrassenya(@PathParam("username") String username, @PathParam("token") String token){
        
        credentialsClient c = super.findClientAutoritizat(username);
        if(token == null)
            return Response.status(Response.Status.NO_CONTENT).entity("No hi ha token").build();
        else{
            String substring ="-";
            if(!token.contains(substring))
                return Response.status(Response.Status.BAD_REQUEST).entity("Token invàlid. No té una forma autoritzada.").build();
            else{
                if(username == null)
                   return Response.status(Response.Status.NOT_FOUND).entity("Username no informat.").build();
                if(c == null)
                    return Response.status(Response.Status.NOT_FOUND).entity("No hi ha un usuari amb aquest username: "+username).build();
                
                token tk = new token();
                tk.setTokenAutoritzacio(token);
                if(!c.getTokenAutoritzacio().compararTokens(tk))
                    return Response.status(Response.Status.BAD_REQUEST).entity("El username "+username+" no té aquest token.").build();
            }
        }
         return Response.ok().entity("La password per a "+c.getUsername()+" es: "+c.getPassword()).build();    
    }
    
    @GET
    @Produces({"application/json"})
    public Response getClientAutenticate(@QueryParam("username") String username){
        if(username == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        
        credentialsClient c = super.findClientAutoritizat(username);
        if(c != null)
            return Response.ok().entity("La informació del client autoritzat:\n"+c+"\n").build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("No s'ha trobat cap client amb aquest username").build();
    }
    
    @GET
    @Path("/all")
    @Produces({"application/json"})
    public Response getAllClientsAutoritzats(){
        List<credentialsClient> llista = new ArrayList<credentialsClient>();
            
        try{
            llista = super.findAllClientsAutoritzats();
            if(llista.isEmpty())
                return Response.status(Response.Status.NOT_FOUND).entity("No hi ha clients autoritzats").build();
            else{
                GenericEntity<List<credentialsClient>> llistacli = new GenericEntity<List<credentialsClient>>(llista){};
                return Response.ok().entity(llistacli).build();
            }
        }catch(NullPointerException e){
                  return Response.status(Response.Status.NOT_FOUND).entity("No hi ha clients autoritzats").build();      
        }
        
    }
    
    
    private boolean authenticateClient(String username, String passwd) throws Exception{
        credentialsClient c = super.findClientAutoritizat(username); 
        System.out.println(c);
        if(c==null)
            return false;
        else{
            if(c.getPassword().equals(passwd))
                return true;
            else
                return false;
        }
    }
    
    private String getToken(String username){
        String alphaNumericString="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int cmpt = 0;
        StringBuilder stringb = new StringBuilder();
        while(cmpt<32){
            int posRand=(int)(Math.random()*alphaNumericString.length());
            stringb.append(alphaNumericString.charAt(posRand));
            cmpt++;
        }
        
        return username+"-"+stringb.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
