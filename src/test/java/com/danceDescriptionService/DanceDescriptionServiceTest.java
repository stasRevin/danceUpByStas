package com.danceDescriptionService;

import com.danceUpByStas.entity.Dance;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.persistence.GenericDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class DanceDescriptionServiceTest {

    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    /*
    @Test
    void getDanceDescriptionSuccess() {

        DanceDescriptionService service = new DanceDescriptionService();

       // Response response = service.getDanceDescription("Rumba");

        Dance dance = (Dance)response.getEntity();

        assertEquals("romantic, slow, easy", dance.getDescription());

    }
    */
}