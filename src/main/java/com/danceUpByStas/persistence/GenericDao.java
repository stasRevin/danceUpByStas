package com.danceUpByStas.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 *
 */

public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public GenericDao(Class<T> type) {

        this.type = type;
    }


    public List<T> getAll() {

        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    public <T> T getById(int id) {

        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;

    }

    public void delete(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();

    }

    public int insert(T entity) {

        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return  id;

    }

    public void saveOrUpdate(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    public Class<T> insertManyToMany(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Class<T> insertedEntity = (Class<T>)session.save(entity);
        transaction.commit();
        session.close();
        return insertedEntity;
    }


    private Session getSession() {

        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
