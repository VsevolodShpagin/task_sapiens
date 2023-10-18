package guessing_game.web_ui.controller;

import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SessionRepository sessions;

    @GetMapping(value = "/")
    public String index(HttpServletRequest httpRequest) {
        HttpSession httpSession = httpRequest.getSession();
        String id = httpSession.getId();
        Session session = sessions.getSessions().get(id) != null ? sessions.getSessions().get(id) : new Session();
        sessions.getSessions().put(id, session);
//        httpSession.setAttribute("session", session);
        return "index";
    }

}
