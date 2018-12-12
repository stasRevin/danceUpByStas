package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

public class UserSignInHelper {

    private Logger logger = LogManager.getLogger(this.getClass());

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

                if (lessonsForRole == UserRoleEnum.INSTRUCTOR.getRoleNumber()) {

                    lesson.addInstructors(specificLessons.get(0).getUser());

                } else if (lessonsForRole == UserRoleEnum.STUDENT.getRoleNumber()) {

                    lesson.addStudents(specificLessons.get(0).getUser());
                }

            }

        }

        return userLessons;
    }

    public List<Notification> getNotifications(User user) {

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        List<Notification> notifications = null;

        try {
            notifications = notificationDao
                    .getElementsOfTypeAByIdOfEntityOfTypeBAndPropertyA("user", user.getId(), "isRead", "0");

            Collections.sort(notifications, Comparator.comparing(Notification::getDateTime).reversed());
        } catch (Exception exception) {

            logger.debug("Problem finding user notifications: {}", exception);
        }

        return notifications;
    }


    public User getUserById(int userId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);

        User user = userDao.getById(userId);

        return user;
    }

}
