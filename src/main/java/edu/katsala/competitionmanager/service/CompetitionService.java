package edu.katsala.competitionmanager.service;

import edu.katsala.competitionmanager.dao.CompetitionDAO;
import edu.katsala.competitionmanager.dao.RefereeDAO;
import edu.katsala.competitionmanager.dao.SportsmanDAO;
import edu.katsala.competitionmanager.dao.StadiumDAO;
import edu.katsala.competitionmanager.model.*;
import edu.katsala.competitionmanager.payload.request.CreateCompetitionRequest;
import edu.katsala.competitionmanager.repository.CompetitionRepository;
import edu.katsala.competitionmanager.repository.SportsmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionDAO competitionDAO;
    private final StadiumDAO stadiumDAO;
    private final CompetitionRepository competitionRepository;
    private final RefereeDAO refereeDAO;
    private final SportsmanDAO sportsmanDAO;
    private final SportsmanRepository sportsmanRepository;

    public Competition createEmptyCompetition(User user) {
        Competition competition = new Competition();
        competition.setUser(user);

        return competitionDAO.createCompetition(competition);
    }

    public void addStadium(Long compId, Long stadiumId) {
        Competition competition = competitionDAO.getCompetitionById(compId);
        Stadium stadium = stadiumDAO.getStadiumById(stadiumId);

        competition.setStadium(stadium);
        competitionRepository.save(competition);
    }

    public void addReferee(Long compId, Long refId) {
        Competition competition = competitionDAO.getCompetitionById(compId);
        Referee referee = refereeDAO.getRefereeById(refId);

        competition.setReferee(referee);

        competitionRepository.save(competition);
    }

    public void createCompetition(Long compId, CreateCompetitionRequest request) {
        Competition competition = competitionDAO.getCompetitionById(compId);

        competition.setName(request.getName());
        competition.setPlace(request.getPlace());
        competition.setDate(request.getDate());
        competition.setType(request.getType());
        competition.setBudget(request.getBudget());
        competition.setNumOfParticipants(request.getNumOfParticipants());

        competitionRepository.save(competition);
    }

    public void inviteSportsman(Long compId, Long sportsmanId) {
        Competition competition = competitionDAO.getCompetitionById(compId);
        Sportsman sportsman = sportsmanDAO.getSportsmanById(sportsmanId);

        sportsman.setCompetition(competition);

        sportsmanRepository.save(sportsman);
    }
}
