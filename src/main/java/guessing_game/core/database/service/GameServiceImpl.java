package guessing_game.core.database.service;

import guessing_game.core.converter.GameConverter;
import guessing_game.core.database.repository.GameRepository;
import guessing_game.core.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameConverter gameConverter;

    @Override
    public Game save(Game game) {
        return gameConverter.toGame(gameRepository.save(gameConverter.toGameEntity(game)));
    }

    @Override
    public void updateGameResult(Game game, boolean result) {
        gameRepository.updateGameResult(game.getId(), result);
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id).map(gameConverter::toGame);
    }
}
