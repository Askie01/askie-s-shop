package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    private final Logger logger = LogManager.getLogger(DateValidator.class.getName());

    public boolean isValid(String dateString) {
        final String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(dateString);

        if (matcher.matches()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));

            if (isValidDate(year, month, day)) {
                logger.info("'{}' is a valid date.", dateString);
            } else {
                logger.warn("'{}' is an invalid date (logical check failed).", dateString);
            }
        } else {
            logger.warn("'{}' is an invalid date (regex check failed).", dateString);
        }

        return true;
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }

        // Check for months with less than 31 days
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }

        // Check for February
        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }

        return true;
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
}
