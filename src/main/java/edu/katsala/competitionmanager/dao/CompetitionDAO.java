package edu.katsala.competitionmanager.dao;

import edu.katsala.competitionmanager.model.Competition;
import edu.katsala.competitionmanager.model.User;
import edu.katsala.competitionmanager.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompetitionDAO {
    private final CompetitionRepository competitionRepository;

    public Competition getCompetitionById(Long contractId) {
        return competitionRepository.findById(contractId)
                .orElse(null);
    }

    public List<Competition> getAllCompetitionsForUser(User user) {
        return competitionRepository.findAllByUser(user);
    }

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
