package guessing_game.core.database.service;

import guessing_game.core.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Player save(Player player);

    Optional<Player> findFirstByName(String name);

    List<Player> findAllOrderByWinRate();

    List<Player> findByTotalGamesOrderByWinRate(int minGameCount);

    void increaseTotalGames(Player player);

    void increaseTotalWins(Player player);

}
