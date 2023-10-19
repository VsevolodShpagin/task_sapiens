package guessing_game.core.service;

import guessing_game.core.database.service.GameService;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Game;
import guessing_game.core.number.NumberGenerator;
import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.StartGameResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.StartGameValidator;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class StartGameServiceImpl implements StartGameService {

    private static final int ATTEMPTS_ALLOWED = 8;

    @Autowired
    private StartGameValidator validator;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private NumberGenerator numberGenerator;
    @Autowired
    private GameService gameService;
    @Autowired
    private SessionRepository sessions;

    @Override
    public StartGameResponse execute(StartGameRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new StartGameResponse(errors);
        Session session = sessions.getSession(request.getSessionId());
        Game game = gameService.save(new Game(session.getPlayer(), false));
        playerService.increaseTotalGames(session.getPlayer());
        String number = numberGenerator.createNumber();
        session.setGame(game);
        session.setNumber(number);
        session.setAttemptsLeft(ATTEMPTS_ALLOWED);
        return new StartGameResponse();
    }

}
