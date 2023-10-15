package guessing_game.web_ui.controller;

import guessing_game.core.request.SignInRequest;
import guessing_game.core.response.SignInResponse;
import guessing_game.core.service.SignInService;
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

    @GetMapping(value = "/signIn")
    public String showSignInPage(ModelMap modelMap) {
        modelMap.addAttribute("signInRequest", new SignInRequest());
        return "signIn";
    }

    @PostMapping(value = "/signIn")
    public String processSignInRequest(
            @ModelAttribute(value = "signInRequest") SignInRequest request,
            ModelMap modelMap
    ) {
        try {
            SignInResponse response = signInService.execute(request);
            if (response.getErrors() != null && !response.getErrors().isEmpty()) {
                modelMap.addAttribute("signInErrors", response.getErrors());
                return "signIn";
            }
            return "redirect:/game";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

}
