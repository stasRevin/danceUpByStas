package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Dance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class DanceDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    GenericDao<Dance> genericDao;

    public DanceDao() {

        genericDao = new GenericDao<Dance>(Dance.class);
    }

    public int insert(Dance dance) {

        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(dance);
        transaction.commit();
        session.close();
        return id;

    }


    public List<Dance> getAllDances() {

        return genericDao.getAll();
    }


    public Dance getDanceById(int id) {

        return genericDao.getById(id);
    }

    public void saveOrUpdate(Dance dance) {

        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(dance);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteDance(Dance dance) {

        genericDao.delete(dance);
    }


    private Session getSession() {

        return sessionFactory.openSession();

    }

}
