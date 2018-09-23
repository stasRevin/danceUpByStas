package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDanceDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    GenericDao<UserDance> genericDao;

    public UserDanceDao() {

        genericDao = new GenericDao<UserDance>(UserDance.class);
    }

    public List<UserDance> getAll() {

        return genericDao.getAll();
    }

    public UserDance insert(UserDance userDance) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        UserDance userDanceInserted = (UserDance)session.save(userDance);
        transaction.commit();
        session.close();
        return userDanceInserted;


    }

    private Session getSession() {

        return sessionFactory.openSession();

    }


}
