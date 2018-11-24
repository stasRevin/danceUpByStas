package com.danceUpByStas.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    private Map<String, String[]> parameterMap = new HashMap<>();
    private SignUpInputValidator inputValidator = new SignUpInputValidator();

    @BeforeEach
    void populateMap() {

        parameterMap.put("role", new String[] {"instructor"});
        parameterMap.put("username", new String[] {"val"});
        parameterMap.put("firstName", new String[] {"Mike"});
        parameterMap.put("lastName", new String[] {"Boss"});
        parameterMap.put("address", new String[] {"2324 Main St"});
        parameterMap.put("city", new String[] {"Wichita"});
        parameterMap.put("state", new String[] {"KS"});
        parameterMap.put("zip", new String[] {"56789"});
        parameterMap.put("password", new String[] {"afsa"});
        parameterMap.put("passwordConfirmation", new String[] {"afsa"});
        parameterMap.put("ratePerLesson", new String[]{"0.00"});
    }

    @Test
    void runInputValidatorSuccess() {

        boolean isValidInput = inputValidator.runInputValidator(parameterMap);
        assertEquals(true, isValidInput);
    }

    @Test
    void runInputValidatorFailOnEmptyInput() {

        parameterMap.get("username")[0] = "";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);
    }

    @Test
    void runInputValidatorFailOnNonNumericZip() {

        parameterMap.get("zip")[0] = "fas";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);
    }

    @Test
    void runInputValidatorFailOnNonAlfaLastName() {

        parameterMap.get("lastName")[0] = "$2df";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);

    }


    @Test
    void runInputValidatorFailOnInvalidState() {

        parameterMap.get("state")[0] = "JJ";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);
    }

    @Test
    void runInputValidatorFailOnNonMatchingPasswords() {

        parameterMap.get("passwordConfirmation")[0] = "abc";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);
    }

    @Test
    void runInputValidatorFailOnNonMatchingRatePerLesson() {

        parameterMap.get("ratePerLesson")[0] = "$%";
        boolean isValidInput = inputValidator.runInputValidator(parameterMap);

        assertEquals(false, isValidInput);
    }

}