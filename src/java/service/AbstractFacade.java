/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.entities.Habitacio;
import model.entities.Llogater;

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
        getEntityManager().persist(entity);
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

    
    public List<Habitacio> findRoomsWithCriteria(String criteria){
        TypedQuery<Habitacio> query;
        if(criteria.toUpperCase().equals("ASC")){
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalASC");
        }else{
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalDESC");
        }
        
        return query.getResultList();
    }
    
    public List<Habitacio> findRoomsWithCityAndCriteria(String city, String criteria){
        TypedQuery <Habitacio> query;
        if(criteria.toUpperCase().equals("ASC")){
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalASC")
                .setParameter("city", city);
        }else{
            query = (TypedQuery<Habitacio>) getEntityManager()
                .createNamedQuery("room.findAllCondicionalDESC")
                .setParameter("city", city);
        }

        return query.getResultList();
    }
    
    public List<Llogater> findAllTenants(){
        TypedQuery<Llogater> query = (TypedQuery<Llogater>) getEntityManager()
                .createNamedQuery("tenant.findAll");
        return query.getResultList();
    }
    
}
