package guessing_game.core.response;

import guessing_game.core.response.shared.Response;
import guessing_game.core.response.shared.ResponseError;
import lombok.Getter;

import java.util.List;

@Getter
public class SignInResponse extends Response {

    private String playerName;

    public SignInResponse(String playerName) {
        this.playerName = playerName;
    }

    public SignInResponse(List<ResponseError> errors) {
        super(errors);
    }

}
