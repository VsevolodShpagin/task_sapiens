package guessing_game.core.service.validator.errorProcessor;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class ErrorProcessorImpl implements ErrorProcessor {

    private final ResourceBundle errorMessages =
            ResourceBundle.getBundle("errorMessage");

    @Override
    public String getErrorMessage(String errorCode) {
        if (errorCode == null || !errorMessages.containsKey(errorCode)) errorCode = "GENERAL";
        return errorMessages.getString(errorCode);
    }

}
