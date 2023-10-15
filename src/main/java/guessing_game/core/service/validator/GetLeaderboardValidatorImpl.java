package guessing_game.core.service.validator;

import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.errorProcessor.ErrorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetLeaderboardValidatorImpl implements GetLeaderboardValidator {

    private static final String REGEX_NUMBER = "[0-9]+";

    private static final String MIN_NOT_NUMBER = "MIN_NOT_NUMBER";

    @Autowired
    private ErrorProcessor errorProcessor;

    @Override
    public List<ResponseError> validate(GetLeaderboardRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        validateIsNumber(request.getMinGameCount()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ResponseError> validateIsNumber(String number) {
        return (number != null && !number.isBlank() && !number.matches(REGEX_NUMBER))
                ? Optional.of(new ResponseError(errorProcessor.getErrorMessage(MIN_NOT_NUMBER)))
                : Optional.empty();
    }

}
