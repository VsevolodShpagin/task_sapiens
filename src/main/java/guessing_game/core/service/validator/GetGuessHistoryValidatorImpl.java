package guessing_game.core.service.validator;

import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.shared.ResponseError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetGuessHistoryValidatorImpl implements GetGuessHistoryValidator {

    @Override
    public List<ResponseError> validate(GetGuessHistoryRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        return errors;
    }

}
