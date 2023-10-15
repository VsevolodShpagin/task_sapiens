package guessing_game.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberDto {

    private int firstDigit;
    private int secondDigit;
    private int thirdDigit;
    private int fourthDigit;

}
