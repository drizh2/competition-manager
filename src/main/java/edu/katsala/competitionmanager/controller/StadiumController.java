package edu.katsala.competitionmanager.controller;

import edu.katsala.competitionmanager.payload.request.CreateStadiumRequest;
import edu.katsala.competitionmanager.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stadium")
@RequiredArgsConstructor
public class StadiumController {
    private final StadiumService stadiumService;

    @GetMapping
    public String getStadium() {
        return "stadiumCreation";
    }

    @PostMapping
    public String createStadium(CreateStadiumRequest request) {
        stadiumService.createStadium(request);

        return "redirect:/home";
    }
}
