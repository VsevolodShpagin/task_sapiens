package guessing_game.core.database.service;

import guessing_game.core.domain.Game;
import guessing_game.core.domain.Guess;
import guessing_game.core.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GuessServiceImplTest {

    private static final Player PLAYER = new Player(5L, "first", 1, 1);
    private static final Game GAME = new Game(3L, PLAYER, true);

    @Autowired
    private GuessService service;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void save_shouldSave() {
        LocalDateTime creationTime = LocalDateTime.now();
        service.save(new Guess(GAME, "1234", 1, 2, creationTime));
        Optional<Guess> guess = service.findById(5L);
        assertTrue(guess.isPresent());
        assertEquals("1234", guess.get().getGuess());
        assertEquals(1, guess.get().getMatchCount());
        assertEquals(2, guess.get().getPlaceMatchCount());
        assertEquals(creationTime.truncatedTo(ChronoUnit.SECONDS), guess.get().getCreationTime().truncatedTo(ChronoUnit.SECONDS));
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void findByGame_shouldFilterAndOrder() {
        List<Guess> guesses = service.findByGame(GAME);
        assertEquals(3, guesses.size());
        assertEquals("3456", guesses.get(0).getGuess());
        assertEquals("2345", guesses.get(1).getGuess());
        assertEquals("1234", guesses.get(2).getGuess());
    }

}
