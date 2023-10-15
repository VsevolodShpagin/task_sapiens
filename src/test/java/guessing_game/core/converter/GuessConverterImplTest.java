package guessing_game.core.converter;

import guessing_game.core.database.entity.GuessEntity;
import guessing_game.core.database.repository.GameRepository;
import guessing_game.core.domain.Guess;
import guessing_game.core.dto.GuessDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuessConverterImplTest extends ConverterTest {

    @Mock
    private GameRepository mockGameRepository;
    @Mock
    private GameConverter mockGameConverter;

    @InjectMocks
    private GuessConverterImpl guessConverter;

    @Test
    void toGuessEntity_convertDomain_getEntity() {
        when(mockGameRepository.findById(GAME.getId())).thenReturn(Optional.of(GAME_ENTITY));
        GuessEntity result = guessConverter.toGuessEntity(GUESS);
        assertEquals(GUESS_ENTITY.getId(), result.getId());
        assertEquals(GUESS_ENTITY.getGame(), result.getGame());
        assertEquals(GUESS_ENTITY.getGuess(), result.getGuess());
        assertEquals(GUESS_ENTITY.getMatchCount(), result.getMatchCount());
        assertEquals(GUESS_ENTITY.getPlaceMatchCount(), result.getPlaceMatchCount());
        assertEquals(GUESS_ENTITY.getCreationTime(), result.getCreationTime());
        result.setId(1L);
        assertEquals(GUESS_ENTITY, result);
    }

    @Test
    void toGuess_convertEntity_getDomain() {
        when(mockGameConverter.toGame(GAME_ENTITY)).thenReturn(GAME);
        Guess result = guessConverter.toGuess(GUESS_ENTITY);
        assertEquals(GUESS.getId(), result.getId());
        assertEquals(GUESS.getGame(), result.getGame());
        assertEquals(GUESS.getGuess(), result.getGuess());
        assertEquals(GUESS.getMatchCount(), result.getMatchCount());
        assertEquals(GUESS.getPlaceMatchCount(), result.getPlaceMatchCount());
        assertEquals(GUESS.getCreationTime(), result.getCreationTime());
        assertEquals(GUESS, result);
    }

    @Test
    void toGuessDto_convertDomain_getDto() {
        GuessDto result = guessConverter.toGuessDto(GUESS);
        assertEquals(GUESS_DTO.getGuess(), result.getGuess());
        assertEquals(GUESS_DTO.getMatchCount(), result.getMatchCount());
        assertEquals(GUESS_DTO.getPlaceMatchCount(), result.getPlaceMatchCount());
        assertEquals(GUESS_DTO, result);
    }

}
