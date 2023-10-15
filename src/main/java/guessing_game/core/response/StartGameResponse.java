package guessing_game.core.response;

import guessing_game.core.response.shared.Response;
import guessing_game.core.response.shared.ResponseError;

import java.util.List;

public class StartGameResponse extends Response {

    public StartGameResponse() {
    }

    public StartGameResponse(List<ResponseError> errors) {
        super(errors);
    }

}
