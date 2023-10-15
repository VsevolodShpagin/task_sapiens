package guessing_game.core.request;

import guessing_game.core.dto.NumberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeGuessRequest {

    private NumberDto guess;

}
