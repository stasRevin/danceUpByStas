package com.danceUpByStas.utilities;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SignUpInputValidator extends InputValidator {

    public boolean runInputValidator(Map<String, String[]> parameterMap) {

        Properties properties = loadProperties("/regexValidation.properties");
        Set<String> generalInput = parameterMap.keySet();
        Set<String> commonInput = filterSet(generalInput, Arrays.asList("role", "username", "firstName", "lastName",
                "address", "city", "state", "zip", "password", "passwordConfirmation"));
        if (!checkIfInputIsValid(parameterMap, commonInput, ".+"))
            return false;

        Set<String> numericInput = filterSet(generalInput, Arrays.asList("zip"));
        if (!checkIfInputIsValid(parameterMap, numericInput, properties.getProperty("regex.zipcode")))
            return false;

        Set<String> alphaInput = filterSet(generalInput, Arrays.asList("firstName", "lastName", "city", "state"));
        if (!checkIfInputIsValid(parameterMap, alphaInput, properties.getProperty("regex.alpha")))
            return false;

        Set<String> doubleInput = filterSet(generalInput, Arrays.asList("ratePerLesson"));
        if (!checkIfInputIsValid(parameterMap, doubleInput, properties.getProperty("regex.double")))
            return false;

        Set<String> stateInput = filterSet(generalInput, Arrays.asList("state"));
        // Regex to validate states was found here https://gist.github.com/PhazeonPhoenix/2008449
        if (!checkIfInputIsValid(parameterMap, stateInput, properties.getProperty("regex.state")))
            return false;

        if (!parameterMap.get("password")[0].equals(parameterMap.get("passwordConfirmation")[0]))
            return false;

        return true;
    }
}
