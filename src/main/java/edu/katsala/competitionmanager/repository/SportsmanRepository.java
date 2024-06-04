package edu.katsala.competitionmanager.repository;

import edu.katsala.competitionmanager.model.Competition;
import edu.katsala.competitionmanager.model.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
    List<Sportsman> getAllByCompetition(Competition competition);
}
