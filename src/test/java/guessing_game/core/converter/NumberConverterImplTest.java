package guessing_game.core.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberConverterImplTest extends ConverterTest {

    private final NumberConverter converter = new NumberConverterImpl();

    @Test
    void toString_convertDto_getString() {
        String result = converter.toString(NUMBER_DTO);
        assertEquals(NUMBER_STRING, result);
    }

}
