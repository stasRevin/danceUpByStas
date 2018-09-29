package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    public List<Schedule> getScheduleByUserIdAndDate(int userId, LocalDate date) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> query = builder.createQuery(Schedule.class);
        Root<Schedule> root = query.from(Schedule.class);
        query.select(root).where(builder
                .and(builder
                .equal(root.get("user").get("id"), userId),
                 builder.equal(root.get("date"), date)));
        List<Schedule> schedules = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return schedules;
    }

    private Session getSession() {

        return sessionFactory.openSession();
    }
}
