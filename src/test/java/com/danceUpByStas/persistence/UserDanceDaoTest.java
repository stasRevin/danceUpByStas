package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDanceDaoTest {

    private UserDanceDao userDanceDao;
    private UserDao userDao;
    private GenericDao<Dance> genericDao;
    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userDanceDao = new UserDanceDao();
        this.userDao = new UserDao();
        this.genericDao = new GenericDao<>(Dance.class);
    }

    @Test
    void getAllSuccess() {

        User user = userDao.getUserById(1);
        Dance dance = genericDao.getById(1);
        userDanceDao.insert(new UserDance(user, dance));
        List<UserDance> userDance = userDanceDao.getAll();
        assertEquals(1, userDance.size());

    }

    @Test
    void saveOrUpdateSuccess() {

        User user = userDao.getUserById(1);
        Dance dance = genericDao.getById(2);
        userDanceDao.insert(new UserDance(user, dance));
        List<UserDance> userDanceList = userDanceDao.getDancesByUserId(1);
        UserDance userDance = userDanceList.get(0);
        Dance newDance = genericDao.getById(2);
        userDance.setDance(newDance);
        assertEquals(2, userDanceDao.getDancesByUserId(1).get(0).getDance().getId());

    }

    @Test
    void getDancesByUserIdSuccess() {

        User user = userDao.getUserById(2);
        Dance dance = genericDao.getById(2);
        userDanceDao.insert(new UserDance(user, dance));
        List<UserDance> userDanceList = userDanceDao.getDancesByUserId(2);
        assertEquals(2, userDanceList.get(0).getDance().getId());

    }
}