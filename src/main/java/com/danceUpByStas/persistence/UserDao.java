package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
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

    public int insertUser(User user) {

        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }


    public User getUserById(int id) {

        return genericDao.getById(id);
    }


    public void saveOrUpdate(User user) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {

        genericDao.delete(user);
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

    public List<User> getAllUsers() {

        return genericDao.getAll();
    }


    private Session getSession() {

        return sessionFactory.openSession();

    }

}
