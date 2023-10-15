package guessing_game.core.converter;

import guessing_game.core.dto.NumberDto;
import org.springframework.stereotype.Component;

@Component
public class NumberConverterImpl implements NumberConverter {

    @Override
    public String toString(NumberDto number) {
        return "" + number.getFirstDigit() +
                number.getSecondDigit() +
                number.getThirdDigit() +
                number.getFourthDigit();
    }

}
