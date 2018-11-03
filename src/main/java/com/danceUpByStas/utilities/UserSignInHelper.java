package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

public class UserSignInHelper {


    public List<UserDance> getUserDances(int userId) {

        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
        return userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);

    }

    public List<Schedule> getUserSchedule(int userId) {

        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);

        return scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);
    }

    public List<UserLesson> getUserLessons(int userId, int role) {

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);

        Map<String, Map<String, String>> userLessonEntities = new HashMap<>();
        Map<String, String> userLessonPropertiesOne = new HashMap<>();
        Map<String, String> userLessonPropertiesTwo = new HashMap<>();

        userLessonPropertiesOne.put("id", userId + "");
        userLessonPropertiesTwo.put("id", role + "");
        userLessonEntities.put("user", userLessonPropertiesOne);
        userLessonEntities.put("role", userLessonPropertiesTwo);

        return userLessonDao.getElementsByEntitiesAndProperties(userLessonEntities);
    }

    public void setUserDances(Set<User> users) {

        for (User user : users) {

            user.setDances(getUserDances(user.getId()));

        }
    }

    public List<UserLesson> getUsersForLessons(List<UserLesson> userLessons, int lessonsForRole) {

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
        User student = null;
        Lesson lesson = null;
        List<UserLesson> specificLessons = null;

        for (UserLesson userLesson : userLessons) {

            lesson = userLesson.getLesson();

            specificLessons
                        = userLessonDao.getElementsOfTypeAByIdOfEntityOfTypeB("lesson", lesson.getId())
                                       .stream().filter(ul -> ul.getRole().getId() == lessonsForRole).collect(Collectors.toList());


            if (!specificLessons.isEmpty()) {

                if (lessonsForRole == 1) {

                    lesson.addInstructors(specificLessons.get(0).getUser());

                } else if (lessonsForRole == 2) {

                    lesson.addStudents(specificLessons.get(0).getUser());
                }

            }

        }

        return userLessons;
    }

    public List<Notification> getNotifications(User user) {

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);

        return notificationDao
                .getElementsOfTypeAByIdOfEntityOfTypeBAndPropertyA("user", user.getId(), "isRead", "0");
    }

}
