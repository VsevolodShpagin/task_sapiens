package guessing_game.core.service.validator;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.shared.ResponseError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartGameValidatorImpl implements StartGameValidator {

    @Override
    public List<ResponseError> validate(StartGameRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        return errors;
    }

}
