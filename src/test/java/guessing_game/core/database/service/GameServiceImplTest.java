package guessing_game.core.database.service;

import guessing_game.core.domain.Game;
import guessing_game.core.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GameServiceImplTest {

    private static final Player PLAYER = new Player(1L, "fifth", 6, 2);

    @Autowired
    private GameService service;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void save_shouldSave() {
        service.save(new Game(PLAYER, false));
        Optional<Game> game = service.findById(6L);
        assertTrue(game.isPresent());
        assertEquals(false, game.get().getResult());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void updateGameResult_shouldUpdate() {
        service.save(new Game(PLAYER, false));
        Optional<Game> game = service.findById(6L);
        assertTrue(game.isPresent());
        service.updateGameResult(game.get(), true);
        game = service.findById(6L);
        assertTrue(game.isPresent());
        assertEquals(true, game.get().getResult());
    }

}
