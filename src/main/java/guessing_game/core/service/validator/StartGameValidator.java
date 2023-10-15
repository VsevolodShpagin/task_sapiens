package guessing_game.core.service.validator;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public interface StartGameValidator {

    List<ResponseError> validate(StartGameRequest request);

}
