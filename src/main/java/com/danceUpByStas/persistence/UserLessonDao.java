package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserLesson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserLessonDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<UserLesson> getLessonsByUserId(int userId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserLesson> query = builder.createQuery(UserLesson.class);
        Root<UserLesson> root = query.from(UserLesson.class);
        query.select(root).where(builder.equal(root.get("user").get("id"), userId));
        List<UserLesson> userLessons = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return userLessons;
    }

    public List<UserLesson> getUsersByLessonId(int lessonId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserLesson> query = builder.createQuery(UserLesson.class);
        Root<UserLesson> root = query.from(UserLesson.class);
        query.select(root).where(builder.equal(root.get("lesson").get("id"), lessonId));
        List<UserLesson> userLessons = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return userLessons;
    }


    private Session getSession() {

        return sessionFactory.openSession();
    }
}
