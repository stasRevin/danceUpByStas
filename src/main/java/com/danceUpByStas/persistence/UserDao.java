package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.rmi.runtime.Log;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    GenericDao<User> genericDao;

    public UserDao() {

        genericDao = new GenericDao<User>(User.class);
    }


    public List<User> getByPropertyEqual(String propertyName, String value) {

        Session session = getSession();

        logger.debug("Searching for user with {} = {}", propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        return users;

    }

    public List<User> getInstructorsByProperty(String property, String value) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.select(root).where(builder
                          .and(builder
                          .equal(root.get("role"), 1),
                           builder.equal(root.get(property), value)));
        List<User> users = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return users;

    }

    public User sigIn(String username, String password) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.select(root).where(builder.and(
                                 builder.equal(root.get("username"), username),
                                 builder.equal(root.get("password"), password)
        ));
        User user = (User)session.createQuery(query).getSingleResult();
        transaction.commit();
        session.close();

        return user;
    }

    private Session getSession() {

        return sessionFactory.openSession();

    }

}
