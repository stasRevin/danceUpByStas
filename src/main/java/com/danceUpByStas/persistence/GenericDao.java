package com.danceUpByStas.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 * This is the GenericDao class designed to store a set of generic Hibernate queries.
 * @author srevin
 */

public class GenericDao<T> {

    private Class<T> type;

    /**
     * The constructor with type parameter.
     * @param type The reference to what type this generic DAO will relate.
     */
    public GenericDao(Class<T> type) {

        this.type = type;
    }

    /**
     * This method gets all elements of the specified type.
     * @return list The list of all instances.
     */
    public List<T> getAll() {

        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * This method gets an element by its id.
     * @param id The id of the element.
     * @param <T> The type of the element.
     * @return entity The found entity.
     */
    public <T> T getById(int id) {

        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;

    }

    /**
     * This method deletes the specified entity.
     * @param entity The entity to delete.
     */
    public void delete(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();

    }

    /**
     * This method inserts the specified entity.
     * @param entity The entity to insert.
     * @return id The returned id.
     */
    public int insert(T entity) {

        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return  id;
    }

    /**
     * This method saves or updates the specified entity.
     * @param entity The entity to save or update.
     */
    public void saveOrUpdate(T entity) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * This method inserts a junction entity.
     * @param entity The junction entity to insert.
     * @return entityInserted. The inserted entity.
     */
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

    /**
     * This method gets elements of type 'A' by id of entity of type 'B' and a property of type 'A'
     * @param entityBName The name of entity 'B'
     * @param id The id of entity of type 'A'
     * @param propertyName The name of property of 'A' entity.
     * @param propertyValue The value of property of 'A' entity.
     * @return elements The elements that were found.
     */
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

    /**
     * This method gets elements by entities and properties.
     * @param entities The map of entities and its properties.
     * @return resultList The list of results.
     */
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

    /**
     * This method gets elements by multiple properties.
     * @param properties The map of properties.
     * @return elements. The elements that were found.
     */
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

    /**
     * This method gets an element by property.
     * @param property The name of the property.
     * @param value The property value.
     * @return element. The element found.
     */
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

    /**
     * This elements deletes entity by its property.
     * @param property The name of the property.
     * @param value The property value.
     */
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

    /**
     * This method imitate SELECT 1 FROM %Table% WHERE %Property% IN (%VALUES%)
     * @param property The property name.
     * @param itemList The list of qualified items.
     * @return elements The found elements.
     */
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

    /**
     * This method returns the open session.
     * @return session The Hibernate session.
     */
    private Session getSession() {

        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
