package edu.katsala.competitionmanager.service;

import edu.katsala.competitionmanager.dao.RefereeDAO;
import edu.katsala.competitionmanager.model.Referee;
import edu.katsala.competitionmanager.payload.request.CreateRefereeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefereeService {
    private final RefereeDAO refereeDAO;

    public Referee createReferee(CreateRefereeRequest request){
        Referee referee = Referee.builder()
                .fullName(request.getFullName())
                .age(request.getAge())
                .salary(request.getSalary())
                .build();

        return refereeDAO.save(referee);
    }
}
