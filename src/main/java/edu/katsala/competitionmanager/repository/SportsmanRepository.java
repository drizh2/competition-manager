package edu.katsala.competitionmanager.repository;

import edu.katsala.competitionmanager.model.Sportsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsmanRepository extends JpaRepository<Sportsman, Long> {
}
