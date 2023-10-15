package guessing_game.core.number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorFourDigitImplTest {

    private final NumberGenerator generator = new NumberGeneratorFourDigitImpl();

    @Test
    void createNumber_execute_getFourDigitNumber() {
        for (int i = 0; i < 1000; i++) {
            String result = generator.createNumber();
            assertEquals(4, result.length());
        }
    }

    @Test
    void createNumber_execute_neverStartsWithZero() {
        for (int i = 0; i < 1000; i++) {
            String result = generator.createNumber();
            assertNotEquals('0', result.charAt(0));
        }
    }

    @Test
    void createNumber_execute_getNumberWithDistinctDigits() {
        for (int i = 0; i < 1000; i++) {
            String result = generator.createNumber();
            assertFalse(result.matches("^.*(.).*\\1.*$"));
        }
    }

}
