package com.askie01.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class PersonalDataValidator {

    private final Logger log = LogManager.getLogger(PersonalDataValidator.class.getName());
    private final DataValidatorLogger validatorLog = new DataValidatorLogger(log);

    public boolean isValidUsername(String username) {
        final String regex = "^[a-zA-Z0-9](_?[a-zA-Z0-9]){2,19}$";
        final boolean isValid = Pattern.matches(regex, username);

        validatorLog.logDataValidation(username, isValid);
        return isValid;
    }

    public boolean isValidFirstName(String firstName) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean isValid = Pattern.matches(regex, firstName);

        validatorLog.logDataValidation(firstName, isValid);
        return isValid;
    }

    public boolean isValidLastName(String lastName) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean isValid = Pattern.matches(regex, lastName);

        validatorLog.logDataValidation(lastName, isValid);
        return isValid;
    }

    public boolean isValidEmail(String email) {
        final String regex = "^[a-zA-Z0-9._-]{3,40}@[a-zA-Z.]{2,15}$";
        final boolean isValid = Pattern.matches(regex, email);

        validatorLog.logDataValidation(email, isValid);
        return isValid;
    }

    public boolean isValidPhone(String phone) {
        final String regex = "^[0-9]{9}$";
        final boolean isValid = Pattern.matches(regex, phone);

        validatorLog.logDataValidation(phone, isValid);
        return isValid;
    }
}