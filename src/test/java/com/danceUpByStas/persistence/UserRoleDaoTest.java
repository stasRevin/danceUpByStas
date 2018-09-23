package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDaoTest {

    private UserRoleDao userRoleDao;
    @BeforeEach
    void setUp() {
        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.userRoleDao = new UserRoleDao();
    }

    @Test
    void getAllUserRoles() {

        List<UserRole> userRoleList = userRoleDao.getAllUserRoles();
        assertEquals(2, userRoleList.size());
    }


}