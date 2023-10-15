package guessing_game.core.service;

import guessing_game.core.Session;
import guessing_game.core.database.service.PlayerService;
import guessing_game.core.domain.Player;
import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.SignInResponse;
import guessing_game.core.response.shared.ResponseError;
import guessing_game.core.service.validator.SignInValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class SignInServiceImpl implements SignInService {

    @Autowired
    private Session session;
    @Autowired
    private SignInValidator validator;
    @Autowired
    private PlayerService playerService;

    @Override
    public SignInResponse execute(SignInRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new SignInResponse(errors);
        Player player = playerService.findFirstByName(request.getName())
                .orElseGet(() -> createNewPlayer(request.getName()));
        session.setPlayer(player);
        return new SignInResponse(request.getName());
    }

    private Player createNewPlayer(String name) {
        return playerService.save(new Player(name));
    }

}
