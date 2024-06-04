package edu.katsala.competitionmanager.dao;

import edu.katsala.competitionmanager.model.Competition;
import edu.katsala.competitionmanager.model.Sportsman;
import edu.katsala.competitionmanager.repository.SportsmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SportsmanDAO {
    private final SportsmanRepository sportsmanRepository;

    public Sportsman save(Sportsman sportsman) {
        return sportsmanRepository.save(sportsman);
    }

    public List<Sportsman> getAllSportsmen() {
        return sportsmanRepository.findAll();
    }

    public Sportsman getSportsmanById(Long id) {
        return sportsmanRepository.findById(id)
                .orElse(null);
    }

    public List<Sportsman> getSportsmanByCompetition(Competition competition) {
        return sportsmanRepository.getAllByCompetition(competition);
    }
}
