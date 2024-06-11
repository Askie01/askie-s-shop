package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidator {

    private final Logger log = LogManager.getLogger(UserValidator.class.getName());

    public boolean isValidLoginData(String login, String password) {
        final LoginDataValidator loginDataValidator = new LoginDataValidator();
        final boolean isValidLogin = loginDataValidator.isValidLogin(login);
        final boolean isValidPassword = loginDataValidator.isValidPassword(password);
        final boolean isValidLoginData = isValidLogin && isValidPassword;

        if (isValidLoginData) {
            log.info("Received valid login data");
        } else {
            log.info("Received invalid login data");
        }

        return isValidLoginData;
    }

    public boolean isValidPersonalData(String username, String firstName, String lastName, String email, String phone, String birthdate) {
        final PersonalDataValidator personalDataValidator = new PersonalDataValidator();
        final DateValidator dateValidator = new DateValidator();

        final boolean isValidUsername = personalDataValidator.isValidUsername(username);
        final boolean isValidFirstName = personalDataValidator.isValidFirstName(firstName);
        final boolean isValidLastName = personalDataValidator.isValidLastName(lastName);
        final boolean isValidEmail = personalDataValidator.isValidEmail(email);
        final boolean isValidPhone = personalDataValidator.isValidPhone(phone);
        final boolean isValidBirthdate = dateValidator.isValid(birthdate);
        final boolean isValidPersonalData = isValidUsername &&
                isValidFirstName &&
                isValidLastName &&
                isValidEmail &&
                isValidPhone &&
                isValidBirthdate;

        if (isValidPersonalData) {
            log.info("Received valid personal data");
        } else {
            log.warn("Received invalid personal data");
        }

        return isValidPersonalData;
    }
}
