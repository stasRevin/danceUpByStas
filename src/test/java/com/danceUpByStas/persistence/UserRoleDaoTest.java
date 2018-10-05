package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Role;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDaoTest {

    private GenericDao<UserRole> userRoleDaoGeneric;
    private GenericDao<User> userDao;
    private GenericDao<Role> genericDao;
    private UserRoleDao userRoleDao;
    private final Logger logger = LogManager.getLogger(this.getClass());
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userRoleDaoGeneric = new GenericDao<>(UserRole.class);
        this.userDao = new GenericDao<>(User.class);
        this.genericDao = new GenericDao<Role>(Role.class);
        this.userRoleDao = new UserRoleDao();
    }

    @Test
    void getAllUserRolesSuccess() {

        List<UserRole> userRoleList = userRoleDaoGeneric.getAll();
        assertEquals(2, userRoleList.size());
    }

    @Test
    void insertUserRoleSuccess() {

        User user = userDao.getById(1);
        Role role = genericDao.getById(2);
        UserRole userRoleInserted = userRoleDaoGeneric.insertManyToMany(new UserRole(user, role));
        assertEquals(1, userRoleInserted.getUser().getId());
        assertEquals(2, userRoleInserted.getRole().getId());

    }

    @Test
    void getInstructorsByPropertySuccess() {

        List<UserRole> instructors = userRoleDao.getInstructorsByProperty("postalCode", "53705");
        logger.debug(instructors);
    }

    @Test
    void getElementsByTwoPropertiesSuccess() {

        String entityOne = "user";
        String entityTwo = "role";
        String propertyOne = "id";
        String propertyTwo = "id";
        String valueOne = "1";
        String valueTwo = "1";

        List<UserRole> userRoleList = userRoleDaoGeneric.getElementsByTwoEntitiesAndTwoProperties(entityOne, entityTwo, propertyOne,
                                                                                    propertyTwo, valueOne, valueTwo);

        assertEquals(1, userRoleList.get(0).getUser().getId());
        assertEquals(1, userRoleList.get(0).getRole().getId());
    }

}