/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import autenticacio.credentialsClient;
import autenticacio.token;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.entities.Habitacio;
import model.entities.Llogater;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import java.util.*;
/**
 *
 * @author Administrador
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        try{
            getEntityManager().persist(entity);
        }catch(ConstraintViolationException e){
            for (ConstraintViolation actual : e.getConstraintViolations()) {
             System.out.println(actual.toString());
            }
        }
        
    }
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public Habitacio findWithId(Long id){
        
        TypedQuery<Habitacio> query =(TypedQuery<Habitacio>) getEntityManager().createNamedQuery("room.information").setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Habitacio> findAllRooms(){
        TypedQuery<Habitacio> query = (TypedQuery<Habitacio>)getEntityManager().createNamedQuery("room.allRooms");
        return query.getResultList();
    }
    
    public Habitacio returnHabitacioClient(Llogater ll){
        try{
            Habitacio hab = new Habitacio();
            List<Habitacio> llistaHabitacions = findAllRooms();
            for(Habitacio h : llistaHabitacions){
                if(h.getLlogater().equals(ll)){
                    hab = h;
                    break;
                }
            }
            return hab;
        }catch(NullPointerException e){
            return null;
        }
    }
    
    public boolean isTenant(List<Habitacio> llistaHabitacions, Llogater ll){
        try{
            if(llistaHabitacions.isEmpty())
            return false;
        
        for(Habitacio h : llistaHabitacions){
            if(h.getLlogater().getId().equals(ll.getId()))
                return true;
        }
       
        return false;
        }catch(NullPointerException e){
            return false;
        }
        
    }
    
    public List<Habitacio> findRoomsWithCriteria(String criteria){
        TypedQuery<Habitacio> query;
        if(criteria.toUpperCase().equals("ASC")){
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.allRoomsASC");
        }else{
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.allRoomsDESC");
        }
        return query.getResultList();
    }
    
    public List<Habitacio> findRoomsWithCityAndCriteria(String location, String criteria){
        TypedQuery <Habitacio> query;
        if(criteria.toUpperCase().equals("ASC")){
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalASC")
                .setParameter("location", location);
        }else{
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalDESC")
                .setParameter("location", location);
        }

        return query.getResultList();
    }
    
    public List<Llogater> findAllTenants(){
        TypedQuery<Llogater> query = (TypedQuery<Llogater>) getEntityManager()
                .createNamedQuery("tenant.findAll");
        return query.getResultList();
    }
    
    public List<credentialsClient> findAllClientsAutoritzats(){
        TypedQuery<credentialsClient> query = (TypedQuery<credentialsClient>)
                getEntityManager().createNamedQuery("credentialsClient.findAll");
        return query.getResultList();
    }
    
    public credentialsClient findClientAutoritizat(String username){
        TypedQuery<credentialsClient> query = (TypedQuery<credentialsClient>)
                getEntityManager().createNamedQuery("credentialsClient.matchUsername").setParameter("username", username);
        return query.getSingleResult();
    }
    
    
    public credentialsClient whoDoneThisPetition(token token){
        credentialsClient c = new credentialsClient();
        List<credentialsClient> llistaClients = findAllClientsAutoritzats();
        for(credentialsClient cli : llistaClients){
            if(cli.getTokenAutoritzacio().compararTokens(token)){
                c = cli;
                break;
            }
        }
        return c;
    }
    
    public boolean tokenVerificat(token token){
        System.out.println("::token a verificar:"+token);
        if(token == null)
            return false;
        try{
            List<credentialsClient> llistatClientsAutenticats = findAllClientsAutoritzats();
            if(llistatClientsAutenticats.isEmpty())
                return false;
               
            boolean trobat = false;
            for(credentialsClient cli : llistatClientsAutenticats){
                System.out.println("::client: "+cli);
                
                if(cli.getTokenAutoritzacio()!=null){
                    
                    if(cli.getTokenAutoritzacio().compararTokens(token))
                    {
                        System.out.println(":: token: "+cli.getTokenAutoritzacio());
                        trobat = true;
                        break;
                    }
                }
                
            }
            
            if(trobat){
                if(!token.getTokenAutoritzacio().contains("-"))
                    return false;
   
            }else
                return false;
            
        }catch(NullPointerException e){
            return false;
        }
        return true;
    }
    
}
