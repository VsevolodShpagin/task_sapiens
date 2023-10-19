package guessing_game.core.service.validator;

import guessing_game.core.converter.NumberConverter;
import guessing_game.core.dto.NumberDto;
import guessing_game.core.request.MakeGuessRequest;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MakeGuessValidatorImplTest {

    @Mock
    private NumberConverter mockNumberConverter;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private NumberDto mockNumberDto;

    @InjectMocks
    private MakeGuessValidatorImpl validator;

    @Test
    void validate_validInput_noError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("1234");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_nullId_returnError() {
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_blankId_returnError() {
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "   ");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_emptyId_returnError() {
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, null);
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_nullGuess_returnError() {
        MakeGuessRequest request = new MakeGuessRequest(null, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_BLANK");
    }

    @Test
    void validate_blankGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_NOT_NUMBER");
    }

    @Test
    void validate_emptyGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("    ");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_NOT_NUMBER");
    }

    @Test
    void validate_letterGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("123A");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_NOT_NUMBER");
    }

    @Test
    void validate_shortGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("123");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_WRONG_LENGTH");
    }

    @Test
    void validate_longGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("123456789");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_WRONG_LENGTH");
    }

    @Test
    void validate_startingZeroGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("0234");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_START_0");
    }

    @Test
    void validate_repeatingNumberGuess_returnError() {
        when(mockNumberConverter.toString(mockNumberDto)).thenReturn("1134");
        MakeGuessRequest request = new MakeGuessRequest(mockNumberDto, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("GUESS_REPEATING");
    }

}
