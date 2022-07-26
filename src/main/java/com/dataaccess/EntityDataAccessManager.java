package com.dataaccess;


import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    //Below Method is equivalent to where condition in SQL
    //Pass the COLUMN NAME and the VALUE depending on the Entity
    public List<T> findBy(String colName, Object val) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root).where(em.getCriteriaBuilder().equal(root.get(colName),val));

        return em.createQuery(cq).getResultList();
    }

    //Pass the Column Names in a list depending on the Entity
    public List<T> filter(Map<String,Object> fitlerCols) {
        javax.persistence.criteria.CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = criteriaBuilder.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();

        //Each Column Name with its Corresponding Value will be added as Condition in Select query
        for (Map.Entry<String,Object> map:fitlerCols.entrySet()){
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(map.getKey()),map.getValue())));
        }


        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        return em.createQuery(cq).getResultList();
    }

}
