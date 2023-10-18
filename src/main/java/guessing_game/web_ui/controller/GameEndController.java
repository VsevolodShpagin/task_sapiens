package guessing_game.web_ui.controller;

import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameEndController {

    @Autowired
    private SessionRepository sessions;

    @GetMapping(value = "/gameEnd")
    public String showGameEndPage(HttpServletRequest httpRequest, ModelMap modelMap) {
        try {
            HttpSession httpSession = httpRequest.getSession();
            String id = httpSession.getId();
            Session session = sessions.getSessions().get(id) != null ? sessions.getSessions().get(id) : new Session();
            modelMap.addAttribute("player", session.getPlayer().getName());
            modelMap.addAttribute("result", session.getGame().getResult());
            modelMap.addAttribute("number", session.getNumber());
            return "gameEnd";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

}
