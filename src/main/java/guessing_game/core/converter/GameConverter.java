package guessing_game.core.converter;

import guessing_game.core.database.entity.GameEntity;
import guessing_game.core.domain.Game;

public interface GameConverter {

    GameEntity toGameEntity(Game game);

    Game toGame(GameEntity gameEntity);

}
