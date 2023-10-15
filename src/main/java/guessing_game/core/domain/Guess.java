package guessing_game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Guess {

    private Long id;
    private final Game game;
    private final String guess;
    private final Integer matchCount;
    private final Integer placeMatchCount;
    private final LocalDateTime creationTime;

}
