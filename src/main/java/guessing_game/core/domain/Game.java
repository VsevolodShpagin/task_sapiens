package guessing_game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    private Long id;
    private final Player player;
    private Boolean result;

    public Game(Player player, Boolean result) {
        this.player = player;
        this.result = result;
    }

}
