package guessing_game.core.service;

import guessing_game.core.Session;
import guessing_game.core.converter.NumberConverter;
import guessing_game.core.database.service.GameService;
import guessing_game.core.database.service.GuessService;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Game;
import guessing_game.core.domain.GuessResult;
import guessing_game.core.domain.Player;
import guessing_game.core.number.GuessEvaluator;
import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.response.MakeGuessResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.MakeGuessValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MakeGuessServiceImplTest {

    @Mock
    private Session mockSession;
    @Mock
    private MakeGuessValidator mockValidator;
    @Mock
    private GuessEvaluator mockGuessEvaluator;
    @Mock
    private NumberConverter mockNumberConverter;
    @Mock
    private GuessService mockGuessService;
    @Mock
    private GameService mockGameService;
    @Mock
    private PlayerService mockPlayerService;
    @Mock
    private MakeGuessRequest mockRequest;
    @Mock
    private ResponseError mockError;
    @Mock
    private GuessResult mockGuessResult;
    @Mock
    private Game mockGame;
    @Mock
    private Player mockPlayer;

    @InjectMocks
    private MakeGuessServiceImpl service;

    @Test
    void execute_invalidRequest_returnError() {
        when(mockValidator.validate(any())).thenReturn(List.of(mockError));
        MakeGuessResponse response = service.execute(mockRequest);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    void execute_validGuess_reduceAttemptsLeft() {
        when(mockGuessEvaluator.evaluate(any())).thenReturn(mockGuessResult);
        when(mockSession.getAttemptsLeft()).thenReturn(8, 7);
        when(mockSession.getNumber()).thenReturn("1234");
        when(mockGuessResult.getMatch()).thenReturn(1);
        when(mockGuessResult.getPlaceMatch()).thenReturn(1);
        service.execute(mockRequest);
        verify(mockSession).setAttemptsLeft(7);
    }

    @Test
    void execute_validGuess_saveGuess() {
        when(mockGuessEvaluator.evaluate(any())).thenReturn(mockGuessResult);
        when(mockSession.getNumber()).thenReturn("1234");
        when(mockGuessResult.getMatch()).thenReturn(1);
        when(mockGuessResult.getPlaceMatch()).thenReturn(1);
        service.execute(mockRequest);
        verify(mockGuessService).save(any());
    }

    @Test
    void execute_winningGuess_updateToWin() {
        when(mockGuessEvaluator.evaluate(any())).thenReturn(mockGuessResult);
        when(mockSession.getNumber()).thenReturn("1234");
        when(mockGuessResult.getMatch()).thenReturn(0);
        when(mockGuessResult.getPlaceMatch()).thenReturn(4);
        when(mockSession.getGame()).thenReturn(mockGame);
        when(mockSession.getPlayer()).thenReturn(mockPlayer);
        service.execute(mockRequest);
        verify(mockGame).setResult(true);
        verify(mockGameService).updateGameResult(mockGame, true);
        verify(mockPlayerService).increaseTotalWins(mockPlayer);
    }

    @Test
    void execute_lastGuess_doNotUpdateToWin() {
        when(mockGuessEvaluator.evaluate(any())).thenReturn(mockGuessResult);
        when(mockSession.getAttemptsLeft()).thenReturn(1, 0);
        when(mockSession.getNumber()).thenReturn("1234");
        when(mockGuessResult.getMatch()).thenReturn(1);
        when(mockGuessResult.getPlaceMatch()).thenReturn(2);
        service.execute(mockRequest);
        verify(mockGame, times(0)).setResult(true);
        verify(mockGameService, times(0)).updateGameResult(any(), anyBoolean());
        verify(mockPlayerService, times(0)).increaseTotalWins(any());
    }

    @Test
    void execute_lastWinningGuess_updateToWin() {
        when(mockGuessEvaluator.evaluate(any())).thenReturn(mockGuessResult);
        when(mockSession.getAttemptsLeft()).thenReturn(1, 0);
        when(mockSession.getNumber()).thenReturn("1234");
        when(mockGuessResult.getMatch()).thenReturn(0);
        when(mockGuessResult.getPlaceMatch()).thenReturn(4);
        when(mockSession.getGame()).thenReturn(mockGame);
        when(mockSession.getPlayer()).thenReturn(mockPlayer);
        service.execute(mockRequest);
        verify(mockGame).setResult(true);
        verify(mockGameService).updateGameResult(mockGame, true);
        verify(mockPlayerService).increaseTotalWins(mockPlayer);
    }

}
