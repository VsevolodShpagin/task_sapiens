package guessing_game.core.service;

import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.SignInResponse;

public interface SignInService {

    SignInResponse execute(SignInRequest request);

}
