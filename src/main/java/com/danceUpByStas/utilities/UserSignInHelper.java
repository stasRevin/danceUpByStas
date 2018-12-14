package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.*;
import com.danceUpByStas.enums.UserRoleEnum;
import com.danceUpByStas.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is ther UserSignInHelper class designed to perform operations required for signing in.
 * @author srevin
 */
public class UserSignInHelper {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This method returns the list of user dances.
     * @param userId The id of the user.
     * @return userDanceList The list of user dances.
     */
    public List<UserDance> getUserDances(int userId) {

        GenericDao<UserDance> userDanceDao = new GenericDao<>(UserDance.class);
        return userDanceDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);

    }

    /**
     * This method returns the user schedule.
     * @param userId The user id.
     * @return scheduleList The list of user schedules.
     */
    public List<Schedule> getUserSchedule(int userId) {

        GenericDao<Schedule> scheduleGenericDao = new GenericDao<>(Schedule.class);

        return scheduleGenericDao.getElementsOfTypeAByIdOfEntityOfTypeB("user", userId);
    }

    /**
     * This method returns user lessons.
     * @param userId The user id.
     * @param role The role id.
     * @return userLessonList The list of user lessons.
     */
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

    /**
     * This method sets user dances.
     * @param users The set of user object references.
     */
    public void setUserDances(Set<User> users) {

        for (User user : users) {

            user.setDances(getUserDances(user.getId()));

        }
    }

    /**
     * This method returns the list of user lessons for the specified role.
     * @param userLessons The list of UserLesson object references.
     * @param lessonsForRole The specified role
     * @return userLessons The list of UserLesson object references.
     */
    public List<UserLesson> getUsersForLessons(List<UserLesson> userLessons, int lessonsForRole) {

        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);
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

    /**
     * This method returns the list of user notifications.
     * @param user The reference to the user object.
     * @return notifications.
     */
    public List<Notification> getNotifications(User user) {

        GenericDao<Notification> notificationDao = new GenericDao<>(Notification.class);
        List<Notification> notifications = null;

            notifications = notificationDao
                    .getElementsOfTypeAByIdOfEntityOfTypeBAndPropertyA("user", user.getId(), "isRead", "0");

            Collections.sort(notifications, Comparator.comparing(Notification::getDateTime).reversed());


        return notifications;
    }

    /**
     * This method gets user by id.
     * @param userId The user id.
     * @return user The reference to the found user object.
     */
    public User getUserById(int userId) {

        GenericDao<User> userDao = new GenericDao<>(User.class);

        return userDao.getById(userId);
    }

}
