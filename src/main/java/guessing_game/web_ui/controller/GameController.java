package guessing_game.web_ui.controller;

import guessing_game.core.converter.GuessConverter;
import guessing_game.core.domain.Guess;
import guessing_game.core.dto.GuessDto;
import guessing_game.core.request.GetGuessHistoryRequest;
import guessing_game.core.request.MakeGuessRequest;
import guessing_game.core.request.StartGameRequest;
import guessing_game.core.response.GetGuessHistoryResponse;
import guessing_game.core.response.MakeGuessResponse;
import guessing_game.core.service.GetGuessHistoryService;
import guessing_game.core.service.MakeGuessService;
import guessing_game.core.service.StartGameService;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private StartGameService startGameService;
    @Autowired
    private MakeGuessService makeGuessService;
    @Autowired
    private GetGuessHistoryService getGuessHistoryService;
    @Autowired
    private GuessConverter guessConverter;
    @Autowired
    private SessionRepository sessions;

    @GetMapping(value = "/game")
    public String showGamePage(HttpSession httpSession, ModelMap modelMap) {
        try {
            Session session = sessions.getSession(httpSession.getId());
            modelMap.addAttribute("player", session.getPlayer().getName());
            modelMap.addAttribute("makeGuessRequest", new MakeGuessRequest());
            startGameService.execute(new StartGameRequest(httpSession.getId()));
            modelMap.addAttribute("attemptsLeft", session.getAttemptsLeft());
            return "game";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

    @PostMapping(value = "/game")
    public String processMakeGuessRequest(
            @ModelAttribute(value = "makeGuessRequest") MakeGuessRequest request,
            HttpSession httpSession,
            ModelMap modelMap
    ) {
        try {
            Session session = sessions.getSession(httpSession.getId());
            modelMap.addAttribute("player", session.getPlayer().getName());
            request.setSessionId(httpSession.getId());
            makeGuess(request, modelMap);
            if (isEnd(session)) return "redirect:/gameEnd";
            modelMap.addAttribute("attemptsLeft", session.getAttemptsLeft());
            getGuessHistory(modelMap, httpSession.getId());
            return "game";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

    private void makeGuess(MakeGuessRequest request, ModelMap modelMap) {
        MakeGuessResponse response = makeGuessService.execute(request);
        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            modelMap.addAttribute("makeGuessErrors", response.getErrors());
        }
    }

    private boolean isEnd(Session session) {
        return session.getGame().getResult() || session.getAttemptsLeft() == 0;
    }

    private void getGuessHistory(ModelMap modelMap, String sessionId) {
        GetGuessHistoryRequest request = new GetGuessHistoryRequest(sessionId);
        GetGuessHistoryResponse response = getGuessHistoryService.execute(request);
        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            modelMap.addAttribute("getGuessHistoryErrors", response.getErrors());
        } else {
            modelMap.addAttribute("guesses", toDto(response.getGuesses()));
            getLastGuess(response.getGuesses(), modelMap);
        }
    }

    private void getLastGuess(List<Guess> guesses, ModelMap modelMap) {
        if (guesses.size() > 0) {
            modelMap.addAttribute("lastGuess", guessConverter.toGuessDto(guesses.get(0)));
        }
    }

    private List<GuessDto> toDto(List<Guess> guesses) {
        return guesses.stream().map(guessConverter::toGuessDto).toList();
    }

}
