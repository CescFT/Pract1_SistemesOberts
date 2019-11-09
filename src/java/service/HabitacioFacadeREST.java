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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import model.entities.Habitacio;


/**
 *
 * @author Administrador
 */
@Stateless
@Path("/room")
public class HabitacioFacadeREST extends AbstractFacade<Habitacio> {
    @PersistenceContext(unitName = "lab10_wspu") //Aixo fa que sigui un container, i no haver de fer us del commit i transaction i tot aixo
                                                 //Lo de lab10_wspu esta dins del fitxer persistence.xml a <persistence-unit>
    private EntityManager em;

    public HabitacioFacadeREST() {
        super(Habitacio.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Habitacio entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(Habitacio entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        
        Habitacio hab = super.find(id);
        if(hab!= null){
            super.remove(super.find(id));
            return Response.ok().entity("Habitacio "+id+" esborrada.").build();
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity("Id no disponible").build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response find(@PathParam("id") Integer id) {
        
        Habitacio hab = super.find(id);
        if(hab != null){
            return Response.status(200).entity(hab).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Id no disponible").build();
    }
    
    @GET
    @Path("{location}/{sort}")
    @Produces({"application/json"})
    public Response find(@PathParam("location") String city, @PathParam("sort") String criterion) {
        List<Habitacio> llistaHabitacions = new ArrayList<Habitacio>();
        if(criterion.equals("")){
            return Response.status(Response.Status.BAD_REQUEST).entity("Falta criteri.").build();
        }
        if (!city.equals("")){
            //retornar totes les habitacions
             llistaHabitacions = super.findRoomsWithCriteria(criterion);
        }else{
            //retornar les habitacions que siguin duna ciutat i el criteri de ordenacio
            llistaHabitacions = super.findRoomsWithCityAndCriteria(city, criterion);
        }
        GenericEntity<List<Habitacio>> llista = new GenericEntity<List<Habitacio>>(llistaHabitacions){};
        return Response.ok().entity(llista).build();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
