package com.danceDescriptionService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class DanceDescriptionConfiguration extends Application {


    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(DanceDescriptionService.class);

        return set;
    }
}
