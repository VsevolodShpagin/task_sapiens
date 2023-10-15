package guessing_game.core.service;

import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.response.MakeGuessResponse;

public interface MakeGuessService {

    MakeGuessResponse execute(MakeGuessRequest request);

}
