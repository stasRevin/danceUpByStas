package com.danceUpByStas.utilities;

import com.danceUpByStas.entity.Location;
import com.danceUpByStas.entity.User;
import com.danceUpByStas.persistence.GenericDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipwise.DataList;
import com.zipwise.DataListItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.*;

/**
 * This is the ZipCodeRadius class designed to consume the web service that finds zip codes withing a given
 * radius from a given zip code.
 * @author srevin
 */
public class ZipCodeRadius implements PropertiesLoader {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This method returns a set of instructors who teach withing the given radius from
     * the given zip code.
     * @param zipCode The zip code.
     * @param radius The radius.
     * @return userSet The set of instructors found.
     */
    public Set<User> getInstructorsWhoTeachNearMe(String zipCode, String radius) {

        GenericDao<Location> locationDao = new GenericDao<>(Location.class);
        // get list of nearby zip codes
        List<String> zipCodes = getListOfNearbyZipCodes(zipCode, radius);

        if (Objects.isNull(zipCodes))
            return null;
        //call generic dao to get locations within those zip codes
        List<Location> locations = locationDao.getElementsInList("postalCode", zipCodes);
        //get users who teach at those locations
        Set<User> userSet = getUsersWhoTeachAtLocations(locations);

        return userSet;
    }

    /**
     * This method returns the list of zip codes located within the given radius from the given zip code.
     * @param zipCode The given zip code.
     * @param radius The given radius.
     * @return zipCodes. The list of zip codes found.
     */
    private List<String> getListOfNearbyZipCodes(String zipCode, String radius) {

        Properties properties = loadProperties("/zipCodeRadius.properties");

        Client client = ClientBuilder.newClient();
        String baseURI = properties.getProperty("uri");
        String uri = baseURI + "?zipcode=" + zipCode + "&maxmaximumradius=" + radius
                   + "&minminimumradius=" + "0&key=" + properties.getProperty("key");
        String results = "";
        ObjectMapper mapper = new ObjectMapper();
        DataList dataListObject = null;

        try {
            results = client.target(uri)
                                   .request(MediaType.APPLICATION_JSON_TYPE)
                                   .get(String.class)
                                   .trim()
                                   .replaceFirst("\ufeff", "");
        } catch (Exception exception) {

            logger.debug("The URI {} is not responding.", uri);
            return null;
        }

        logger.debug("Getting list of nearby zip codes, zipCode: {}, radius: {}", zipCode, radius);

        try {

            dataListObject = mapper.readValue(results, DataList.class);

        } catch (JsonParseException jsonParseException) {

            logger.debug("json parse exception: {}", jsonParseException);

        } catch (JsonMappingException jsonMappingException) {

            logger.debug("json mapping exception: {}", jsonMappingException);

        } catch (IOException inputOutputException) {

            logger.debug("input output exception: {}", inputOutputException);

        } catch (Exception exception) {

            logger.debug("an exception has occurred: {}", exception);
        }

        List<String> zipCodes = null;

        if (!Objects.isNull(dataListObject)) {

            zipCodes = getZipCodesAsStrings(dataListObject.getDataList());
        }

        return zipCodes;
    }

    /**
     * This method returns a list of zip codes as strings.
     * @param dataList The list of data list items.
     * @return zipCodes The list of zip codes as strings.
     */
    private List<String> getZipCodesAsStrings(List<DataListItem> dataList) {

        List<String> zipCodes = new ArrayList<>();

        for (DataListItem item : dataList) {

            zipCodes.add(item.getCode());

        }

        return zipCodes;
    }

    /**
     * This method returns a set of users who teach at the specified locations.
     * @param locations The list of specified locations.
     * @return userSet The set of users.
     */
    private Set<User> getUsersWhoTeachAtLocations(List<Location> locations) {

        Set<User> userSet = new HashSet<>();

        for (Location location : locations) {

            userSet.addAll(location.getUsers());

        }
        return userSet;
    }
}
