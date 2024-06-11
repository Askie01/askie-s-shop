package validators;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class DataValidatorLogger {
    private final Logger log;

    public void logDataValidation(String data, boolean isValid) {
        if (isValid) {
            log.info("Valid data: '{}'", data);
        } else {
            log.warn("Invalid data: '{}'", data);
        }
    }
}
