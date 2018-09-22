package com.danceUpByStas.persistence;

import com.danceUpByStas.entity.Dance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DanceDaoTest {

    private DanceDao danceDao;

    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
        this.danceDao = new DanceDao();

    }

    @Test
    void insertSuccess() {

        Dance dance = new Dance("Cha Cha", "fun, upbeat, flirtatious");
        int id = danceDao.insert(dance);
        assertEquals(4, id);
    }

    @Test
    void getAllSuccess() {

        List<Dance> danceList = danceDao.getAllDances();
        assertEquals(3, danceList.size());

    }

    @Test
    void getDanceByIdSuccess() {

        Dance dance = danceDao.getDanceById(1);
        assertEquals("Waltz", dance.getName());

    }

    @Test
    void saveOrUpdateDanceSuccess() {

        Dance danceToUpdate = danceDao.getDanceById(1);
        danceToUpdate.setDescription("the best dance");
        danceDao.saveOrUpdate(danceToUpdate);
        Dance dance = danceDao.getDanceById(1);
        assertEquals("the best dance", dance.getDescription());

    }

    @Test
    void deleteDanceSuccess() {

        Dance dance = danceDao.getDanceById(1);
        danceDao.deleteDance(dance);
        assertNull(danceDao.getDanceById(1));

    }
}