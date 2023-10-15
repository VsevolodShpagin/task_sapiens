package guessing_game.core.converter;

import guessing_game.core.database.entity.GameEntity;
import guessing_game.core.database.repository.PlayerRepository;
import guessing_game.core.domain.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameConverterImplTest extends ConverterTest {

    @Mock
    private PlayerRepository mockPlayerRepository;
    @Mock
    private PlayerConverter mockPlayerConverter;

    @InjectMocks
    private GameConverterImpl gameConverter;

    @Test
    void toGameEntity_convertDomain_getEntity() {
        when(mockPlayerRepository.findById(PLAYER.getId())).thenReturn(Optional.of(PLAYER_ENTITY));
        GameEntity result = gameConverter.toGameEntity(GAME);
        assertEquals(GAME_ENTITY.getId(), result.getId());
        assertEquals(GAME_ENTITY.getPlayer(), result.getPlayer());
        assertEquals(GAME_ENTITY.getResult(), result.getResult());
        result.setId(1L);
        assertEquals(GAME_ENTITY, result);
    }

    @Test
    void toGame_convertEntity_getDomain() {
        when(mockPlayerConverter.toPlayer(PLAYER_ENTITY)).thenReturn(PLAYER);
        Game result = gameConverter.toGame(GAME_ENTITY);
        assertEquals(GAME.getId(), result.getId());
        assertEquals(GAME.getPlayer(), result.getPlayer());
        assertEquals(GAME.getResult(), result.getResult());
        assertEquals(GAME, result);
    }

}
