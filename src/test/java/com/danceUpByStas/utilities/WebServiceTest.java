package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.entity.UserLesson;
import com.danceUpByStas.persistence.GenericDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipwise.DataList;
import com.zipwise.DataListItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Web service test.
 * @author srevin
 */
public class WebServiceTest {

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        com.danceUpByStas.test.util.Database database = com.danceUpByStas.test.util.Database.getInstance();
        database.runSQL("cleanTestDb.sql");
    }

    /**
     * Gets nearby zip codes success.
     *
     * @throws Exception the exception
     */
    @Test
    void getNearbyZipCodesSuccess()  throws Exception {
        // https://www.zipwise.com/webservices/?
        Client client = ClientBuilder.newClient();
        String uri = "http://api.zip-codes.com/ZipCodesAPI.svc/1.0/FindZipCodesInRadius?zipcode=53705&maximumradius=5&minimumradius=0&key=K60YCD32JR614QCHW6Y9";

        String results = client.target(uri).request(MediaType.APPLICATION_JSON).get(String.class).trim().replaceFirst("\ufeff", "");

        ObjectMapper mapper = new ObjectMapper();
        DataList dataList = mapper.readValue(results, DataList.class);

        DataListItem item = dataList.getDataList().get(1);

        assertEquals("53726", item.getCode());

    }


    /**
     * Gets zip codes in list success.
     */
    @Test
    void getZipCodesInListSuccess() {

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);

        List<String> zipCodes = new ArrayList<>();
        zipCodes.add("53705");
        zipCodes.add("53704");

        List<Location> locations = locationDao.getElementsInList("postalCode", zipCodes);

        Set<User> users = new HashSet<>();

        for (Location location : locations) {

            users.addAll(location.getUsers());
        }

        assertEquals(2, locations.size());
        assertEquals(2, users.size());

    }
}
