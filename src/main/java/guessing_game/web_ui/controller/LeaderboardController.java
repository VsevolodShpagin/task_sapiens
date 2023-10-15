package guessing_game.web_ui.controller;

import guessing_game.core.converter.PlayerConverter;
import guessing_game.core.domain.Player;
import guessing_game.core.dto.PlayerDto;
import guessing_game.core.request.GetLeaderboardRequest;
import guessing_game.core.response.GetLeaderboardResponse;
import guessing_game.core.service.GetLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    private GetLeaderboardService getLeaderboardService;
    @Autowired
    private PlayerConverter playerConverter;

    @GetMapping(value = "/leaderboard")
    public String showLeaderboardPage(ModelMap modelMap) {
        try {
            modelMap.addAttribute("getLeaderboardRequest", new GetLeaderboardRequest());
            GetLeaderboardRequest request = (GetLeaderboardRequest) modelMap.getAttribute("getLeaderboardRequest");
            getLeaderboard(request, modelMap);
            return "leaderboard";
        } catch (RuntimeException exception) {
            return "error";
        }
    }

    @PostMapping(value = "/leaderboard")
    public String processGetLeaderboardRequest(
            @ModelAttribute(value = "getLeaderboardRequest") GetLeaderboardRequest request,
            ModelMap modelMap
    ) {
        try {
            getLeaderboard(request, modelMap);
            return "leaderboard";
        } catch (RuntimeException exception) {
            return "error";
        }

    }

    private void getLeaderboard(GetLeaderboardRequest request, ModelMap modelMap) {
        GetLeaderboardResponse response = getLeaderboardService.execute(request);
        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            modelMap.addAttribute("getLeaderboardErrors", response.getErrors());
        } else {
            modelMap.addAttribute("players", toDto(response.getPlayers()));
        }
    }

    private List<PlayerDto> toDto(List<Player> players) {
        return players.stream().map(playerConverter::toPlayerDto).toList();
    }

}
