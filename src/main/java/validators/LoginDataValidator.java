package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class LoginDataValidator {

    private final Logger log = LogManager.getLogger(LoginDataValidator.class.getName());
    private final DataValidatorLogger validatorLog = new DataValidatorLogger(log);

    public boolean isValidLogin(String login) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean isValid = Pattern.matches(regex, login);

        validatorLog.logDataValidation(login, isValid);
        return isValid;
    }

    public boolean isValidPassword(String password) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean isValid = Pattern.matches(regex, password);

        validatorLog.logDataValidation(password, isValid);
        return isValid;
    }
}