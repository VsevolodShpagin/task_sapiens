package guessing_game.core.service;

import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Player;
import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.GetLeaderboardResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.GetLeaderboardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetLeaderboardServiceImpl implements GetLeaderboardService {

    @Autowired
    private GetLeaderboardValidator validator;
    @Autowired
    private PlayerService playerService;

    @Override
    public GetLeaderboardResponse execute(GetLeaderboardRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new GetLeaderboardResponse(null, errors);
        List<Player> players = isWithMinCount(request)
                ? playerService.findByTotalGamesOrderByWinRate(Integer.parseInt(request.getMinGameCount()))
                : playerService.findAllOrderByWinRate();
        return new GetLeaderboardResponse(players, null);
    }

    private boolean isWithMinCount(GetLeaderboardRequest request) {
        return request.getMinGameCount() != null && !request.getMinGameCount().isBlank();
    }

}
