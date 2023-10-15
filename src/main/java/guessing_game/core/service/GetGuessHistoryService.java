package guessing_game.core.service;

import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.GetGuessHistoryResponse;

public interface GetGuessHistoryService {

    GetGuessHistoryResponse execute(GetGuessHistoryRequest request);

}
