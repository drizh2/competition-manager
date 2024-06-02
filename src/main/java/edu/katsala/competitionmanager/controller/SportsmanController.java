package edu.katsala.competitionmanager.controller;

import edu.katsala.competitionmanager.payload.request.CreateSportsmanRequest;
import edu.katsala.competitionmanager.service.SportsmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sportsman")
@RequiredArgsConstructor
public class SportsmanController {

    private final SportsmanService sportsmanService;

    @GetMapping
    public String getSportsman() {
        return "sportsmanCreation";
    }

    @PostMapping
    public String addSportsman(CreateSportsmanRequest request) {
        sportsmanService.createSportsman(request);

        return "redirect:/home";
    }
}
