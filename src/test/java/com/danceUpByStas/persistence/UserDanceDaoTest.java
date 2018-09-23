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
    private DanceDao danceDao;
    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userDanceDao = new UserDanceDao();
        this.userDao = new UserDao();
        this.danceDao = new DanceDao();
    }

    @Test
    void getAllSuccess() {

        User user = userDao.getUserById(1);
        Dance dance = danceDao.getDanceById(1);
        userDanceDao.insert(new UserDance(user, dance));
        List<UserDance> userDance = userDanceDao.getAll();
        assertEquals(1, userDance.size());

    }
}