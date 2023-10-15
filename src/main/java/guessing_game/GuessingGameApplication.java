package guessing_game;

import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Player;
import guessing_game.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GuessingGameApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        //Something for the leaderboard
        PlayerService playerService = context.getBean(PlayerService.class);
        playerService.save(new Player("10 - 05", 10, 5));
        playerService.save(new Player("05 - 00", 5, 0));
        playerService.save(new Player("15 - 05", 15, 5));
        playerService.save(new Player("02 - 01", 2, 1));
        playerService.save(new Player("01 - 01", 1, 1));
        playerService.save(new Player("10 - 02", 10, 2));
        playerService.save(new Player("11 - 01", 11, 1));
        playerService.save(new Player("09 - 03", 9, 3));
        playerService.save(new Player("20 - 05", 20, 5));

    }

}
