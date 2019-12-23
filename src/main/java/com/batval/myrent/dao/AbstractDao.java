package com.batval.myrent.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
//
/**
 * Abstract class for working with a database
 *
 * @author Baturo Valery
 * @version 1.0
 */
public abstract class AbstractDao<PK extends Serializable, T> {


    /**
     * Factory responsible for the database
     *
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Persistent class for storing Java objects in the database
     *
     */
    private final Class<T> persistentClass;


    /**
     * Constructor for abstract dao class
     *
     */
    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Get a database session
     *
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Get value by key
     *
     */
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * Save object to the database
     *
     */
    public void persist(T entity) {
        getSession().persist(entity);
    }
    /**
     * Update object to the database
     *
     */
    public void update(T entity) {
        getSession().update(entity);
    }
    /**
     * Delete object to the database
     *
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }
    /**
     * Return result by criterion
     *
     */
    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}
