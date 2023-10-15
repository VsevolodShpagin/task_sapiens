package guessing_game.core.service.validator;

import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public interface MakeGuessValidator {

    List<ResponseError> validate(MakeGuessRequest request);

}
