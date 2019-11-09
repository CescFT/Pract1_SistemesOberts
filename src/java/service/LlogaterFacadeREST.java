/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import model.entities.Llogater;

/**
 *
 * @author Cesc
 */
@Stateless
@Path("/tenant")
public class LlogaterFacadeREST extends AbstractFacade<Llogater>{
    @PersistenceContext(unitName = "Homework1PU") //Aixo fa que sigui un container, i no haver de fer us del commit i transaction i tot aixo
                                                 //Lo de lab10_wspu esta dins del fitxer persistence.xml a <persistence-unit>
    private EntityManager em;

    public LlogaterFacadeREST() {
        super(Llogater.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Llogater entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(Llogater entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        
        Llogater tenant = super.find(id);
        if (tenant != null){
            super.remove(super.find(id));
            return Response.ok().entity("Llogater "+id+" eliminat").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(id+" no disponible").build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response find(@PathParam("id") Integer id) {
        
        Llogater tenant = super.find(id);
        if (tenant != null){
            return Response.ok().entity(tenant).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Id no correcte").build();
    }
    
    @GET
    @Produces({"application/json"})
    public Response listOfTenants(){
        List<Llogater> llogaters = super.findAllTenants();
        if (llogaters.size() == 0)
            return Response.status(Response.Status.NO_CONTENT).build();
        GenericEntity<List<Llogater>> llista = new GenericEntity<List<Llogater>>(llogaters){};
        return Response.ok().entity(llista).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
