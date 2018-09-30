package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;
    private GenericDao<User> genericDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userDao = new UserDao();
        this.genericDao = new GenericDao<>(User.class);
    }

    @Test
    void getAllUsersSuccess() {

        List<User> users = genericDao.getAll();
        assertEquals(2, users.size());

    }

    @Test
    void insertUserSuccess() {

        int id = genericDao.insert(new User("vpablo", "123abc", (short)0, "Victor",
                "Pablo", "123 Main St", "", "Madison", "WI",
                "53705", 65.00));
        User user = genericDao.getById(3);

        assertEquals("Pablo", user.getLastName());

    }

    @Test
    void saveOrUpdateUserSuccess() {

        User user = genericDao.getById(1);
        user.setCity("Milwaukee");
        genericDao.saveOrUpdate(user);
        user = genericDao.getById(1);
        assertEquals("Milwaukee", user.getCity());

    }


}