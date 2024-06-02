package edu.katsala.competitionmanager.service;

import edu.katsala.competitionmanager.dao.SportsmanDAO;
import edu.katsala.competitionmanager.model.Sportsman;
import edu.katsala.competitionmanager.payload.request.CreateSportsmanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportsmanService {

    private final SportsmanDAO sportsmanDAO;

    public Sportsman createSportsman(CreateSportsmanRequest request) {
        Sportsman sportsman = Sportsman.builder()
                .fullName(request.getFullName())
                .age(request.getAge())
                .salary(request.getSalary())
                .build();

        return sportsmanDAO.save(sportsman);
    }
}
