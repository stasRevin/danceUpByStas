package com.danceUpByStas.utilities;

import java.util.*;
import java.util.stream.Collectors;

public abstract class InputValidator {

    public abstract boolean runInputValidator(Map<String, String[]> parameterMap);

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

    protected String getZipRegex() {

        return "^\\d{1,20}$";
    }

    protected String getAlphaInputRegex() {

        return "^[A-Za-z]+$";
    }

    protected String getDoubleTypeRegex() {

        return "^\\d{1,3}.\\d{2}$";
    }

    protected String getStateRegex() {

        return "^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$";
    }

}
