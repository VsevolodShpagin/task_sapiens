package guessing_game.core.service;

import guessing_game.core.database.service.GuessService;
import guessing_game.core.domain.Guess;
import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.response.GetGuessHistoryResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.GetGuessHistoryValidator;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetGuessHistoryServiceImpl implements GetGuessHistoryService {

    @Autowired
    private SessionRepository sessions;
    @Autowired
    private GetGuessHistoryValidator validator;
    @Autowired
    private GuessService guessService;

    @Override
    public GetGuessHistoryResponse execute(GetGuessHistoryRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new GetGuessHistoryResponse(null, errors);
        String id = request.getSessionId();
        Session session = sessions.getSessions().get(id) != null ? sessions.getSessions().get(id) : new Session();
        List<Guess> guesses = guessService.findByGame(session.getGame());
        return new GetGuessHistoryResponse(guesses, null);
    }

}
