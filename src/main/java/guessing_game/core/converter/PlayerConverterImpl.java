package guessing_game.core.converter;

import guessing_game.core.database.entity.PlayerEntity;
import guessing_game.core.domain.Player;
import guessing_game.core.dto.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverterImpl implements PlayerConverter {

    @Override
    public PlayerEntity toPlayerEntity(Player player) {
        return new PlayerEntity(
                player.getId(),
                player.getName(),
                player.getTotalGames(),
                player.getTotalWins()
        );
    }

    @Override
    public Player toPlayer(PlayerEntity playerEntity) {
        return new Player(
                playerEntity.getId(),
                playerEntity.getName(),
                playerEntity.getTotalGames(),
                playerEntity.getTotalWins()
        );
    }

    @Override
    public PlayerDto toPlayerDto(Player player) {
        return new PlayerDto(
                player.getName(),
                player.getTotalGames(),
                getWinRate(player)
        );
    }

    private int getWinRate(Player player) {
        return (int) ((double) player.getTotalWins() / player.getTotalGames() * 100);
    }

}
