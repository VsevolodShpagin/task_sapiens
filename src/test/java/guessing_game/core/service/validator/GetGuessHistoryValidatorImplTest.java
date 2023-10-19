package guessing_game.core.service.validator;

import guessing_game.core.request.GetGuessHistoryRequest;
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
class GetGuessHistoryValidatorImplTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;

    @InjectMocks
    private GetGuessHistoryValidatorImpl validator;

    @Test
    void validate_validInput_noError() {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest("sessionId");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_nullId_returnError() {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest(null);
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_blankId_returnError() {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest("");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_emptyId_returnError() {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest("   ");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

}
