package guessing_game.core.response.shared;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Response {

    private List<ResponseError> errors;

    public Response() {
    }

    public Response(List<ResponseError> errors) {
        this.errors = errors;
    }

}
