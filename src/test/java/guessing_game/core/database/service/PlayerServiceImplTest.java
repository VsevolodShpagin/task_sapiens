package guessing_game.core.database.service;

import guessing_game.core.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PlayerServiceImplTest {

    @Autowired
    private PlayerService service;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void save_shouldSave() {
        service.save(new Player("new player"));
        Optional<Player> player = service.findFirstByName("new player");
        assertTrue(player.isPresent());
        assertEquals(0, player.get().getTotalGames());
        assertEquals(0, player.get().getTotalWins());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void findAllOrderByWinRate_shouldOrder() {
        List<Player> players = service.findAllOrderByWinRate();
        assertEquals(7, players.size());
        assertEquals("first", players.get(0).getName());
        assertEquals("second (first)", players.get(1).getName());
        assertEquals("third", players.get(2).getName());
        assertEquals("fourth (second)", players.get(3).getName());
        assertEquals("fifth", players.get(4).getName());
        assertEquals("sixth", players.get(5).getName());
        assertEquals("seventh (third)", players.get(6).getName());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void findByTotalGamesOrderByWinRate_shouldFilterAndOrder() {
        List<Player> players = service.findByTotalGamesOrderByWinRate(10);
        assertEquals(3, players.size());
        assertEquals("second (first)", players.get(0).getName());
        assertEquals("fourth (second)", players.get(1).getName());
        assertEquals("seventh (third)", players.get(2).getName());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void increaseTotalGames_shouldIncreaseByOne() {
        service.save(new Player("new player"));
        Optional<Player> player = service.findFirstByName("new player");
        assertTrue(player.isPresent());
        assertEquals(0, player.get().getTotalGames());
        service.increaseTotalGames(player.get());
        player = service.findFirstByName("new player");
        assertTrue(player.isPresent());
        assertEquals(1, player.get().getTotalGames());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void increaseTotalWins_shouldIncreaseByOne() {
        service.save(new Player("new player"));
        Optional<Player> player = service.findFirstByName("new player");
        assertTrue(player.isPresent());
        assertEquals(0, player.get().getTotalWins());
        service.increaseTotalWins(player.get());
        player = service.findFirstByName("new player");
        assertTrue(player.isPresent());
        assertEquals(1, player.get().getTotalWins());
    }

}
