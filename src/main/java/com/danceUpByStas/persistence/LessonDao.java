package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.Lesson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LessonDao {


    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Lesson> getLocationByLessonId(int lessonId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        query.select(root).where(builder.equal(root.get("location"), lessonId));
        List<Lesson> lesson = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return lesson;

    }


    private Session getSession() {

        return sessionFactory.openSession();

    }

}
