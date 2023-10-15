package guessing_game.core.response;

import guessing_game.core.domain.Player;
import guessing_game.core.response.shared.Response;
import guessing_game.core.response.shared.ResponseError;
import lombok.Getter;

import java.util.List;

@Getter
public class GetLeaderboardResponse extends Response {

    private final List<Player> players;

    public GetLeaderboardResponse(List<Player> players, List<ResponseError> errors) {
        super(errors);
        this.players = players;
    }

}
