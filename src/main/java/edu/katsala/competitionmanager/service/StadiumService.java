package edu.katsala.competitionmanager.service;

import edu.katsala.competitionmanager.dao.StadiumDAO;
import edu.katsala.competitionmanager.model.Stadium;
import edu.katsala.competitionmanager.payload.request.CreateStadiumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {
    private final StadiumDAO stadiumDAO;

    public Stadium createStadium(CreateStadiumRequest request) {
        Stadium stadium = Stadium.builder()
                .name(request.getName())
                .address(request.getAddress())
                .capacity(request.getCapacity())
                .build();

        return stadiumDAO.save(stadium);
    }
}
