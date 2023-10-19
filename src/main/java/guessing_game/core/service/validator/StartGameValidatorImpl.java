package guessing_game.core.service.validator;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.errorProcessor.ErrorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StartGameValidatorImpl implements StartGameValidator {

    private static final String ID_BLANK = "ID_BLANK";

    @Autowired
    private ErrorProcessor errorProcessor;

    @Override
    public List<ResponseError> validate(StartGameRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        validateIdIsPresent(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ResponseError> validateIdIsPresent(StartGameRequest request) {
        return (request.getSessionId() == null || request.getSessionId().isBlank())
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(ID_BLANK)))
                : Optional.empty();
    }

}
