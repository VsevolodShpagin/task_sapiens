package guessing_game.core.converter;

import guessing_game.core.database.entity.PlayerEntity;
import guessing_game.core.domain.Player;
import guessing_game.core.dto.PlayerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerConverterImplTest extends ConverterTest {

    private final PlayerConverter playerConverter = new PlayerConverterImpl();

    @Test
    void toPlayerEntity_convertDomain_getEntity() {
        PlayerEntity result = playerConverter.toPlayerEntity(PLAYER);
        assertEquals(PLAYER_ENTITY.getId(), result.getId());
        assertEquals(PLAYER_ENTITY.getName(), result.getName());
        assertEquals(PLAYER_ENTITY.getTotalGames(), result.getTotalGames());
        assertEquals(PLAYER_ENTITY.getTotalWins(), result.getTotalWins());
        result.setId(1L);
        assertEquals(PLAYER_ENTITY, result);
    }

    @Test
    void toPlayer_convertEntity_getDomain() {
        Player result = playerConverter.toPlayer(PLAYER_ENTITY);
        assertEquals(PLAYER.getId(), result.getId());
        assertEquals(PLAYER.getName(), result.getName());
        assertEquals(PLAYER.getTotalGames(), result.getTotalGames());
        assertEquals(PLAYER.getTotalWins(), result.getTotalWins());
        assertEquals(PLAYER, result);
    }

    @Test
    void toPlayerDto_convertDomain_getDto() {
        PlayerDto result = playerConverter.toPlayerDto(PLAYER);
        assertEquals(PLAYER_DTO.getName(), result.getName());
        assertEquals(PLAYER_DTO.getTotalGames(), result.getTotalGames());
        assertEquals(PLAYER_DTO.getWinPercent(), result.getWinPercent());
        assertEquals(PLAYER_DTO, result);
    }

}
