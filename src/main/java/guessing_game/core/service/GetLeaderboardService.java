package guessing_game.core.service;

import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.GetLeaderboardResponse;

public interface GetLeaderboardService {

    GetLeaderboardResponse execute(GetLeaderboardRequest request);

}
