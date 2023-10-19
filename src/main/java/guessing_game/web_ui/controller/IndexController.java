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
        Session session = sessions.getSession(httpSession.getId());
        if (session == null) sessions.createSession(httpSession.getId());
        return "index";
    }

}
