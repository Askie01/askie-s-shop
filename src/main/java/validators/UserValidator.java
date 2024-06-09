package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserValidator {

    private final Logger logger = LogManager.getLogger(UserValidator.class.getName());

    public boolean isValid(User user) {
        final boolean hasValidUsername = isValidContent(user.getUsername());
        final boolean hasValidPassword = isValidContent(user.getPassword());
        final boolean hasValidFirstName = isValidContent(user.getFirstName());
        final boolean hasValidLastName = isValidContent(user.getLastName());
        final boolean hasValidBirthdate = isValidBirthday(user.getBirthdate());
        final boolean hasValidEmail = isValidEmail(user.getEmail());
        final boolean hasValidPhone = isValidPhone(user.getPhone());

        return hasValidUsername &&
                hasValidPassword &&
                hasValidFirstName &&
                hasValidLastName &&
                hasValidEmail &&
                hasValidBirthdate &&
                hasValidPhone;
    }

    private boolean isValidContent(String content) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean isValid = Pattern.matches(regex, content);

        logValidation(content, isValid);
        return isValid;
    }

    private boolean isValidEmail(String email) {
        final String regex = "^[a-zA-Z0-9._-]{3,40}@[a-zA-Z.]{2,15}$";
        final boolean isValid = Pattern.matches(regex, email);

        logValidation(email, isValid);
        return isValid;
    }

    private boolean isValidPhone(String phone) {
        final String regex = "^[0-9]{9}$";
        final boolean isValid = Pattern.matches(regex, phone);

        logValidation(phone, isValid);
        return isValid;
    }

    private boolean isValidBirthday(LocalDate birthday) {
        final String birthdayString = birthday.toString();
        final DateValidator dateValidator = new DateValidator();
        final boolean isValid = dateValidator.isValid(birthdayString);
        logValidation(birthdayString, isValid);
        return isValid;
    }

    private void logValidation(String data, boolean isValid) {
        if (isValid) {
            logger.info("'{}' is valid data.", data);
        } else {
            logger.warn("'{}' is an invalid data.", data);
        }
    }
}
