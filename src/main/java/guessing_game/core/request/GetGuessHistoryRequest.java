package guessing_game.core.request;

import lombok.Data;

@Data
public class GetGuessHistoryRequest {

    private final String sessionId;

}
