package com.danceUpByStas.persistence;

import com.danceUpByStas.controller.DeleteUserDance;
import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserDance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dance dao test.
 * @author srevin
 */
class UserDanceDaoTest {

    private GenericDao<Dance> genericDaoDance;
    private GenericDao<User> genericDaoUser;
    private GenericDao<UserDance> userDanceGeneric;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.genericDaoDance = new GenericDao<>(Dance.class);
        this.userDanceGeneric = new GenericDao<>(UserDance.class);
        this.genericDaoUser = new GenericDao<>(User.class);
    }

    /**
     * Gets all success.
     */
    @Test
    void getAllSuccess() {

        List<UserDance> userDance = userDanceGeneric.getAll();
        assertEquals(2, userDance.size());

    }

    /**
     * Insert user dance success.
     */
    @Test
    void insertUserDanceSuccess() {

        User user = new User("apeter", "123abc", 0, "Peter",
                             "Stevenson", "123 Main St","Madison",
                             "WI", "53705", 80.00, "");
        Dance dance = new Dance("Pasodoble", "strong, theatrical");
        UserDance userDance = new UserDance(user, dance);

        genericDaoUser.insert(user);
        genericDaoDance.insert(dance);
        UserDance insertedDance = userDanceGeneric.insertManyToMany(userDance);

        assertEquals(userDance, insertedDance);

    }

    /**
     * Save or update success.
     */
    @Test
    void saveOrUpdateSuccess() {

        List<UserDance> userDanceList = userDanceGeneric.getElementsOfTypeAByIdOfEntityOfTypeB("user", 1);
        UserDance userDance = userDanceList.get(0);
        Dance newDance = (Dance)genericDaoDance.getById(3);
        userDance.setDance(newDance);
        userDanceGeneric.saveOrUpdate(userDance);
        assertEquals(1, userDanceGeneric.getElementsOfTypeAByIdOfEntityOfTypeB("dance", 3).get(0).getUser().getId());

    }

    /**
     * Gets dances by user id success.
     */
    @Test
    void getDancesByUserIdSuccess() {

        User user = genericDaoUser.getById(2);
        Dance dance = (Dance)genericDaoDance.getById(2);
        userDanceGeneric.insertManyToMany(new UserDance(user, dance));
        List<UserDance> userDanceList = userDanceGeneric.getElementsOfTypeAByIdOfEntityOfTypeB("user", 2);
        assertEquals(2, userDanceList.get(0).getDance().getId());

    }

    /**
     * Delete user dances by user id success.
     */
    @Test
    void deleteUserDancesByUserIdSuccess() {

        List<UserDance> userDances = userDanceGeneric.getElementsOfTypeAByIdOfEntityOfTypeB("user", 1);
        Optional<UserDance> userDanceToDelete = userDances.stream().filter(ud -> ud.getDance().getId() == 1).findFirst();
        userDanceGeneric.delete(userDanceToDelete.get());
        userDances = userDanceGeneric.getElementsOfTypeAByIdOfEntityOfTypeB("user", 1);

        boolean wasDeleted = !userDances.contains(userDanceToDelete);

        assertEquals(wasDeleted, true);
    }


}