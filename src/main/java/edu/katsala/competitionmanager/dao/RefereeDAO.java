package edu.katsala.competitionmanager.dao;

import edu.katsala.competitionmanager.model.Referee;
import edu.katsala.competitionmanager.repository.RefereeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RefereeDAO {
    private final RefereeRepository refereeRepository;

    public Referee save(Referee referee){
        return refereeRepository.save(referee);
    }

    public List<Referee> getAllReferees() {
        return refereeRepository.findAll();
    }

    public Referee getRefereeById(Long id) {
        return refereeRepository.findById(id)
                .orElse(null);
    }
}
