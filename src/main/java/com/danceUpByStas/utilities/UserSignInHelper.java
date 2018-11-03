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

    public Map<Lesson, User> getStudentsForLessons(List<UserLesson> userLessons) {

        Map<Lesson, User> lessonStudentMap = new HashMap<>();
        GenericDao<UserLesson> userLessonDao = new GenericDao<>(UserLesson.class);

        for (UserLesson userLesson : userLessons) {

            Lesson lesson = userLesson.getLesson();

            List<UserLesson> studentLessons
                        = userLessonDao.getElementsOfTypeAByIdOfEntityOfTypeB("lesson", lesson.getId())
                                       .stream().filter(ul -> ul.getRole().getId() == 2).collect(Collectors.toList());
            User student = studentLessons.get(0).getUser();
            lessonStudentMap.put(lesson, student);
        }

        return lessonStudentMap;
    }

}
