package guessing_game.core.response;

import guessing_game.core.response.shared.Response;
import guessing_game.core.response.shared.ResponseError;
import lombok.Getter;

import java.util.List;

@Getter
public class MakeGuessResponse extends Response {

    public MakeGuessResponse() {
    }

    public MakeGuessResponse(List<ResponseError> errors) {
        super(errors);
    }

}
