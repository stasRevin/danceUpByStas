package com.danceUpByStas.utilities;

import java.util.*;
import java.util.stream.Collectors;

public class InputValidator implements PropertiesLoader {

    //public abstract boolean runInputValidator(Map<String, String[]> parameterMap);

    protected Set<String> filterSet(Set<String> setToFilter, List<String> filterCriteria) {

        return setToFilter.stream()
                          .filter(x -> filterCriteria.contains(x))
                          .collect(Collectors.toSet());
    }

    protected boolean validateMatchesRegularExpression(String input, String regularExpession) {

        if (input.matches(regularExpession)) {

            return true;
        }
        return false;
    }

    protected boolean checkIfInputIsValid(Map<String, String[]> parameterMap, Set<String> requiredKeys,
                                        String regularExpression) {

        for (String requiredKey : requiredKeys) {

            String[] values = parameterMap.get(requiredKey);

            if (Objects.isNull(values[0])
                    || !validateMatchesRegularExpression(values[0], regularExpression)) {

                return false;
            }
        }
        return true;
    }

}
