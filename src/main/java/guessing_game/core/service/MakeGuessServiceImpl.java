package guessing_game.core.service;

import guessing_game.core.converter.NumberConverter;
import guessing_game.core.database.service.GameService;
import guessing_game.core.database.service.GuessService;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Guess;
import guessing_game.core.domain.GuessResult;
import guessing_game.core.number.GuessEvaluator;
import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.response.MakeGuessResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.MakeGuessValidator;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class MakeGuessServiceImpl implements MakeGuessService {

    @Autowired
    private MakeGuessValidator validator;
    @Autowired
    private GuessEvaluator guessEvaluator;
    @Autowired
    private NumberConverter numberConverter;
    @Autowired
    private GuessService guessService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SessionRepository sessions;

    @Override
    public MakeGuessResponse execute(MakeGuessRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new MakeGuessResponse(errors);
        String id = request.getSessionId();
        Session session = sessions.getSessions().get(id) != null ? sessions.getSessions().get(id) : new Session();
        GuessResult guessResult = guessEvaluator.evaluate(numberConverter.toString(request.getGuess()), session.getNumber());
        session.setAttemptsLeft(session.getAttemptsLeft() - 1);
        Guess guess = new Guess(
                session.getGame(),
                numberConverter.toString(request.getGuess()),
                guessResult.getMatch(),
                guessResult.getPlaceMatch(),
                LocalDateTime.now());
        guessService.save(guess);
        if (isWin(guessResult, session.getNumber())) processWin(session);
        return new MakeGuessResponse();
    }

    private boolean isWin(GuessResult guessResult, String number) {
        return guessResult.getPlaceMatch() == number.length();
    }

    private void processWin(Session session) {
        session.getGame().setResult(true);
        gameService.updateGameResult(session.getGame(), true);
        playerService.increaseTotalWins(session.getPlayer());
    }

}
