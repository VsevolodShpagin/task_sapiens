package guessing_game.core.number;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorFourDigitImpl implements NumberGenerator {

    public static final int SECRET_NUMBER_LENGTH = 4;
    private static final String AVAILABLE_NUMBERS = "1234567890";

    private final Random random = new Random();

    @Override
    public String createNumber() {
        StringBuilder available = new StringBuilder(AVAILABLE_NUMBERS);
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < SECRET_NUMBER_LENGTH; i++) {
            int index = i == 0
                    ? random.nextInt(0, available.length() - 1)
                    : random.nextInt(0, available.length());
            number.append(available.charAt(index));
            available.deleteCharAt(index);
        }
        return number.toString();
    }

}
