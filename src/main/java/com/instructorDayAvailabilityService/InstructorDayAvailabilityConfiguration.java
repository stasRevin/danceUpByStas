package com.instructorDayAvailabilityService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the InstructorDayAvailabilityConfiguration designed to produce a set of required classes
 * to run the instructor availability service.
 * @author srevin
 */
@ApplicationPath("/schedule-services")
public class InstructorDayAvailabilityConfiguration extends Application {

    /**
     * This method returns a set of required classes to run the service.
     * @return set The set of classes.
     */
    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(InstructorDayAvailabilityService.class);

        return set;
    }

}
