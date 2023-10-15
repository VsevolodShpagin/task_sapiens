package guessing_game.core.service.validator;

import guessing_game.core.request.GetLeaderboardRequest;
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
class GetLeaderboardValidatorImplTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;

    @InjectMocks
    private GetLeaderboardValidatorImpl validator;

    @Test
    void validate_validInput_noError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest("10");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_nullGuess_noError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest(null);
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_blankGuess_noError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest("");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_emptyGuess_noError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest("    ");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_letterGuess_returnError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest("A");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("MIN_NOT_NUMBER");
    }

    @Test
    void validate_decimalGuess_returnError() {
        GetLeaderboardRequest request = new GetLeaderboardRequest("10.10");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("MIN_NOT_NUMBER");
    }

}
