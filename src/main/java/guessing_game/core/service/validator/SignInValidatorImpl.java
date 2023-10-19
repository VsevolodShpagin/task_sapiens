package guessing_game.core.service.validator;

import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.errorProcessor.ErrorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SignInValidatorImpl implements SignInValidator {

    private static final String ID_BLANK = "ID_BLANK";
    private static final String NAME_BLANK = "NAME_BLANK";

    @Autowired
    private ErrorProcessor errorProcessor;

    @Override
    public List<ResponseError> validate(SignInRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        validateIdIsPresent(request).ifPresent(errors::add);
        if (errors.size() == 0) validateNameIsPresent(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ResponseError> validateIdIsPresent(SignInRequest request) {
        return (request.getSessionId() == null || request.getSessionId().isBlank())
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(ID_BLANK)))
                : Optional.empty();
    }

    private Optional<ResponseError> validateNameIsPresent(SignInRequest request) {
        return (request.getName() == null || request.getName().isBlank())
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(NAME_BLANK)))
                : Optional.empty();
    }

}
