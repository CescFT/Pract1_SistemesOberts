/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import autenticacio.credentialsClient;
import autenticacio.token;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.*;
import model.entities.Habitacio;
import model.entities.Llogater;
import model.entities.Requeriment;
import model.entities.informacioLlogater;

/**
 *
 * @author Cesc
 */
@Stateless
@Path("tenant")
public class LlogaterFacadeREST extends AbstractFacade<Llogater>{
    @PersistenceContext(unitName = "Homework1PU") //Aixo fa que sigui un container, i no haver de fer us del commit i transaction i tot aixo
                                                 //Lo de lab10_wspu esta dins del fitxer persistence.xml a <persistence-unit>
    private EntityManager em;
    private token token;
    private credentialsClient client;

    public LlogaterFacadeREST() {
        super(Llogater.class);
        
    }
    
    public void setClient(credentialsClient client){
        this.client = client;
    }
    
    public credentialsClient getClient(){
        return this.client;
    }
    
    private token getToken(){
        return this.token;
    }
    
    private void setToken(token token){
        this.token = token;
    }
    
    @POST
    @Path("/processarToken")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response processamentProva(token json){
        
        System.out.println("::dada entrada"+json);
        try{
                if(super.tokenVerificat(json)){
                this.setToken(json);
                this.setClient(super.whoDoneThisPetition(this.getToken()));
                return Response.ok().entity("Token emmagatzemat correctament:\n\n"+this.getToken()+"\nUsuari:"+this.getClient().getUsername()).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("El token no es correcte").build();
            }
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Hi ha hagut algun error al processar el token.").build();
        }
        
        
    }

