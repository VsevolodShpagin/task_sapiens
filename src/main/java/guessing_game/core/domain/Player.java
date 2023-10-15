package guessing_game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Player {

    private Long id;
    private final String name;
    private final Integer totalGames;
    private final Integer totalWins;

    public Player(String name) {
        this.name = name;
        this.totalGames = 0;
        this.totalWins = 0;
    }
}
