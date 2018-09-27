package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.entity.UserRole;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public void delete(UserDance userDance) {

        genericDao.delete(userDance);
    }


    public List<UserDance> getDancesByUserId(int userId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDance> query = builder.createQuery(UserDance.class);
        Root<UserDance> root = query.from(UserDance.class);
        query.select(root).where(builder.equal(root.get("user").get("id"), userId));
        List<UserDance> userDances = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return userDances;

    }

    private Session getSession() {

        return sessionFactory.openSession();

    }


}
