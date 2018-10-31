package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Role;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDaoTest {

    private GenericDao<UserRole> userRoleDaoGeneric;
    private GenericDao<User> userDao;
    private GenericDao<Role> genericDao;
    private final Logger logger = LogManager.getLogger(this.getClass());


    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userRoleDaoGeneric = new GenericDao<>(UserRole.class);
        this.userDao = new GenericDao<>(User.class);
        this.genericDao = new GenericDao<Role>(Role.class);
    }

    @Test
    void getAllUserRolesSuccess() {

        List<UserRole> userRoleList = userRoleDaoGeneric.getAll();
        assertEquals(3, userRoleList.size());
    }

    @Test
    void insertUserRoleSuccess() {

        User user = userDao.getById(2);
        Role role = genericDao.getById(1);
        UserRole userRoleInserted = userRoleDaoGeneric.insertManyToMany(new UserRole(user, role));
        assertEquals(2, userRoleInserted.getUser().getId());
        assertEquals(1, userRoleInserted.getRole().getId());

    }


    @Test
    void getElementsByTwoPropertiesSuccess() {

        String entityOne = "user";
        String entityTwo = "role";
        String propertyOne = "id";
        String propertyTwo = "id";
        String valueOne = "1";
        String valueTwo = "1";

        Map<String, Map<String, String>> entities = new HashMap<>();
        Map<String, String> propertiesOne= new HashMap<>();
        Map<String, String> propertiesTwo = new HashMap<>();

        propertiesOne.put(propertyOne, valueOne);
        entities.put(entityOne, propertiesOne);
        propertiesTwo.put(propertyTwo, valueTwo);
        entities.put(entityTwo, propertiesTwo);

        List<UserRole> userRoleList = userRoleDaoGeneric.getElementsByEntitiesAndProperties(entities);

        assertEquals(1, userRoleList.get(0).getUser().getId());
        assertEquals(1, userRoleList.get(0).getRole().getId());
    }

}