package guessing_game.core.service;

import guessing_game.core.Session;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Player;
import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.SignInResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.SignInValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInServiceImplTest {

    @Mock
    private Session mockSession;
    @Mock
    private SignInValidator mockValidator;
    @Mock
    private PlayerService mockPlayerService;
    @Mock
    private SignInRequest mockRequest;
    @Mock
    private ResponseError mockError;
    @Mock
    private Player mockPlayer;

    @InjectMocks
    private SignInServiceImpl service;

    @Test
    void execute_invalidRequest_returnError() {
        when(mockValidator.validate(any())).thenReturn(List.of(mockError));
        SignInResponse response = service.execute(mockRequest);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    void execute_validRequest_returnName() {
        when(mockRequest.getName()).thenReturn("name");
        when(mockPlayerService.findFirstByName("name")).thenReturn(Optional.of(mockPlayer));
        SignInResponse response = service.execute(mockRequest);
        assertEquals("name", response.getPlayerName());
    }

    @Test
    void execute_validRequest_setCurrentPlayer() {
        when(mockPlayerService.findFirstByName(any())).thenReturn(Optional.of(mockPlayer));
        service.execute(mockRequest);
        verify(mockSession).setPlayer(mockPlayer);
    }

    @Test
    void execute_newPlayer_setNewPlayer() {
        when(mockPlayerService.findFirstByName(any())).thenReturn(Optional.empty());
        when(mockPlayerService.save(any())).thenReturn(mockPlayer);
        service.execute(mockRequest);
        verify(mockPlayerService).save(any());
        verify(mockSession).setPlayer(mockPlayer);
    }

    @Test
    void execute_existingPlayer_setExistingPlayer() {
        when(mockPlayerService.findFirstByName(any())).thenReturn(Optional.of(mockPlayer));
        service.execute(mockRequest);
        verify(mockPlayerService, times(0)).save(any());
        verify(mockSession).setPlayer(mockPlayer);
    }

}
