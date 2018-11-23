package com.danceUpByStas.utilities;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InputValidator {

    public boolean runInputValidator(Map<String, String[]> parameterMap) {

        Set<String> generalInput = parameterMap.keySet();
        Set<String> commonInput = filterSet(generalInput, Arrays.asList("role", "username", "firstName", "lastName",
                "address", "city", "state", "zip", "password", "passwordConfirmation"));
        if (!checkIfInputIsValid(parameterMap, commonInput, ".+"))
            return false;

        Set<String> numericInput = filterSet(generalInput, Arrays.asList("zip"));
        if (!checkIfInputIsValid(parameterMap, numericInput, "^\\d{1,20}$"))
            return false;

        Set<String> alphaInput = filterSet(generalInput, Arrays.asList("firstName", "lastName", "city", "state"));
        if (!checkIfInputIsValid(parameterMap, alphaInput, "^[A-Za-z]+$" ))
            return false;

        Set<String> stateInput = filterSet(generalInput, Arrays.asList("state"));
        // Regex to validate states was found here https://gist.github.com/PhazeonPhoenix/2008449
        if (!checkIfInputIsValid(parameterMap, stateInput, "^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$"))
            return false;

        if (!parameterMap.get("password")[0].equals(parameterMap.get("passwordConfirmation")[0]))
            return false;

        return true;
    }

    private boolean keyIsARequiredKey(String currentKey, String requiredKey) {

        if (currentKey.equals(requiredKey)) {

            return true;
        }
        return false;
    }

    private Set<String> filterSet(Set<String> setToFilter, List<String> filterCriteria) {

        return setToFilter.stream()
                          .filter(x -> filterCriteria.contains(x))
                          .collect(Collectors.toSet());
    }

    private boolean validateMatchesRegularExpression(String input, String regularExpession) {

        if (input.matches(regularExpession)) {

            return true;
        }
        return false;
    }

    private boolean checkIfInputIsValid(Map<String, String[]> parameterMap, Set<String> requiredKeys,
                                        String regularExpression) {

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {

            for (String requiredKey : requiredKeys) {

                if (keyIsARequiredKey(entry.getKey(), requiredKey)) {

                    if (Objects.isNull(entry.getValue()[0])
                            || !validateMatchesRegularExpression(entry.getValue()[0], regularExpression)) {

                        return false;
                    }
                }
            }
        }
        return true;
    }

}
