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
/*
    private UserRoleDao userRoleDao;
    private UserDao userDao;
    private RoleDao roleDao;
    private final Logger logger = LogManager.getLogger(this.getClass());
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userRoleDao = new UserRoleDao();
        this.userDao = new UserDao();
        this.roleDao = new RoleDao();
    }

    @Test
    void getAllUserRolesSuccess() {

        List<UserRole> userRoleList = userRoleDao.getAllUserRoles();
        assertEquals(2, userRoleList.size());
    }

    @Test
    void insertUserRoleSuccess() {

        User user = userDao.getUserById(1);
        Role role = roleDao.getById(2);

        UserRole userRole = userRoleDao.insert(new UserRole(user, role));

        logger.debug(userRole);
    }

    @Test
    void getInstructorsByPropertySuccess() {

        List<UserRole> instructors = userRoleDao.getInstructorsByProperty("postalCode", "53705");
        logger.debug(instructors);
    }
*/
}