package guessing_game.core.service.validator;

import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public interface SignInValidator {

    List<ResponseError> validate(SignInRequest request);

}
