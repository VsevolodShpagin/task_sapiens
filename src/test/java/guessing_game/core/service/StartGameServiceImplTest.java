package guessing_game.core.service;

import guessing_game.core.database.service.GameService;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Game;
import guessing_game.core.domain.Player;
import guessing_game.core.number.NumberGenerator;
import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.StartGameResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.StartGameValidator;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
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
class StartGameServiceImplTest {

    @Mock
    private StartGameValidator mockValidator;
    @Mock
    private PlayerService mockPlayerService;
    @Mock
    private NumberGenerator mockNumberGenerator;
    @Mock
    private GameService mockGameService;
    @Mock
    private SessionRepository mockSessionRepository;
    @Mock
    private StartGameRequest mockRequest;
    @Mock
    private ResponseError mockError;
    @Mock
    private Session mockSession;
    @Mock
    private Player mockPlayer;
    @Mock
    private Game mockGame;

    @InjectMocks
    private StartGameServiceImpl service;

    @Test
    void execute_invalidRequest_returnError() {
        when(mockValidator.validate(any())).thenReturn(List.of(mockError));
        StartGameResponse response = service.execute(mockRequest);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    void execute_validRequest_saveGame() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        service.execute(mockRequest);
        verify(mockGameService).save(any());
    }

    @Test
    void execute_validRequest_increaseTotalGames() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        when(mockSession.getPlayer()).thenReturn(mockPlayer);
        service.execute(mockRequest);
        verify(mockPlayerService).increaseTotalGames(mockPlayer);
    }

    @Test
    void execute_validRequest_createNumber() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        service.execute(mockRequest);
        verify(mockNumberGenerator).createNumber();
    }

    @Test
    void execute_validRequest_setGame() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        when(mockGameService.save(any())).thenReturn(mockGame);
        service.execute(mockRequest);
        verify(mockSession).setGame(mockGame);
    }

    @Test
    void execute_validRequest_setNumber() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        when(mockNumberGenerator.createNumber()).thenReturn("1234");
        service.execute(mockRequest);
        verify(mockSession).setNumber("1234");
    }

    @Test
    void execute_validRequest_setAttemptsLeft() {
        when(mockSessionRepository.getSession(any())).thenReturn(mockSession);
        service.execute(mockRequest);
        verify(mockSession).setAttemptsLeft(8);
    }

}
