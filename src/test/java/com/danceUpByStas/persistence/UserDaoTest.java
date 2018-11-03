package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private GenericDao<User> genericDao = new GenericDao<>(User.class);

    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
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

    @Test
    void addAndRemoveInstructorsTeachingLocation() {

        GenericDao<Location> locationGenericDao = new GenericDao<>(Location.class);

        Location location = new Location("Empire Ballroom", "134 Main St", "",
                                    "Madison", "WI", "53706");
        locationGenericDao.insert(location);
        User user = genericDao.getById(1);
        user.addTeachingLocation(location);

        genericDao.saveOrUpdate(user);

        user = genericDao.getById(1);

        Set<Location> teachingLocations = user.getLocations();

        assertEquals(true, teachingLocations.contains(location));

        teachingLocations.remove(location);

        assertEquals(false, teachingLocations.contains(location));
    }


}