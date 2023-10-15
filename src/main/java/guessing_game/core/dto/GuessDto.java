package guessing_game.core.dto;

import lombok.Data;

@Data
public class GuessDto {

    private final String guess;
    private final int matchCount;
    private final int placeMatchCount;

}
