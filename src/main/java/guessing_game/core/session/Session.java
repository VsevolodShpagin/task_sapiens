package guessing_game.core.session;

import guessing_game.core.domain.Game;
import guessing_game.core.domain.Player;
import lombok.Data;

@Data
public class Session {

    private Player player;
    private Game game;
    private String number;
    private int attemptsLeft;

}
