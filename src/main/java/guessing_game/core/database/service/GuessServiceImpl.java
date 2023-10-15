package guessing_game.core.database.service;

import guessing_game.core.converter.GuessConverter;
import guessing_game.core.database.repository.GameRepository;
import guessing_game.core.database.repository.GuessRepository;
import guessing_game.core.domain.Game;
import guessing_game.core.domain.Guess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GuessServiceImpl implements GuessService {

    @Autowired
    private GuessRepository guessRepository;
    @Autowired
    private GuessConverter guessConverter;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Guess save(Guess guess) {
        return guessConverter.toGuess(guessRepository.save(guessConverter.toGuessEntity(guess)));
    }

    @Override
    public List<Guess> findByGame(Game game) {
        return guessRepository.findByGame(gameRepository.findById(game.getId()).orElseThrow())
                .stream().map(guessConverter::toGuess).toList();
    }

    @Override
    public Optional<Guess> findById(Long id) {
        return guessRepository.findById(id).map(guessConverter::toGuess);
    }
}
