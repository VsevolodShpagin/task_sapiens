package guessing_game.core.service.validator;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.errorProcessor.ErrorProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartGameValidatorImplTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;

    @InjectMocks
    private StartGameValidatorImpl validator;

    @Test
    void validate_validInput_noError() {
        StartGameRequest request = new StartGameRequest("sessionId");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_nullId_returnError() {
        StartGameRequest request = new StartGameRequest(null);
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_blankId_returnError() {
        StartGameRequest request = new StartGameRequest("");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_emptyId_returnError() {
        StartGameRequest request = new StartGameRequest("  ");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

}
