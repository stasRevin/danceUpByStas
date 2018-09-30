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

public class UserRoleDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    GenericDao<UserRole> genericDao;

    public UserRoleDao() {

        genericDao = new GenericDao<UserRole>(UserRole.class);
    }



    public List<UserRole> getInstructorsByProperty(String property, String value) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root).where(builder
                          .and(builder
                          .equal(root.get("role"), 1),
                           builder.equal(root.get("user").get(property), value)));

        List<UserRole> userRoles = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return userRoles;

    }


    private Session getSession() {

        return sessionFactory.openSession();

    }

}
