package edu.katsala.competitionmanager.repository;

import edu.katsala.competitionmanager.model.Competition;
import edu.katsala.competitionmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findById(long id);
    List<Competition> findAllByUser(User user);
}
