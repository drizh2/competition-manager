package edu.katsala.competitionmanager.controller;

import edu.katsala.competitionmanager.payload.request.CreateRefereeRequest;
import edu.katsala.competitionmanager.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/referee")
@RequiredArgsConstructor
public class RefereeController {

    private final RefereeService refereeService;

    @GetMapping
    public String getReferee() {
        return "refereeCreation";
    }

    @PostMapping
    public String addReferee(CreateRefereeRequest request) {
        refereeService.createReferee(request);

        return "redirect:/home";
    }
}
