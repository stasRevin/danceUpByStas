package com.danceUpByStas.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipwise.DataList;
import com.zipwise.DataListItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebServiceTest {

    Logger logger = LogManager.getLogger(this.getClass());
/*
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
*/
}
