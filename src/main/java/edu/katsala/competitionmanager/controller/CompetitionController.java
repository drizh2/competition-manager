package edu.katsala.competitionmanager.controller;

import edu.katsala.competitionmanager.dao.CompetitionDAO;
import edu.katsala.competitionmanager.dao.RefereeDAO;
import edu.katsala.competitionmanager.dao.SportsmanDAO;
import edu.katsala.competitionmanager.dao.StadiumDAO;
import edu.katsala.competitionmanager.model.*;
import edu.katsala.competitionmanager.payload.request.CreateCompetitionRequest;
import edu.katsala.competitionmanager.service.CompetitionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    private final StadiumDAO stadiumDAO;
    private final RefereeDAO refereeDAO;
    private final CompetitionService competitionService;
    private final SportsmanDAO sportsmanDAO;
    private final CompetitionDAO competitionDAO;

    public CompetitionController(StadiumDAO stadiumDAO, RefereeDAO refereeDAO, CompetitionService competitionService, SportsmanDAO sportsmanDAO, CompetitionDAO competitionDAO) {
        this.stadiumDAO = stadiumDAO;
        this.refereeDAO = refereeDAO;
        this.competitionService = competitionService;
        this.sportsmanDAO = sportsmanDAO;
        this.competitionDAO = competitionDAO;
    }

    @PostMapping
    public String createEmptyContract(@AuthenticationPrincipal User user) {
        List<Stadium> stadiums = stadiumDAO.getAllStadiums();
        if (stadiums.isEmpty()) {
            return "redirect:/home?error=no_stadiums";
        }

        List<Referee> referees = refereeDAO.getAllReferees();
        if (referees.isEmpty()) {
            return "redirect:/home?error=no_referees";
        }

        Competition competition = competitionService.createEmptyCompetition(user);
        return "redirect:/competition/" + competition.getId() + "/stadium";
    }

    @GetMapping("/{compId}/stadium")
    public String getAllStadiums(@PathVariable Long compId, Model model) {
        model.addAttribute("compId", compId);
        model.addAttribute("stadiums", stadiumDAO.getAllStadiums());

        return "competitionStadiumAddition";
    }

    @PostMapping("/{compId}/stadium")
    public String addStadium(@PathVariable Long compId,
                             Long stadiumId) {
        competitionService.addStadium(compId, stadiumId);

        return "redirect:/competition/" + compId + "/referee";
    }

    @GetMapping("/{compId}/referee")
    public String getAllReferees(@PathVariable Long compId, Model model) {
        model.addAttribute("compId", compId);
        model.addAttribute("referees", refereeDAO.getAllReferees());

        return "competitionRefereeAddition";
    }

    @PostMapping("/{compId}/referee")
    public String addReferee(@PathVariable Long compId,
                             Long refId) {
        competitionService.addReferee(compId, refId);

        return "redirect:/competition/" + compId + "/info";
    }

    @GetMapping("/{compId}/info")
    public String getCompetitionInfo(@PathVariable Long compId, Model model) {
        model.addAttribute("compId", compId);

        return "competitionInfoAddition";
    }

    @PostMapping("/{compId}/info")
    public String createCompetition(@PathVariable Long compId,
                                    CreateCompetitionRequest request) {
        competitionService.createCompetition(compId, request);
        return "redirect:/home";
    }

    @GetMapping("/user")
    public String getAllCompetitionsForUser(@AuthenticationPrincipal User user, Model model) {
        List<Competition> competitions = competitionDAO.getAllCompetitionsForUser(user);

        if (competitions.isEmpty()) {
            return "redirect:/home?error=no_competition";
        }

        model.addAttribute("competitions", competitionDAO.getAllCompetitionsForUser(user));

        return "userCompetitions";
    }

    @GetMapping("/{compId}/inviteSportsmen")
    public String getInviteSportsmen(@PathVariable Long compId,
                                     @RequestParam(value = "budget", required = false) Integer budget,
                                     @RequestParam(value = "error", required = false) String error,
                                     Model model) {
        List<Sportsman> sportsmen = sportsmanDAO.getAllSportsmen();

        model.addAttribute("sportsmen", sportsmen);
        model.addAttribute("compId", compId);

        if (sportsmen.isEmpty()) {
            return "redirect:/home?error=no_sportsmen";
        }

        if (error != null) {
            model.addAttribute("budget", budget);
            model.addAttribute("error", "No budget to invite this sportsman!");
            return "competitionInviteSportsmen";
        }

        if (budget == null) {
            Integer requestedBudget = competitionService.getBudgetForCompetition(compId);
            model.addAttribute("budget", requestedBudget);
        } else {
            model.addAttribute("budget", budget);
        }
        return "competitionInviteSportsmen";
    }

    @PostMapping("/{compId}/inviteSportsman")
    public String inviteSportsman(@PathVariable Long compId,
                                  @RequestParam(value = "budget", required = false) Integer budget,
                                  Long sportsmanId) {

        Integer tempBudget = competitionService.decreaseBudget(budget, sportsmanId);

        if (tempBudget < 0) {
            return "redirect:/competition/" + compId + "/inviteSportsmen?budget=" + budget + "&error=no_budget";
        }

        competitionService.inviteSportsman(compId, sportsmanId);

        return "redirect:/competition/" + compId + "/inviteSportsmen?budget=" + tempBudget;
    }

}
