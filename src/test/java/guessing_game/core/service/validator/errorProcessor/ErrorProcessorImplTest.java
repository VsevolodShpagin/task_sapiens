package guessing_game.core.service.validator.errorProcessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorProcessorImplTest {

    private final ErrorProcessor errorProcessor = new ErrorProcessorImpl();

    @Test
    void getErrorMessage_validCode_returnMessage() {
        String result = errorProcessor.getErrorMessage("MIN_NOT_NUMBER");
        assertEquals("Minimal played game count must be a number", result);
    }

    @Test
    void getErrorMessage_nullCode_returnGeneralMessage() {
        String result = errorProcessor.getErrorMessage(null);
        assertEquals("Something went wrong", result);
    }

    @Test
    void getErrorMessage_wrongCode_returnGeneralMessage() {
        String result = errorProcessor.getErrorMessage("WRONG");
        assertEquals("Something went wrong", result);
    }

}
