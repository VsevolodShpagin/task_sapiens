package guessing_game.core.domain;

import lombok.Data;

@Data
public class GuessResult {

    private final int match;
    private final int placeMatch;

}
