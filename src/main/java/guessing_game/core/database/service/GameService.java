package guessing_game.core.database.service;

import guessing_game.core.domain.Game;

import java.util.Optional;

public interface GameService {

    Game save(Game game);

    void updateGameResult(Game game, boolean result);

    Optional<Game> findById(Long id);

}
