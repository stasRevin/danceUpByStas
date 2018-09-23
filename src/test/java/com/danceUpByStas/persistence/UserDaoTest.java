package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userDao = new UserDao();
    }

    @Test
    void getAllUsersSuccess() {

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());

    }

    @Test
    void insertUserSuccess() {

        int id = userDao.insertUser(new User("vpablo", "123abc", (short)0, "Victor",
                "Pablo", "123 Main St", "", "Madison", "WI",
                "53705", 65.00));

        assertEquals("Pablo", userDao.getUserById(3).getLastName());

    }

    @Test
    void saveOrUpdateUserSuccess() {

        User user = userDao.getUserById(1);
        user.setCity("Milwaukee");
        userDao.saveOrUpdate(user);
        assertEquals("Milwaukee", userDao.getUserById(1).getCity());

    }


}