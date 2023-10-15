package guessing_game.core.service.validator;

import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public interface GetLeaderboardValidator {

    List<ResponseError> validate(GetLeaderboardRequest request);

}
