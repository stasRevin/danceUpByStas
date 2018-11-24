package com.inputValidationService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/inputValidationServices")
public class InputValidationConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        HashSet set = new HashSet<Class<?>>();
        set.add(InputValidationService.class);

        return set;
    }

}
