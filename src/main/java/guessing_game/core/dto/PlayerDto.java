package guessing_game.core.dto;

import lombok.Data;

@Data
public class PlayerDto {

    private final String name;
    private final int totalGames;
    private final int winPercent;

}