    /*
    @POST
    @Path("{id}/rent")
    @Consumes({"application/json", MediaType.APPLICATION_FORM_URLENCODED})
    public Response rentingRoom(Habitacio hab, @PathParam("id") Integer id, @FormParam("token") String token){
        token tk = new token();
        tk.setTokenAutoritzacio(token);
       if(super.tokenVerificat(tk)){
           if(hab ==null)
            return Response.status(Response.Status.NOT_FOUND).entity("Per a fer anar aquest mètode has de passar una habitacio en JSON!").build();
            if(id == null)
                return Response.status(Response.Status.BAD_REQUEST).entity("ID nul, no es pot fer renting").build();
            Llogater llogater = super.find(Long.valueOf(id));
            if(llogater == null)
                return Response.status(Response.Status.NOT_FOUND).entity("El llogater amb id: "+id+" no es troba a la base de dades.").build();
            else{
                  if(comprovarRequeriments(hab, llogater)){
                      hab.setLlogater(llogater);
                      getEntityManager().merge(hab);
                      return Response.status(Response.Status.CREATED).entity(hab+"\n\nHa estat llogada per: "+llogater+"\n\nOperació finalitada.").build();
                  }else
                      return Response.status(Response.Status.CONFLICT).entity("No compleix els requisits.").build();
            }
       }else{
           return Response.status(Response.Status.BAD_REQUEST).entity("Token invàlid o no t'has autenticat").build();
       }        
    }*/
    
    
    /*
    
    -----------PROVA SENSE TOKEN-----------
    
    */
    @POST
    @Path("{id}/rent")
    @Consumes({"application/json"})
    public Response rentingRoom(Habitacio hab, @PathParam("id") Integer id){
        
        if(token != null){
                if(hab ==null)
                return Response.status(Response.Status.NOT_FOUND).entity("Per a fer anar aquest mètode has de passar una habitacio en JSON!").build();
            if(id == null)
                return Response.status(Response.Status.BAD_REQUEST).entity("ID nul, no es pot fer renting").build();
            Llogater llogater = super.find(Long.valueOf(id));
            if(llogater == null)
                return Response.status(Response.Status.NOT_FOUND).entity("El llogater amb id: "+id+" no es troba a la base de dades.").build();
            else{
                  if(comprovarRequeriments(hab, llogater)){
                      Habitacio hab1 = super.findWithId(hab.getIdHabitacio());
                      hab1.setLlogater(llogater);

                      getEntityManager().merge(hab1);
                      return Response.status(Response.Status.CREATED).entity(hab1+"\n\nHa estat llogada per: "+llogater+"\n\nOperació finalitada.").build();
                  }else
                      return Response.status(Response.Status.CONFLICT).entity("No compleix els requisits.").build();
            }

        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();

    }
    
    
    private boolean comprovarRequeriments(Habitacio h, Llogater ll){
        informacioLlogater infoLlogater = ll.getInfo();
        Requeriment reqHab = h.getRequeriment();
        
        boolean compleix = false;
        if(infoLlogater.isTeMascotes() == reqHab.isMascotes()){
            if(infoLlogater.isFumador() == reqHab.isFumador()){
                if(infoLlogater.getEdat() >= reqHab.getRangEdatMin() && infoLlogater.getEdat()<=reqHab.getRangEdatMax())
                    compleix=true;
            }
        }
        
        if(!compleix)
            return false;
        else{
            if(reqHab.isUnisex())
                return true;
            else if(infoLlogater.isDona() == reqHab.isDona())
                return true;
            else if (infoLlogater.isHome() == reqHab.isHome())
                return true;
        }
        return false;
    }
    
    /*
    @POST
    @Consumes({"application/json", MediaType.APPLICATION_FORM_URLENCODED})
    public Response createLlogater(Llogater entity, @FormParam("token") String token) {       //OK
        token tk = new token();
        tk.setTokenAutoritzacio(token);
        if(super.tokenVerificat(tk)){
                if(entity == null)
                return Response.status(Response.Status.BAD_REQUEST).entity("No es pot generar perquè no hi ha un JSON vàlid o informat").build();
            else{
                super.create(entity);
                return Response.status(Response.Status.CREATED).entity("Llogater:\n "+entity+"\ncreat amb èxit.").build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("token invàlid o no has afegit un token").build();
        }
        
        
    }*/
    
    /*
    
    ----------PROVA SENSE TOKEN--------
    */
    @POST
    @Consumes({"application/json"})
    public Response createLlogater(Llogater entity) {       //OK
        
        if(token != null){
                if(entity == null)
                return Response.status(Response.Status.BAD_REQUEST).entity("No es pot generar perquè no hi ha un JSON vàlid o informat").build();
            else{
                super.create(entity);
                return Response.status(Response.Status.CREATED).entity("Llogater:\n "+entity+"\ncreat amb èxit.").build();
            }
        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();
        
    }
    

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response editLlogater(Llogater entity) {     //OK
        
       if(token != null){
           if(entity == null)
                return Response.status(Response.Status.BAD_REQUEST).entity("No hi ha JSON informat o és invàlid").build();
            else{
                super.edit(entity);
                return Response.status(Response.Status.GONE).entity("Llogater "+entity+"\n\n modificat correctament.").build();
            }
       }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();

    }
    
    @DELETE
    @Path("alliberarHabitacio/{id}")
    public Response alliberarHabitacio(@PathParam("id") Integer id){
        
        if(token != null){
            Llogater tenant = super.find(Long.valueOf(id));
            if (tenant != null){
                if(super.isTenant(super.findAllRooms(), tenant)){
                    Habitacio hab = super.returnHabitacioClient(tenant);
                    hab.setLlogater(null);
                    getEntityManager().merge(hab);
                    super.remove(tenant);
                    return Response.status(Response.Status.FOUND).entity("Llogater "+tenant+"\n\n eliminat correctament.").build();
                }
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(id+" no disponible").build();
        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();
        
   

    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {       //OK
        
        if(token != null){
            Llogater tenant = super.find(Long.valueOf(id));
            if (tenant != null){
                if(super.isTenant(super.findAllRooms(), tenant))
                    return Response.status(Response.Status.FOUND).entity("No es pot esborrar perque té una habitacio.").build();
                else{
                    super.remove(tenant);
                    return Response.ok().entity("Llogater "+tenant+"\n eliminat").build();
                }

            }
            return Response.status(Response.Status.BAD_REQUEST).entity(id+" no disponible").build();
        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();

    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response find(@PathParam("id") Integer id) { //OK
        if(token != null){
            Llogater tenant = super.find(Long.valueOf(id));
            if (tenant != null){
                return Response.ok().entity(tenant).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("Id no correcte").build();
        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response listOfTenants(){    //OK
        
        if(token != null){
                try{
            List<Llogater> llogaters = super.findAllTenants();

            if (llogaters.isEmpty())
                return Response.status(Response.Status.NO_CONTENT).entity("No hi ha llogaters.").build();
            else{
                GenericEntity<List<Llogater>> llista = new GenericEntity<List<Llogater>>(llogaters){};
                return Response.ok().entity(llista).build();
            }
            }catch(NullPointerException e){
                return Response.status(Response.Status.NOT_FOUND).entity("ouuuuh fuck").build();
            }
        }else
            return Response.status(Response.Status.BAD_REQUEST).entity("No t'has autenticat :(").build();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    
    
}
