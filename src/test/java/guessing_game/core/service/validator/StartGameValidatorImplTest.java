package guessing_game.core.service.validator;

import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.shared.ResponseError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StartGameValidatorImplTest {

    private final StartGameValidator validator = new StartGameValidatorImpl();

    @Test
    void validate_validInput_noError() {
        StartGameRequest request = new StartGameRequest();
        List<ResponseError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

}
