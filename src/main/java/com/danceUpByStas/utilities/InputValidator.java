package com.danceUpByStas.utilities;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is the InputValidator class designed to validate user input.
 * @author srevin
 */

public class InputValidator implements PropertiesLoader {

    /**
     * This method filters the specified set against the specified criteria.
     * @param setToFilter The set to be filtered.
     * @param filterCriteria The specified filter criteria.
     * @return filteredSet The set containing only filtered elements.
     */
    protected Set<String> filterSet(Set<String> setToFilter, List<String> filterCriteria) {

        return setToFilter.stream()
                          .filter(x -> filterCriteria.contains(x))
                          .collect(Collectors.toSet());
    }

    /**
     * This method validates whether the given input matches the given regulare expression.
     * @param input The given input.
     * @param regularExpression The given regulare expression.
     * @return true/false The result of the validation.
     */
    public boolean validateMatchesRegularExpression(String input, String regularExpression) {

        if (input.matches(regularExpression)) {

            return true;
        }
        return false;
    }

    /**
     * This method checks whether the given input is valid.
     * @param parameterMap The map of parameters.
     * @param requiredKeys The set of required keys.
     * @param regularExpression The given regular expression.
     * @return true/false The result of the validation.
     */
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
