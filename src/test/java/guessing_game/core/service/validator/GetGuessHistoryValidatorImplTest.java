package guessing_game.core.service.validator;

import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.shared.ResponseError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GetGuessHistoryValidatorImplTest {

    private final GetGuessHistoryValidator validator = new GetGuessHistoryValidatorImpl();

    @Test
    void validate_validInput_noError() {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest();
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

}
