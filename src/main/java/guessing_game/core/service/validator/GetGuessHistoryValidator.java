package guessing_game.core.service.validator;

import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public interface GetGuessHistoryValidator {

    List<ResponseError> validate(GetGuessHistoryRequest request);

}
