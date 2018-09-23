package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoleDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Role> genericDao;

    public RoleDao() {

        genericDao = new GenericDao(Role.class);
    }

    public Role getById(int id) {

        return genericDao.getById(id);

    }

    private Session getSession() {

        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}
