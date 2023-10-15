package guessing_game.core.database.service;

import guessing_game.core.converter.PlayerConverter;
import guessing_game.core.database.repository.PlayerRepository;
import guessing_game.core.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerConverter playerConverter;

    @Override
    public Player save(Player player) {
        return playerConverter.toPlayer(playerRepository.save(playerConverter.toPlayerEntity(player)));
    }

    @Override
    public Optional<Player> findFirstByName(String name) {
        return playerRepository.findFirstByName(name).map(playerConverter::toPlayer);
    }

    @Override
    public List<Player> findAllOrderByWinRate() {
        return playerRepository.findAllOrderByWinRate().stream().map(playerConverter::toPlayer).toList();
    }

    @Override
    public List<Player> findByTotalGamesOrderByWinRate(int minGameCount) {
        return playerRepository.findByTotalGamesOrderByWinRate(minGameCount)
                .stream().map(playerConverter::toPlayer).toList();
    }

    @Override
    public void increaseTotalGames(Player player) {
        playerRepository.increaseTotalGames(player.getId());
    }

    @Override
    public void increaseTotalWins(Player player) {
        playerRepository.increaseTotalWins(player.getId());
    }

}
