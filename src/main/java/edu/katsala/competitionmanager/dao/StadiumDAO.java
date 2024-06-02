package edu.katsala.competitionmanager.dao;

import edu.katsala.competitionmanager.model.Stadium;
import edu.katsala.competitionmanager.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StadiumDAO {
    private final StadiumRepository stadiumRepository;

    public Stadium save(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }

    public Stadium getStadiumById(Long id) {
        return stadiumRepository.findById(id)
                .orElse(null);
    }
}
