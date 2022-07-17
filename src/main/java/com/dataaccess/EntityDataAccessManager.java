package com.dataaccess;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityDataAccessManager<T> {
    private Class<T> entityClass;
    private EntityManager em;
    public EntityDataAccessManager(Class<T> entityClass){
        this.entityClass = entityClass;
        /*The Parameter Passed should be declared in the Persistence.xml file */
        EntityManagerFactory ref = Persistence.createEntityManagerFactory("TestPersistence");
        em = ref.createEntityManager();
    }

    /* Create the Entity in the Database */
    public void create (T entity){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
    }

    /* Update the Entity in the Database */
    public void update (T entity){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(entity);
        et.commit();
    }

    /* Remove the Entity in the Database */
    public void remove(T entity){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(entity);
        et.commit();
    }

    /* Get the Entity  By Id in the Database */
    public T find(Object id){
        return em.find(entityClass, id);
    }

    /* Get all the Entities from the Database */
    public List<T> findAll(){
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return em.createQuery(cq).getResultList();
    }

    public List<T> findBy(String colName){
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return em.createQuery(cq).getResultList();
    }

}
