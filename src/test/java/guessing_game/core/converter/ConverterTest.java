package guessing_game.core.converter;

import guessing_game.core.database.entity.GameEntity;
import guessing_game.core.database.entity.GuessEntity;
import guessing_game.core.database.entity.PlayerEntity;
import guessing_game.core.domain.Game;
import guessing_game.core.domain.Guess;
import guessing_game.core.domain.Player;
import guessing_game.core.dto.GuessDto;
import guessing_game.core.dto.NumberDto;
import guessing_game.core.dto.PlayerDto;

import java.time.LocalDateTime;

public abstract class ConverterTest {

    private static final LocalDateTime CREATION_TIME = LocalDateTime.now();

    protected static final PlayerEntity PLAYER_ENTITY = new PlayerEntity(1L, "name", 4, 1);
    protected static final Player PLAYER = new Player(1L, "name", 4, 1);
    protected static final PlayerDto PLAYER_DTO = new PlayerDto("name", 4, 25);

    protected static final GameEntity GAME_ENTITY = new GameEntity(1L, PLAYER_ENTITY, false);
    protected static final Game GAME = new Game(1L, PLAYER, false);

    protected static final GuessEntity GUESS_ENTITY = new GuessEntity(1L, GAME_ENTITY, "1234", 2, 1, CREATION_TIME);
    protected static final Guess GUESS = new Guess(1L, GAME, "1234", 2, 1, CREATION_TIME);
    protected static final GuessDto GUESS_DTO = new GuessDto("1234", 2, 1);

    protected static final NumberDto NUMBER_DTO = new NumberDto(1, 2, 3, 4);
    protected static final String NUMBER_STRING = "1234";

}
