package com.inputValidationService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the InputValidationConfiguration class designed to produce a set of required classes
 * to run the input validation service.
 * @author srevin
 */
@ApplicationPath("/input-validation")
public class InputValidationConfiguration extends Application {

    /**
     * This method returns a set of required classes to run the service.
     * @return set The set of classes.
     */
    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(InputValidationService.class);

        return set;
    }

}
