package guessing_game.core.converter;

import guessing_game.core.database.entity.GuessEntity;
import guessing_game.core.database.repository.GameRepository;
import guessing_game.core.domain.Guess;
import guessing_game.core.dto.GuessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuessConverterImpl implements GuessConverter {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameConverter gameConverter;

    @Override
    public GuessEntity toGuessEntity(Guess guess) {
        return new GuessEntity(
                guess.getId(),
                gameRepository.findById(guess.getGame().getId()).orElseThrow(),
                guess.getGuess(),
                guess.getMatchCount(),
                guess.getPlaceMatchCount(),
                guess.getCreationTime()
        );
    }

    @Override
    public Guess toGuess(GuessEntity guessEntity) {
        return new Guess(
                guessEntity.getId(),
                gameConverter.toGame(guessEntity.getGame()),
                guessEntity.getGuess(),
                guessEntity.getMatchCount(),
                guessEntity.getPlaceMatchCount(),
                guessEntity.getCreationTime()
        );
    }

    @Override
    public GuessDto toGuessDto(Guess guess) {
        return new GuessDto(
                guess.getGuess(),
                guess.getMatchCount(),
                guess.getPlaceMatchCount()
        );
    }

}
