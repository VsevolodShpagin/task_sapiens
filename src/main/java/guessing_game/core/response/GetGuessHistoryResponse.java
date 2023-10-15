package guessing_game.core.response;

import guessing_game.core.domain.Guess;
import guessing_game.core.response.shared.Response;
import guessing_game.core.response.shared.ResponseError;
import lombok.Getter;

import java.util.List;

@Getter
public class GetGuessHistoryResponse extends Response {

    private final List<Guess> guesses;

    public GetGuessHistoryResponse(List<Guess> guesses, List<ResponseError> errors) {
        super(errors);
        this.guesses = guesses;
    }

}
