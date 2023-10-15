package guessing_game.core.converter;

import guessing_game.core.database.entity.GuessEntity;
import guessing_game.core.domain.Guess;
import guessing_game.core.dto.GuessDto;

public interface GuessConverter {

    GuessEntity toGuessEntity(Guess guess);

    Guess toGuess(GuessEntity guessEntity);

    GuessDto toGuessDto(Guess guess);

}
