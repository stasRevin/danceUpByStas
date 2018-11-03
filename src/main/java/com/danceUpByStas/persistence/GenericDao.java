package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @param entityBName the name of the entity B
     * @param id the id of the entity B
     * @return elements found
     */
    public List<T> getElementsOfTypeAByIdOfEntityOfTypeB(String entityBName,
                                                              int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(entityBName).get("id"), id));
        List<T> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return elements;

    }

    public List<T> getElementsOfTypeAByIdOfEntityOfTypeBAndPropertyA(String entityBName,
                                                         int id, String propertyName, String propertyValue) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.and(builder.equal(root.get(entityBName).get("id"), id)),
                                            builder.equal(root.get(propertyName), propertyValue));
        List<T> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return elements;

    }


    public List<T> getElementsByEntitiesAndProperties(Map<String, Map<String, String>> entities) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Map<String, String>> outerEntry : entities.entrySet()) {

            String entityKey = outerEntry.getKey();
            Map<String, String> properties = outerEntry.getValue();

            for (Map.Entry<String, String> innerEntry : properties.entrySet()) {

                if (entityKey.isEmpty()) {

                    predicates.add(builder.equal(root.get(innerEntry.getKey()), innerEntry.getValue()));
                    break;
                }

                predicates.add(builder.equal(root.get(entityKey).get(innerEntry.getKey()), innerEntry.getValue()));
            }
        }

        query.select(root).where(builder.and(predicates.toArray(new Predicate[] {})));

        List<T> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return resultList;

    }

    //https://stackoverflow.com/questions/12244799/correct-usage-of-jpa-criteria-api-predicates-and-where-method-of-criteriaquery
    public List<T> getElementsByMultipleProperties(Map<String, String> properties) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, String> entry : properties.entrySet()) {

            predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
        }

        query.select(root).where(builder.and(predicates.toArray(new Predicate[] {})));
        List<T> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return elements;
    }

    public T getElementByProperty(String property, String value) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.select(root).where(builder.equal(root.get(property), value));
        T element = (T) session.createQuery(query).getSingleResult();
        transaction.commit();
        session.close();
        return element;


    }

    //https://www.thoughts-on-java.org/criteria-updatedelete-easy-way-to/

    public void deleteEntityByProperty(String property, String value) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete delete = builder.createCriteriaDelete(type);
        Root root = delete.from(type);
        delete.where(builder.equal(root.get(property), value));
        session.createQuery(delete).executeUpdate();
        transaction.commit();
        session.close();
    }


    public List<T> getElementsInList(String property, List<String> itemList) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(type);
        Root<T> root = query.from(type);

        Expression<T> expression = root.get(property);
        Predicate predicate = expression.in(itemList);

        query.select(root).where(predicate);

        List<T> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        query.select(root).where(predicate);

        return elements;

    }


    private Session getSession() {

        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
