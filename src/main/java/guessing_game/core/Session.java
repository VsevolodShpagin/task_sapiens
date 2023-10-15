package guessing_game.core;

import guessing_game.core.domain.Game;
import guessing_game.core.domain.Player;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Session {

    private Player player;
    private Game game;
    private String number;
    private int attemptsLeft;

}
