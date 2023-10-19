package guessing_game.core.service.validator;

import guessing_game.core.request.SignInRequest;
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
class SignInValidatorImplTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;

    @InjectMocks
    private SignInValidatorImpl validator;

    @Test
    void validate_validInput_noError() {
        SignInRequest request = new SignInRequest("name", "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validate_nullId_returnError() {
        SignInRequest request = new SignInRequest("name", null);
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_blankId_returnError() {
        SignInRequest request = new SignInRequest("name", "");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_emptyId_returnError() {
        SignInRequest request = new SignInRequest("name", "   ");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("ID_BLANK");
    }

    @Test
    void validate_nullInput_returnError() {
        SignInRequest request = new SignInRequest(null, "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("NAME_BLANK");
    }

    @Test
    void validate_blankInput_returnError() {
        SignInRequest request = new SignInRequest("", "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("NAME_BLANK");
    }

    @Test
    void validate_emptyInput_returnError() {
        SignInRequest request = new SignInRequest("     ", "sessionId");
        List<ResponseError> result = validator.validate(request);
        assertEquals(1, result.size());
        verify(mockErrorProcessor).getErrorMessage("NAME_BLANK");
    }

}
