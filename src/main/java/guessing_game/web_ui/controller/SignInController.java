package guessing_game.web_ui.controller;

import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.SignInResponse;
import guessing_game.core.service.SignInService;
import guessing_game.core.session.Session;
import guessing_game.core.session.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;
    @Autowired
    private SessionRepository sessions;

    @GetMapping(value = "/signIn")
    public String showSignInPage(ModelMap modelMap) {
        modelMap.addAttribute("signInRequest", new SignInRequest());
        return "signIn";
    }

    @PostMapping(value = "/signIn")
    public String processSignInRequest(
            @ModelAttribute(value = "signInRequest") SignInRequest request,
            HttpServletRequest httpRequest,
            ModelMap modelMap
    ) {
        try {
            HttpSession httpSession = httpRequest.getSession();
            String id = httpSession.getId();
            request.setSessionId(id);
            SignInResponse response = signInService.execute(request);
            if (response.getErrors() != null && !response.getErrors().isEmpty()) {
                modelMap.addAttribute("signInErrors", response.getErrors());
                return "signIn";
            }
            Session session = sessions.getSessions().get(id) != null ? sessions.getSessions().get(id) : new Session();
            sessions.getSessions().put(id, session);
//            httpSession.setAttribute("session", session);
            return "redirect:/game";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

}
