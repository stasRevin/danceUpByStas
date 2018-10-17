package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private GenericDao<User> genericDao;

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.genericDao = new GenericDao<>(User.class);
    }

    @Test
    void getAllUsersSuccess() {

        List<User> users = genericDao.getAll();
        assertEquals(2, users.size());

    }

    @Test
    void insertUserSuccess() {

        int id = genericDao.insert(new User("vpablo", "123abc", 0, "Victor",
                "Pablo", "123 Main St", "", "Madison", "WI",
                "53705", 65.00, ""));
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

    @Test
    void getElementByTwoPropertiesSuccess() {
    /*
        User user = genericDao.getElementByTwoProperties("username", "password",
                                                            "mjessy", "123abc");
    */
        Map<String, String> properties = new HashMap<>();
        properties.put("username", "mjessy");
        properties.put("password", "123abc");

        List<User> users = genericDao.getElementsByMultipleProperties(properties);
        assertEquals("Jessy", users.get(0).getLastName());
    }


    @Test
    void getElementByPropertySuccess() {

        String postalCode = "53705";
        User user = genericDao.getElementByProperty("postalCode", postalCode);

        assertEquals("Michael", user.getFirstName());

    }


    @Test
    void deleteEntityByPropertySuccess() {

        int id = genericDao.insert(new User("vpablo", "123abc", 0, "Victor",
                "Escobar", "123 Main St", "", "Madison", "WI",
                "53705", 65.00, ""));

        List<User> users = genericDao.getAll();

        if (users.size() == 3) {
            genericDao.deleteEntityByProperty("lastName", "Escobar");
            users = genericDao.getAll();
            assertEquals(2, users.size());

        }

    }

}