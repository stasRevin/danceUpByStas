package com.danceDescriptionService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the DanceDescriptioConfiguration class designed to provide a set of service classes.
 * @author srevin
 */
@ApplicationPath("/services")
public class DanceDescriptionConfiguration extends Application {

    /**
     * This method returns a set of service classes.
     * @return set The set of service classes.
     */
    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(DanceDescriptionService.class);

        return set;
    }
}
