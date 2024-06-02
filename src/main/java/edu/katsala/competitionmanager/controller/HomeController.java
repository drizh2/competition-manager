package edu.katsala.competitionmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@RequestParam(value = "error", required = false) String error,
                       Model model) {
        if (error != null) {
            switch (error) {
                case "no_stadiums": {
                    model.addAttribute("error", "No stadiums found!");
                    break;
                }
                case "no_competition": {
                    model.addAttribute("error", "No competitions found!");
                    break;
                }
                case "no_referees": {
                    model.addAttribute("error", "No referees found!");
                    break;
                }
                case "no_sportsmen": {
                    model.addAttribute("error", "No sportsmen found!");
                    break;
                }
            }
        }
        return "home";
    }
}
