package guessing_game.core.database.service;

import guessing_game.core.domain.Game;
import guessing_game.core.domain.Guess;

import java.util.List;
import java.util.Optional;

public interface GuessService {

    Guess save(Guess guess);

    List<Guess> findByGame(Game game);

    Optional<Guess> findById(Long id);

}
