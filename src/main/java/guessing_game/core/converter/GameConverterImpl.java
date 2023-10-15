package guessing_game.core.converter;

import guessing_game.core.database.entity.GameEntity;
import guessing_game.core.database.repository.PlayerRepository;
import guessing_game.core.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameConverterImpl implements GameConverter {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerConverter playerConverter;

    @Override
    public GameEntity toGameEntity(Game game) {
        return new GameEntity(
                game.getId(),
                playerRepository.findById(game.getPlayer().getId()).orElseThrow(),
                game.getResult()
        );
    }

    @Override
    public Game toGame(GameEntity gameEntity) {
        return new Game(
                gameEntity.getId(),
                playerConverter.toPlayer(gameEntity.getPlayer()),
                gameEntity.getResult()
        );
    }

}
