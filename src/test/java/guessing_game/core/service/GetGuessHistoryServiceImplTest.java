package guessing_game.core.service;

import guessing_game.core.Session;
import guessing_game.core.database.service.GuessService;
import guessing_game.core.domain.Guess;
import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.GetGuessHistoryResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.GetGuessHistoryValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGuessHistoryServiceImplTest {

    @Mock
    private Session mockSession;
    @Mock
    private GetGuessHistoryValidator mockValidator;
    @Mock
    private GuessService mockGuessService;
    @Mock
    private GetGuessHistoryRequest mockRequest;
    @Mock
    private ResponseError mockError;
    @Mock
    private Guess mockGuess;

    @InjectMocks
    private GetGuessHistoryServiceImpl service;

    @Test
    void execute_invalidRequest_returnError() {
        when(mockValidator.validate(any())).thenReturn(List.of(mockError));
        GetGuessHistoryResponse response = service.execute(mockRequest);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    void execute_validInput_returnGuesses() {
        when(mockGuessService.findByGame(any())).thenReturn(List.of(mockGuess, mockGuess));
        GetGuessHistoryResponse response = service.execute(mockRequest);
        assertEquals(2, response.getGuesses().size());
    }

}
