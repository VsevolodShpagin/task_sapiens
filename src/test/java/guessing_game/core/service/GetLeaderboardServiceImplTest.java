package guessing_game.core.service;

import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Player;
import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.GetLeaderboardResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.GetLeaderboardValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetLeaderboardServiceImplTest {

    @Mock
    private GetLeaderboardValidator mockValidator;
    @Mock
    private PlayerService mockPlayerService;
    @Mock
    private GetLeaderboardRequest mockRequest;
    @Mock
    private ResponseError mockError;
    @Mock
    private Player mockPlayer;

    @InjectMocks
    private GetLeaderboardServiceImpl service;

    @Test
    void execute_invalidRequest_returnError() {
        when(mockValidator.validate(any())).thenReturn(List.of(mockError));
        GetLeaderboardResponse response = service.execute(mockRequest);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    void execute_validInput_returnPlayers() {
        when(mockPlayerService.findAllOrderByWinRate()).thenReturn(List.of(mockPlayer, mockPlayer));
        GetLeaderboardResponse response = service.execute(mockRequest);
        assertEquals(2, response.getPlayers().size());
    }

    @Test
    void execute_nullMinCount_getAllPlayers() {
        when(mockRequest.getMinGameCount()).thenReturn(null);
        service.execute(mockRequest);
        verify(mockPlayerService).findAllOrderByWinRate();
    }

    @Test
    void execute_blankMinCount_getAllPlayers() {
        when(mockRequest.getMinGameCount()).thenReturn("");
        service.execute(mockRequest);
        verify(mockPlayerService).findAllOrderByWinRate();
    }

    @Test
    void execute_emptyMinCount_getAllPlayers() {
        when(mockRequest.getMinGameCount()).thenReturn("    ");
        service.execute(mockRequest);
        verify(mockPlayerService).findAllOrderByWinRate();
    }

    @Test
    void execute_withMinCount_getFilteredPlayers() {
        when(mockRequest.getMinGameCount()).thenReturn("10");
        service.execute(mockRequest);
        verify(mockPlayerService).findByTotalGamesOrderByWinRate(10);
    }

}
