package guessing_game.core.converter;

import guessing_game.core.database.entity.PlayerEntity;
import guessing_game.core.domain.Player;
import guessing_game.core.dto.PlayerDto;

public interface PlayerConverter {

    PlayerEntity toPlayerEntity(Player player);

    Player toPlayer(PlayerEntity playerEntity);

    PlayerDto toPlayerDto(Player player);

}
