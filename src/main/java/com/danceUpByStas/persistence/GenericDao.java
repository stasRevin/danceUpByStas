package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserRole;
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

    public T insertManyToMany(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        T entityInserted = type.cast(session.save(entity));
        transaction.commit();
        session.close();

        return entityInserted;
    }

    /**
     * This method will get elements of type A by ID of entity of type B
     * @param entityAName the name of the entity A
     * @param id the id of the entity B
     * @return elements found
     */
    public List<T> getElementsOfTypeAByIdOfEntityOfTypeB(String entityAName,
                                                         int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(entityAName).get("id"), id));
        List<T> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return elements;

    }

    public List<T> getElementsByTwoEntitiesAndTwoProperties(String entityOne, String entityTwo, String propertyOne, String propertyTwo,
                                             String valueOne, String valueTwo) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder
                .and(builder
                                .equal(root.get(entityOne).get(propertyOne), valueOne),
                        builder.equal(root.get(entityTwo).get(propertyTwo), valueTwo)));

        List<T> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return resultList;

    }

    public T getElementByTwoProperties(String propertyOne, String propertyTwo, String valueOne, String valueTwo) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder
                          .and(builder
                          .equal(root.get(propertyOne), valueOne), builder
                          .equal(root.get(propertyTwo), valueTwo)));

        T element = session.createQuery(query).getSingleResult();
        transaction.commit();
        session.close();

        return element;
    }


    private Session getSession() {

        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
