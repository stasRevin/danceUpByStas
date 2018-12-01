package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserLesson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // https://stackoverflow.com/questions/41806152/add-criteriabuilder-betweendate-to-predicate

    public List<Schedule> getScheduleRangeForUser(int userId, LocalDate startDate, LocalDate endDate) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> query = builder.createQuery(Schedule.class);
        Root<Schedule> root = query.from(Schedule.class);
        List<Predicate> predicates = new ArrayList<>();
        Predicate onStart = builder.greaterThanOrEqualTo(root.get("date"), startDate);
        Predicate onEnd = builder.lessThanOrEqualTo(root.get("date"), endDate);
        Predicate onUserId = builder.equal(root.get("user").get("id"), userId);

        predicates.add(onUserId);
        predicates.add(onStart);
        predicates.add(onEnd);

        query.select(root).where(predicates.toArray(new Predicate[]{}));

        List<Schedule> schedules = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return schedules;
    }


    public int insertSchedulesInRangeForUser(User user, LocalDate startDate, LocalDate endDate,
                                              Map<DayOfWeek, List<LocalTime>> schedules) {

        GenericDao<Schedule> genericDao = new GenericDao<>(Schedule.class);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Schedule> existingSchedules = null;
        int errorCounter = 0;

        for (LocalDate date = startDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusDays(1)) {

           DayOfWeek currentDay = date.getDayOfWeek();


           if (schedules.containsKey(currentDay)) {

               List<LocalTime> times = schedules.get(currentDay);
               LocalTime startTime = times.get(0);
               LocalTime endTime = times.get(1);
               existingSchedules = getMatchingSchedule(date, startTime, endTime, user);

               if (existingSchedules.size() == 0) {

                   Schedule schedule = new Schedule(date, startTime, times.get(1), user);
                   session.save(schedule);
               }

           } else {

               errorCounter += 1;
           }
        }
        transaction.commit();
        session.close();

        return errorCounter;
    }

    public List<Schedule> getMatchingSchedule(LocalDate date, LocalTime startTime, LocalTime endTime, User user) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Schedule> query = builder.createQuery(Schedule.class);
        Root<Schedule> root = query.from(Schedule.class);
        query.select(root).where(builder.and(builder.equal(root.get("user"), user),
                                             builder.equal(root.get("date"), date),
                                             builder.equal(root.get("startTime"), startTime),
                                             builder.equal(root.get("endTime"), endTime)));
        List<Schedule> elements = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();

        return elements;
    }


    public List<LocalTime> getAvailabilityForDateByInstructorId(LocalDate date, int userId) {

        List<Schedule> schedules = getScheduleByUserIdAndDate(userId, date);
        List<LocalTime> availableTimes = new ArrayList<>();

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
        Map<String, Map<String, String>> userLessonEntities = new HashMap<>();
        Map<String, String> userLessonPropertiesOne = new HashMap<>();
        Map<String, String> userLessonPropertiesTwo = new HashMap<>();

        userLessonPropertiesOne.put("id", userId + "");
        userLessonPropertiesTwo.put("id", "1");
        userLessonEntities.put("user", userLessonPropertiesOne);
        userLessonEntities.put("role", userLessonPropertiesTwo);


        List<UserLesson> userLessons = userLessonDao.getElementsByEntitiesAndProperties(userLessonEntities)
                                        .stream().filter(l -> l.getLesson().getDate().compareTo(date) == 0)
                                        .collect(Collectors.toList());

        LocalTime startTime = null;
        LocalTime endTime = null;

        for (Schedule schedule : schedules) {

            startTime = schedule.getStartTime();
            endTime = schedule.getEndTime();

            iterateOverScheduleRange(startTime, endTime, availableTimes, userLessons);

        }

        return availableTimes;
    }


    private void iterateOverScheduleRange(LocalTime startTime, LocalTime endTime, List<LocalTime> availableTimes,
                                          List<UserLesson> userLessons) {

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusHours(1)) {

            compareTimes(userLessons, availableTimes, time);
        }
    }

    private void compareTimes(List<UserLesson> userLessons, List<LocalTime> availableTimes, LocalTime time) {

        boolean isBooked = false;

        for (UserLesson lesson : userLessons) {

            LocalTime lessonTime = lesson.getLesson().getStartTime();

            if (lessonTime.compareTo(time) == 0) {

                isBooked = true;
            }
        }

        if (!isBooked) {

            availableTimes.add(time);
        }

    }

    private Session getSession() {

        return sessionFactory.openSession();
    }
}
