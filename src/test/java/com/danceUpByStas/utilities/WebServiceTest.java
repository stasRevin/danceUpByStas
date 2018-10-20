package com.danceUpByStas.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WebServiceTest {

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void getNearbyZipCodesSuccess() {
        // https://www.zipwise.com/webservices/?
        Client client = ClientBuilder.newClient();
        String uri = "https://www.zipwise.com/webservices/radius.php?key=ehwjedqpjnm7xdsc&zip=90210&radius=2&format=json";

        Map<String, String> jsonResultMap = client.target(uri).request(MediaType.APPLICATION_JSON).get(LinkedHashMap.class);

        List locations = (List) new ArrayList(jsonResultMap.values()).get(0);

        LinkedHashMap<String, String> firstLocation = (LinkedHashMap<String, String>)locations.get(0);
        String zipCode = firstLocation.get("zip");

        client.close();

        assertEquals("90210", zipCode);

    }

}
