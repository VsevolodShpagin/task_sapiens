package guessing_game.web_ui.controller;

import guessing_game.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameEndController {

    @Autowired
    private Session session;

    @GetMapping(value = "/gameEnd")
    public String showGameEndPage(ModelMap modelMap) {
        try {
            modelMap.addAttribute("player", session.getPlayer().getName());
            modelMap.addAttribute("result", session.getGame().getResult());
            modelMap.addAttribute("number", session.getNumber());
            return "gameEnd";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

}
