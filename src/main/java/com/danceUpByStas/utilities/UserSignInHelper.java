package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.Schedule;
import com.danceUpByStas.entity.UserDance;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.persistence.GenericDao;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
