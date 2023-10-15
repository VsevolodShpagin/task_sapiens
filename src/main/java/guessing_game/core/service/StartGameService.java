package guessing_game.core.service;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.StartGameResponse;

public interface StartGameService {

    StartGameResponse execute(StartGameRequest request);

}
