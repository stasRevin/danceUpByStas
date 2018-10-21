package com.danceDescriptionService;

import com.danceUpByStas.entity.Dance;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class DanceDescriptionServiceTest {

    @Test
    void getDanceDescriptionSuccess() {

        DanceDescriptionService service = new DanceDescriptionService();

        Response response = service.getDanceDescription("Rumba");

        Dance dance = (Dance)response.getEntity();

        assertEquals("romantic, slow, easy", dance.getDescription());

    }
}