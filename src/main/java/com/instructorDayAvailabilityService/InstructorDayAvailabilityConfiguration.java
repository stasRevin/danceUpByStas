package com.instructorDayAvailabilityService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/scheduleServices")
public class InstructorDayAvailabilityConfiguration extends Application {


    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(InstructorDayAvailabilityService.class);

        return set;
    }

}
