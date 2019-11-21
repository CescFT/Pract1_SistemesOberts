/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.entities.Habitacio;


/**
 *
 * @author Administrador
 */
@Stateless
@Path("/room")
public class HabitacioFacadeREST extends AbstractFacade<Habitacio> {
    @PersistenceContext(unitName = "Homework1PU") //Aixo fa que sigui un container, i no haver de fer us del commit i transaction i tot aixo
                                                 //Lo de lab10_wspu esta dins del fitxer persistence.xml a <persistence-unit>
    private EntityManager em;

    public HabitacioFacadeREST() {
        super(Habitacio.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createHabitacio(Habitacio entity) {
        if(entity == null)
            return Response.status(Response.Status.PRECONDITION_FAILED).entity("No ve un JSON informat").build();
        else{
            super.create(entity);
            System.out.println(entity.toString());
            return Response.ok().entity("Nova entrada"+entity.toString()+"\nAfegida correctament.").build();
        }
       
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editHabitacio(Habitacio entity) {
        if(entity == null)
            return Response.status(Response.Status.PRECONDITION_FAILED).entity("No ve un JSON informat").build();
        else{
            super.edit(entity);
            System.out.println(entity);
            return Response.ok().entity(entity+"\nha estat modificada correctament.").build();
        }
        
    }

    @DELETE
    @Path("{id}")
    //posar com a metode DELETE i la url: http://localhost:8080/Pract1_SistemesOberts/webresources/room/id
    public Response remove(@PathParam("id") Integer id) {
        
        Habitacio hab = super.find(Long.valueOf(id));
        if(hab!= null){
            super.remove(hab);
            return Response.ok().entity("Habitacio \n"+hab+"\n esborrada correctament.").build();
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity("Id no disponible").build();
    }
    
    
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    //http://localhost:8080/Pract1_SistemesOberts/webresources/room/id
    public Response find(@PathParam("id") Integer id) {
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        Habitacio hab = super.find(Long.valueOf(id));
        if(hab != null){
            return Response.ok(hab, MediaType.APPLICATION_JSON).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("ID:"+id+" no disponible.").build();
        }
        
    }
    
    @GET
    @Produces({"application/json"})
    //http://localhost:8080/Pract1_SistemesOberts/webresources/room?location=Valls&sort=asc
    //http://localhost:8080/Pract1_SistemesOberts/webresources/room?location&sort=asc   
    public Response find(@QueryParam("location") String location,@QueryParam("sort") String criterion) {
        List<Habitacio> llistaHabitacions = new ArrayList<Habitacio>();
        if(criterion==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Falta criteri.").build();
        }
        
        try{
            if (location!=null && criterion!=null){
            //retornar les habitacions que siguin duna ciutat i el criteri de ordenacio
                llistaHabitacions = super.findRoomsWithCityAndCriteria(location, criterion);
            
            }else if (criterion!=null){
            //retornar totes les habitacions
                llistaHabitacions = super.findRoomsWithCriteria(criterion);
                
            }
            GenericEntity<List<Habitacio>> llista = new GenericEntity<List<Habitacio>>(llistaHabitacions){};
            return Response.ok().entity(llista).build();
        }catch(NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).entity("null "+criterion+" "+location).build();
        }
        
    }
    
    
    private List<Habitacio> totesHabitacions(){
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
